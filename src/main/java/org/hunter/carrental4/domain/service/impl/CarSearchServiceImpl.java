package org.hunter.carrental4.domain.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.hunter.carrental4.common.model.Result;
import org.hunter.carrental4.domain.model.entity.CarInventory;
import org.hunter.carrental4.domain.model.vo.CarType;
import org.hunter.carrental4.domain.repository.CarRepository;
import org.hunter.carrental4.domain.service.CarSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CarSearchServiceImpl implements CarSearchService {

    @Autowired
    private CarRepository carRepository;


    @Override
    public Result<List<CarInventory>> listAvailableCars(String brand) {

        Map<CarType, CarInventory> carInventories = null;
        List<CarInventory> inventoryLst = null;
        try {
            carInventories = carRepository.retrieveCarInventory();

            inventoryLst = carInventories.values().stream().filter(carInventory -> {
                boolean hasInventory = carInventory.getAvailableAmount() != null && carInventory.getAvailableAmount() > 0;

                if (StringUtils.isNotBlank(brand)) {
                    boolean metchBrand = carInventory.getCarType() != null
                            && brand.equalsIgnoreCase(carInventory.getCarType().getBrand());

                    return hasInventory && metchBrand;
                } else {
                    return hasInventory;
                }
            }).collect(Collectors.toList());

        } catch (Exception e) {
            return Result.fail("call repository throw exception", "");
        }

        return Result.success(inventoryLst);
    }
}
