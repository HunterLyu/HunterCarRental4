package org.hunter.carrental4.application.service.impl;

import org.hunter.carrental4.application.dto.CarInventoryDTO;
import org.hunter.carrental4.application.service.CarSearchAppService;
import org.hunter.carrental4.common.model.Result;
import org.hunter.carrental4.domain.model.entity.CarInventory;
import org.hunter.carrental4.domain.service.CarSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarSearchServiceAppImpl implements CarSearchAppService {

    @Autowired
    private CarSearchService carSearchService;


    @Override
    public Result<List<CarInventoryDTO>> listAvailableCars(String brand) {

        Result<List<CarInventory>> carInventoryResult = carSearchService.listAvailableCars(brand);

        if(!carInventoryResult.isSuccess()){
            return Result.fail(carInventoryResult);
        }

        List<CarInventory> models = carInventoryResult.getModel();

        List<CarInventoryDTO> carInventoryDTOList = models.stream().map(model -> {
            CarInventoryDTO dto = CarInventoryDTO.builder().build();
            dto.fromDomainObj(model);
            return dto;
        }).collect(Collectors.toList());

        return Result.success(carInventoryDTOList);
    }
}
