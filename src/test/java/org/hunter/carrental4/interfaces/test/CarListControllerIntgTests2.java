package org.hunter.carrental4.interfaces.test;

import org.hunter.carrental4.HunterCarRental4Application;
import org.hunter.carrental4.application.dto.CarInventoryDTO;
import org.hunter.carrental4.common.model.Result;
import org.hunter.carrental4.domain.repository.CarRepository;
import org.hunter.carrental4.interfaces.CarListController;
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
import java.util.List;

@SpringBootTest(classes = HunterCarRental4Application.class)
@RunWith(SpringRunner.class)
public class CarListControllerIntgTests2 {
    @Resource
    private CarListController controller;

    @MockBean
    private CarRepository carRepository;

    @Before
    public void before() {
        System.out.println("test start");
    }

    @Test
    public void testSearchCarHappyPath() {
        try {
            Mockito.when(carRepository.retrieveCarInventory()).thenThrow(new Exception("test error"));
        } catch (Exception e) {
            Assert.fail();
        }

        Result<List<CarInventoryDTO>> listResult = controller.listInventory(null);
        Assert.assertNotNull(listResult);
        Assert.assertFalse(listResult.isSuccess());
    }



    @After
    public void after() {
        System.out.println("test end");
    }

}
