package org.hunter.carrental4.application.service;

import org.hunter.carrental4.application.model.dto.CarInventoryDTO;
import org.hunter.carrental4.common.model.Result;

import java.util.List;

public interface CarSearchAppService {

    Result<List<CarInventoryDTO>> listAvailableCars(String brand);
}
