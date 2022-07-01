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
import org.hunter.carrental4.interfaces.EmployeeOperationController;
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

@SpringBootTest(classes = HunterCarRental4Application.class)
@RunWith(SpringRunner.class)
public class EmployeeOperationControllerIntgTests {
    @Resource
    private EmployeeOperationController employeeOperationController;

    @Resource
    private CustomerReservationController customerReservationController;

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
    public void testPickUpCarHappyPath() {
        try {
            BookingRecordVO vo = BookingRecordVO.builder().address("xxxxxx").age(33).idCardNumber("12313513545")
                    .name("Hunter").licenseNo("3423525324").phoneNo("18026982000")
                    .pickUpDateStr("2022-07-01 13:00:00").returnDateStr("2022-07-12 13:00:00")
                    .personalIdIdType(PersonalIdIdType.PrcId)
                    .build();

            vo.setCarTypeDTO(CarTypeDTO.builder().name("Toyota Camry").brand("Toyota").build());


            Result<CustomerReservationDTO> customerReservationDTOResult = customerReservationController.reserveCar(vo);

            Assert.assertNotNull(customerReservationDTOResult);
            Assert.assertTrue(customerReservationDTOResult.isSuccess());
            Assert.assertNotNull(customerReservationDTOResult.getModel());
            String reservationId = customerReservationDTOResult.getModel().getId();
            Assert.assertNotNull(reservationId);

            SimpleReservationRequest simpleReservationRequest = SimpleReservationRequest.builder()
                    .reservationId(reservationId)
                    .build();
            Result<CustomerReservationDTO> pickUpResult =
                    employeeOperationController.confirmPickingUp(simpleReservationRequest);
            Assert.assertNotNull(pickUpResult);
            Assert.assertTrue(pickUpResult.isSuccess());
            CustomerReservationDTO reservation = pickUpResult.getModel();
            Assert.assertNotNull(reservation);
            Assert.assertEquals(reservationId, reservation.getId());
            Assert.assertEquals(BookingStatus.PickUp, reservation.getBookingStatus());
        } catch (ParseException e) {
            Assert.fail();
        }
    }

    @Test
    public void testPickUpTwice() {
        try {
            BookingRecordVO vo = BookingRecordVO.builder().address("xxxxxx").age(33).idCardNumber("12313513545")
                    .name("Hunter").licenseNo("3423525324").phoneNo("18026982000")
                    .pickUpDateStr("2022-07-01 13:00:00").returnDateStr("2022-07-12 13:00:00")
                    .personalIdIdType(PersonalIdIdType.PrcId)
                    .build();

            vo.setCarTypeDTO(CarTypeDTO.builder().name("Toyota Camry").brand("Toyota").build());


            Result<CustomerReservationDTO> customerReservationDTOResult = customerReservationController.reserveCar(vo);

            Assert.assertNotNull(customerReservationDTOResult);
            Assert.assertTrue(customerReservationDTOResult.isSuccess());
            Assert.assertNotNull(customerReservationDTOResult.getModel());
            String reservationId = customerReservationDTOResult.getModel().getId();
            Assert.assertNotNull(reservationId);

            SimpleReservationRequest simpleReservationRequest = SimpleReservationRequest.builder()
                    .reservationId(reservationId)
                    .build();
            Result<CustomerReservationDTO> pickUpResult1 =
                    employeeOperationController.confirmPickingUp(simpleReservationRequest);
            Assert.assertNotNull(pickUpResult1);
            Assert.assertTrue(pickUpResult1.isSuccess());
            CustomerReservationDTO reservation = pickUpResult1.getModel();
            Assert.assertNotNull(reservation);
            Assert.assertEquals(reservationId, reservation.getId());
            Assert.assertEquals(BookingStatus.PickUp, reservation.getBookingStatus());

            Result<CustomerReservationDTO> pickUpResult2 =
                    employeeOperationController.confirmPickingUp(simpleReservationRequest);
            Assert.assertNotNull(pickUpResult2);
            Assert.assertFalse(pickUpResult2.isSuccess());
        } catch (ParseException e) {
            Assert.fail();
        }
    }

