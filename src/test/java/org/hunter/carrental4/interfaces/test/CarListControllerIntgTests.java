package org.hunter.carrental4.interfaces.test;

import org.hunter.carrental4.HunterCarRental4Application;
import org.hunter.carrental4.application.dto.CarInventoryDTO;
import org.hunter.carrental4.common.model.Result;
import org.hunter.carrental4.infrastructure.repository.impl.CarRepositoryLocalImpl;
import org.hunter.carrental4.interfaces.CarListController;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest(classes = HunterCarRental4Application.class)
@RunWith(SpringRunner.class)
public class CarListControllerIntgTests {
    @Resource
    private CarListController controller;

    @Resource
    private CarRepositoryLocalImpl carRepository;

    @Before
    public void before() {
        System.out.println("test start");
        carRepository.initialCars();
    }

    @Test
    public void testSearchCarHappyPath() {
        Result<List<CarInventoryDTO>> listResult = controller.listInventory(null);
        Assert.assertNotNull(listResult);
        Assert.assertTrue(listResult.isSuccess());
        List<CarInventoryDTO> carInventoryDTOS = listResult.getModel();
        Assert.assertNotNull(carInventoryDTOS);
        Assert.assertEquals(2, carInventoryDTOS.size());

        Result<List<CarInventoryDTO>> listResultWithBrand = controller.listInventory("BMW");
        Assert.assertNotNull(listResultWithBrand);
        Assert.assertTrue(listResultWithBrand.isSuccess());
        List<CarInventoryDTO> carInventoryDTOSWithBrand = listResultWithBrand.getModel();
        Assert.assertNotNull(carInventoryDTOSWithBrand);
        Assert.assertEquals(1, carInventoryDTOSWithBrand.size());
    }

//    @Test
//    public void testRepositoryThrowException() {
//
//
//        Result<List<CarInventoryDTO>> listResult = controller.listInventory(null);
//        Assert.assertNotNull(listResult);
//        Assert.assertTrue(listResult.isSuccess());
//        List<CarInventoryDTO> carInventoryDTOS = listResult.getModel();
//        Assert.assertNotNull(carInventoryDTOS);
//        Assert.assertEquals(2, carInventoryDTOS.size());
//    }

    @After
    public void after() {
        System.out.println("test end");
    }

}
