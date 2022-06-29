package org.hunter.carrental4.domain.repository;

import org.hunter.carrental4.domain.model.entity.Car;
import org.hunter.carrental4.domain.model.entity.CarInventory;
import org.hunter.carrental4.domain.model.vo.CarType;

import java.util.Map;

public interface CarRepository {
    Map<CarType, CarInventory> retrieveCarInventory() throws Exception;

    Car retrieveById(String carId) throws Exception;

    void saveCar(Car car) throws Exception;

    Map<String, Car> retrieveCars() throws Exception;

    void updateCarInventory(CarInventory carInventory) throws Exception;

    void updateCars(Map<String, Car> cars) throws Exception;
}
