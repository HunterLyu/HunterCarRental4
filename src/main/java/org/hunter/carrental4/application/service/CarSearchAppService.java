package org.hunter.carrental4.application.service;

import org.hunter.carrental4.application.dto.CarInventoryDTO;
import org.hunter.carrental4.common.model.Result;

import java.util.List;

public interface CarSearchAppService {

    /**
     * list available car inventory.
     *
     * @param brand
     * @return a list of car inventory info.
     */
    Result<List<CarInventoryDTO>> listAvailableCars(String brand);
}
