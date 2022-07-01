package org.hunter.carrental4.domain.service.test;

import org.hunter.carrental4.HunterCarRental4Application;
import org.hunter.carrental4.common.model.Result;
import org.hunter.carrental4.domain.model.entity.CarInventory;
import org.hunter.carrental4.domain.service.CarSearchService;
import org.hunter.carrental4.domain.service.impl.CarSearchServiceImpl;
import org.hunter.carrental4.infrastructure.repository.impl.CarRepositoryLocalImpl;
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
import java.util.List;

@SpringBootTest(classes = HunterCarRental4Application.class)
@RunWith(SpringRunner.class)
public class CarSearchServiceTests {
    @Resource
    private CarSearchService carSearchService;

    @Resource
    private CarRepositoryLocalImpl carRepository;


    @InjectMocks
    private CarSearchServiceImpl carSearchServiceM;

    @Mock
    private CarRepositoryLocalImpl carRepositoryM;

    @Before
    public void before() {
        System.out.println("test start");
        carRepository.initialCars();
    }

    @Test
    public void testSearchCarHappyPath() {
        Result<List<CarInventory>> listResult = carSearchService.listAvailableCars(null);
        Assert.assertNotNull(listResult);
        Assert.assertTrue(listResult.isSuccess());
        List<CarInventory> carInventoryDTOS = listResult.getModel();
        Assert.assertNotNull(carInventoryDTOS);
        Assert.assertEquals(2, carInventoryDTOS.size());

        Result<List<CarInventory>> listResultWithBrand = carSearchService.listAvailableCars("BMW");
        Assert.assertNotNull(listResultWithBrand);
        Assert.assertTrue(listResultWithBrand.isSuccess());
        List<CarInventory> carInventoryDTOSWithBrand = listResultWithBrand.getModel();
        Assert.assertNotNull(carInventoryDTOSWithBrand);
        Assert.assertEquals(1, carInventoryDTOSWithBrand.size());
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
            Result<List<CarInventory>> listResult = carSearchServiceM.listAvailableCars(null);
            Assert.assertNotNull(listResult);
            Assert.assertFalse(listResult.isSuccess());

        } catch (Exception e) {
            Assert.fail();
        }
    }

    @After
    public void after() {
        System.out.println("test end");
    }

}
