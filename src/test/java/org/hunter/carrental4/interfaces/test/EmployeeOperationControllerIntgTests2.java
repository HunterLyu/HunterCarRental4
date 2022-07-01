package org.hunter.carrental4.interfaces.test;

import org.hunter.carrental4.HunterCarRental4Application;
import org.hunter.carrental4.application.dto.CustomerReservationDTO;
import org.hunter.carrental4.common.model.Result;
import org.hunter.carrental4.domain.repository.ReservationRepository;
import org.hunter.carrental4.infrastructure.repository.impl.CarRepositoryLocalImpl;
import org.hunter.carrental4.interfaces.EmployeeOperationController;
import org.hunter.carrental4.interfaces.viewobject.SimpleReservationRequest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest(classes = HunterCarRental4Application.class)
@RunWith(SpringRunner.class)
public class EmployeeOperationControllerIntgTests2 {
    @Resource
    private EmployeeOperationController employeeOperationController;

    @MockBean
    private ReservationRepository reservationRepository;

    @Resource
    private CarRepositoryLocalImpl carRepository;

    @Before
    public void before() {
        System.out.println("test start");

        carRepository.initialCars();
    }

    @Test
    public void testPickUpCarThrowExceptionBy() {
        try {
            try {
                Mockito.when(reservationRepository.retrieveById(Mockito.anyString())).thenThrow(new Exception("test " +
                        "error"));
            } catch (Exception e) {
                Assert.fail();
            }

            SimpleReservationRequest simpleReservationRequest = SimpleReservationRequest.builder()
                    .reservationId("111")
                    .build();
            Result<CustomerReservationDTO> pickUpResult =
                    employeeOperationController.confirmPickingUp(simpleReservationRequest);
            Assert.assertNotNull(pickUpResult);
            Assert.assertFalse(pickUpResult.isSuccess());
        } catch (Exception e) {
            Assert.fail();
        }
    }


    @After
    public void after() {
        System.out.println("test end");
    }

}
