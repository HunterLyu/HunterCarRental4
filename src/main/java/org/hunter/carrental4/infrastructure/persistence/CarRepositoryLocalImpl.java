package org.hunter.carrental4.infrastructure.persistence;

import org.hunter.carrental4.domain.model.Car;
import org.hunter.carrental4.domain.model.CarId;
import org.hunter.carrental4.domain.repository.CarRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * Using the local cache to store the data instead of DB.
 */
@Repository
public class CarRepositoryLocalImpl implements CarRepository {
    //TODO: receplace with DB implementation
    private static Map<CarId, Car> cars = new HashMap<>();

    /**
     * initial local DB
     */
    @PostConstruct
    public void initialCars(){
        cars.put(new CarId(), new Car());
    }


    @Override
    public Collection<Car> retrieveCars() {
        return cars.values();
    }

    @Override
    public Car retrieveById(CarId carId) {
        return cars.get(carId);
    }
}
