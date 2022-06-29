package org.hunter.carrental4.domain.service;

import org.hunter.carrental4.common.model.Result;
import org.hunter.carrental4.domain.model.entity.CarInventory;

import java.util.List;

public interface CarSearchService {

    Result<List<CarInventory>> listAvailableCars(String brand);
}
