package org.hunter.carrental4.interfaces.test;

import org.hunter.carrental4.HunterCarRental4Application;
import org.hunter.carrental4.application.dto.CarTypeDTO;
import org.hunter.carrental4.application.dto.CustomerReservationDTO;
import org.hunter.carrental4.common.model.Result;
import org.hunter.carrental4.common.model.enums.BookingStatus;
import org.hunter.carrental4.common.model.enums.PersonalIdIdType;
import org.hunter.carrental4.infrastructure.repository.impl.CarRepositoryLocalImpl;
import org.hunter.carrental4.infrastructure.repository.impl.ReservationRepositoryLocalImpl;
import org.hunter.carrental4.interfaces.CustomerReservationController;
import org.hunter.carrental4.interfaces.viewobject.BookingRecordVO;
import org.hunter.carrental4.interfaces.viewobject.SimpleReservationRequest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Collection;
import java.util.List;

@SpringBootTest(classes = HunterCarRental4Application.class)
@RunWith(SpringRunner.class)
public class CustomerReservationControllerIntgTests {
    @Resource
    private CustomerReservationController controller;

    @Resource
    private ReservationRepositoryLocalImpl reservationRepository;

    @Resource
    private CarRepositoryLocalImpl carRepository;

    @Before
    public void before() {
        System.out.println("test start");

        carRepository.initialCars();
        reservationRepository.initData();
    }

    @Test
    public void testReserveCarHappyPath() {
        try {
            BookingRecordVO vo = BookingRecordVO.builder().address("xxxxxx").age(33).idCardNumber("12313513545")
                    .name("Hunter").licenseNo("3423525324").phoneNo("18026982000")
                    .pickUpDateStr("2022-07-01 13:00:00").returnDateStr("2022-07-12 13:00:00")
                    .personalIdIdType(PersonalIdIdType.PrcId)
                    .build();

            vo.setCarTypeDTO(CarTypeDTO.builder().name("Toyota Camry").brand("Toyota").build());


            Result<CustomerReservationDTO> customerReservationDTOResult = controller.reserveCar(vo);
            Assert.assertNotNull(customerReservationDTOResult);
            Assert.assertTrue(customerReservationDTOResult.isSuccess());
            CustomerReservationDTO model = customerReservationDTOResult.getModel();
            Assert.assertNotNull(model);
            Assert.assertNotNull(model.getId());
            Assert.assertNotNull(model.getCustomerDTO());


            Result<Collection<CustomerReservationDTO>> collectionResult = controller.listReservations();
            Assert.assertNotNull(collectionResult);
            Assert.assertTrue(collectionResult.isSuccess());
            Collection<CustomerReservationDTO> reservations = collectionResult.getModel();
            Assert.assertNotNull(reservations);
            Assert.assertEquals(1, reservations.size());
            List<CustomerReservationDTO> customerReservationDTOS = reservations.stream().toList();
            Assert.assertNotNull(customerReservationDTOS.get(0).getCustomerId());
        } catch (ParseException e) {
            Assert.fail();
        }
    }

    @Test
    public void testNotEnoughInventory() {
        try {
            BookingRecordVO vo = BookingRecordVO.builder().address("xxxxxx").age(33).idCardNumber("12313513545")
                    .name("Hunter").licenseNo("3423525324").phoneNo("18026982000")
                    .pickUpDateStr("2022-07-01 13:00:00").returnDateStr("2022-07-12 13:00:00")
                    .personalIdIdType(PersonalIdIdType.PrcId)
                    .build();

            vo.setCarTypeDTO(CarTypeDTO.builder().name("Toyota Camry").brand("Toyota").build());


            controller.reserveCar(vo);
            controller.reserveCar(vo);
            Result<CustomerReservationDTO> customerReservationDTOResult = controller.reserveCar(vo);

            Assert.assertNotNull(customerReservationDTOResult);
            Assert.assertFalse(customerReservationDTOResult.isSuccess());

            Result<Collection<CustomerReservationDTO>> collectionResult = controller.listReservations();
            Assert.assertNotNull(collectionResult);
            Assert.assertTrue(collectionResult.isSuccess());
            Collection<CustomerReservationDTO> reservations = collectionResult.getModel();
            Assert.assertNotNull(reservations);
            Assert.assertEquals(2, reservations.size());
        } catch (ParseException e) {
            Assert.fail();
        }
    }

    @Test
    public void testCancelReservation() {
        try {
            BookingRecordVO vo = BookingRecordVO.builder().address("xxxxxx").age(33).idCardNumber("12313513545")
                    .name("Hunter").licenseNo("3423525324").phoneNo("18026982000")
                    .pickUpDateStr("2022-07-01 13:00:00").returnDateStr("2022-07-12 13:00:00")
                    .personalIdIdType(PersonalIdIdType.PrcId)
                    .build();

            vo.setCarTypeDTO(CarTypeDTO.builder().name("Toyota Camry").brand("Toyota").build());


            Result<CustomerReservationDTO> customerReservationDTOResult = controller.reserveCar(vo);

            Assert.assertNotNull(customerReservationDTOResult);
            Assert.assertTrue(customerReservationDTOResult.isSuccess());
            Assert.assertNotNull(customerReservationDTOResult.getModel());
            String reservationId = customerReservationDTOResult.getModel().getId();
            Assert.assertNotNull(reservationId);

            SimpleReservationRequest simpleReservationRequest = SimpleReservationRequest.builder()
                    .reservationId(reservationId)
                    .build();
            Result<CustomerReservationDTO> collectionResult = controller.cancel(simpleReservationRequest);
            Assert.assertNotNull(collectionResult);
            Assert.assertTrue(collectionResult.isSuccess());
            CustomerReservationDTO reservation = collectionResult.getModel();
            Assert.assertNotNull(reservation);
            Assert.assertEquals(reservationId, reservation.getId());
            Assert.assertEquals(BookingStatus.Cancelled, reservation.getBookingStatus());
        } catch (ParseException e) {
            Assert.fail();
        }
    }

    @After
    public void after() {
        System.out.println("test end");
    }

}
