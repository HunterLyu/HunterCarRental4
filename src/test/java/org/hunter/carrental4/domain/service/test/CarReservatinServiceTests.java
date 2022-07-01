package org.hunter.carrental4.domain.service.test;

import org.apache.commons.lang3.time.DateUtils;
import org.hunter.carrental4.HunterCarRental4Application;
import org.hunter.carrental4.common.model.Result;
import org.hunter.carrental4.common.model.enums.BookingStatus;
import org.hunter.carrental4.domain.model.entity.CustomerReservation;
import org.hunter.carrental4.domain.model.valueobject.CarType;
import org.hunter.carrental4.domain.service.CarReservationService;
import org.hunter.carrental4.domain.service.impl.CarReservationServiceImpl;
import org.hunter.carrental4.infrastructure.repository.impl.CarRepositoryLocalImpl;
import org.hunter.carrental4.infrastructure.repository.impl.ReservationRepositoryLocalImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

@SpringBootTest(classes = HunterCarRental4Application.class)
@RunWith(SpringRunner.class)
public class CarReservatinServiceTests {
    @Resource
    private CarReservationService carReservationService;

    @Resource
    private CarRepositoryLocalImpl carRepository;

    @Resource
    private ReservationRepositoryLocalImpl reservationRepository;


    @InjectMocks
    private CarReservationServiceImpl carReservationServiceM;

    @Mock
    private CarRepositoryLocalImpl carRepositoryM;

    @Before
    public void before() {
        System.out.println("test start");
        carRepository.initialCars();
        reservationRepository.initData();
    }

    @Test
    public void testReserveCarHappyPath() {
        CarType carType = CarType.builder().name("BMW 650").brand("BMW").build();
        Date startDate = new Date();
        Date endDate = DateUtils.addDays(startDate, 5);

        Result<CustomerReservation> customerReservationResult = carReservationService.reserveCar("111", "222",
                carType, startDate,
                endDate);
        Assert.assertNotNull(customerReservationResult);
        Assert.assertTrue(customerReservationResult.isSuccess());
        CustomerReservation model = customerReservationResult.getModel();
        Assert.assertNotNull(model);
        Assert.assertNotNull(model.getId());
        Assert.assertEquals("111", model.getCustomerId());
        Assert.assertEquals(BookingStatus.New, model.getBookingStatus());
    }

    @Test
    public void testReturningCarOverTime() {
        CarType carType = CarType.builder().name("BMW 650").brand("BMW").build();
        Date startDate = new Date();
        startDate = DateUtils.addDays(startDate, -5);
        Date endDate = DateUtils.addDays(startDate, 3);

        Result<CustomerReservation> customerReservationResult = carReservationService.reserveCar("111", "222",
                carType, startDate,
                endDate);
        Assert.assertNotNull(customerReservationResult);
        Assert.assertTrue(customerReservationResult.isSuccess());
        CustomerReservation model = customerReservationResult.getModel();
        Assert.assertNotNull(model);
        Assert.assertNotNull(model.getId());
        Assert.assertEquals("111", model.getCustomerId());
        Assert.assertEquals(BookingStatus.New, model.getBookingStatus());

        Result<CustomerReservation> pickUpCarResult = carReservationService.pickUpCar(model.getId(),
                "emp111", "windows broken");
        Assert.assertNotNull(pickUpCarResult);
        Assert.assertTrue(pickUpCarResult.isSuccess());

        Result<CustomerReservation> returnCarResult = carReservationService.returnCar(model.getId(),
                "emp111", "windows broken");
        Assert.assertNotNull(returnCarResult);
        Assert.assertTrue(returnCarResult.isSuccess());
        CustomerReservation reservation = returnCarResult.getModel();
        Assert.assertNotNull(reservation);
        Assert.assertNotNull(reservation.getId());
        Assert.assertNotNull(model.getAdditionalPrice());
        Assert.assertTrue(model.getAdditionalPrice() > 0);
    }


    @Test
    public void testRepositoryThrowException() {
        try {
            Mockito.when(carRepositoryM.retrieveCarInventory())
                    .thenThrow(new Exception("test error"));

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            CarType carType = CarType.builder().name("BMW 650").brand("BMW").build();
            Date startDate = new Date();
            Date endDate = DateUtils.addDays(startDate, 5);

            Result<CustomerReservation> customerReservationResult = carReservationServiceM.reserveCar("111", "222",
                    carType, startDate,
                    endDate);
            Assert.assertNotNull(customerReservationResult);
            Assert.assertFalse(customerReservationResult.isSuccess());

        } catch (Exception e) {
            Assert.fail();
        }
    }

    @After
    public void after() {
        System.out.println("test end");
    }

}