    @Test
    public void testReturningCarHappyPath() {
        try {
            BookingRecordVO vo = BookingRecordVO.builder().address("xxxxxx").age(33).idCardNumber("12313513545")
                    .name("Hunter").licenseNo("3423525324").phoneNo("18026982000")
                    .pickUpDateStr("2022-07-01 13:00:00").returnDateStr("2022-07-12 13:00:00")
                    .personalIdIdType(PersonalIdIdType.PrcId)
                    .build();

            vo.setCarTypeDTO(CarTypeDTO.builder().name("Toyota Camry").brand("Toyota").build());


            Result<CustomerReservationDTO> customerReservationDTOResult = customerReservationController.reserveCar(vo);

            Assert.assertNotNull(customerReservationDTOResult);
            Assert.assertTrue(customerReservationDTOResult.isSuccess());
            Assert.assertNotNull(customerReservationDTOResult.getModel());
            String reservationId = customerReservationDTOResult.getModel().getId();
            Assert.assertNotNull(reservationId);



            SimpleReservationRequest simpleReservationRequest = SimpleReservationRequest.builder()
                    .reservationId(reservationId)
                    .build();


            Result<CustomerReservationDTO> pickUpResult =
                    employeeOperationController.confirmPickingUp(simpleReservationRequest);
            Assert.assertNotNull(pickUpResult);
            Assert.assertTrue(pickUpResult.isSuccess());


            Result<CustomerReservationDTO> returningCarResult =
                    employeeOperationController.confirmReturning(simpleReservationRequest);
            Assert.assertNotNull(returningCarResult);
            Assert.assertTrue(returningCarResult.isSuccess());
            CustomerReservationDTO reservation = returningCarResult.getModel();
            Assert.assertNotNull(reservation);
            Assert.assertEquals(reservationId, reservation.getId());
            Assert.assertEquals(BookingStatus.Returned, reservation.getBookingStatus());
        } catch (ParseException e) {
            Assert.fail();
        }
    }

    @Test
    public void testReturningCarTwice() {
        try {
            BookingRecordVO vo = BookingRecordVO.builder().address("xxxxxx").age(33).idCardNumber("12313513545")
                    .name("Hunter").licenseNo("3423525324").phoneNo("18026982000")
                    .pickUpDateStr("2022-07-01 13:00:00").returnDateStr("2022-07-12 13:00:00")
                    .personalIdIdType(PersonalIdIdType.PrcId)
                    .build();

            vo.setCarTypeDTO(CarTypeDTO.builder().name("Toyota Camry").brand("Toyota").build());


            Result<CustomerReservationDTO> customerReservationDTOResult = customerReservationController.reserveCar(vo);

            Assert.assertNotNull(customerReservationDTOResult);
            Assert.assertTrue(customerReservationDTOResult.isSuccess());
            Assert.assertNotNull(customerReservationDTOResult.getModel());
            String reservationId = customerReservationDTOResult.getModel().getId();
            Assert.assertNotNull(reservationId);

            SimpleReservationRequest simpleReservationRequest = SimpleReservationRequest.builder()
                    .reservationId(reservationId)
                    .build();

            Result<CustomerReservationDTO> pickUpResult =
                    employeeOperationController.confirmPickingUp(simpleReservationRequest);
            Assert.assertNotNull(pickUpResult);
            Assert.assertTrue(pickUpResult.isSuccess());

            Result<CustomerReservationDTO> returningCarResult =
                    employeeOperationController.confirmReturning(simpleReservationRequest);
            Assert.assertNotNull(returningCarResult);
            Assert.assertTrue(returningCarResult.isSuccess());
            CustomerReservationDTO reservation = returningCarResult.getModel();
            Assert.assertNotNull(reservation);
            Assert.assertEquals(reservationId, reservation.getId());
            Assert.assertEquals(BookingStatus.Returned, reservation.getBookingStatus());

            Result<CustomerReservationDTO> returningCarResult2 =
                    employeeOperationController.confirmReturning(simpleReservationRequest);
            Assert.assertNotNull(returningCarResult2);
            Assert.assertFalse(returningCarResult2.isSuccess());
        } catch (ParseException e) {
            Assert.fail();
        }
    }

    @After
    public void after() {
        System.out.println("test end");
    }

}
