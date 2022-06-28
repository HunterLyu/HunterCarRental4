package org.hunter.carrental4.domain.repository;

import org.hunter.carrental4.domain.model.Car;
import org.hunter.carrental4.domain.model.CarId;

import java.util.Collection;

public interface CarRepository {
    Collection<Car> retrieveCars();

    Car retrieveById(CarId carId);
}
