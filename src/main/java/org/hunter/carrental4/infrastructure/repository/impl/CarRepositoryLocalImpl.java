package org.hunter.carrental4.infrastructure.repository.impl;

import org.hunter.carrental4.domain.model.entity.Car;
import org.hunter.carrental4.common.model.enums.CarStatus;
import org.hunter.carrental4.common.model.enums.PriceType;
import org.hunter.carrental4.domain.model.entity.CarInventory;
import org.hunter.carrental4.domain.model.valueobject.RentalPricePlan;
import org.hunter.carrental4.domain.model.valueobject.CarType;
import org.hunter.carrental4.domain.repository.CarRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * Using the local cache to store the data instead of DB.
 */
@Repository
public class CarRepositoryLocalImpl implements CarRepository {

    //TODO: replace with DB implementation
    private static Map<String, Car> cars = new HashMap<>();
    private static Map<CarType, CarInventory> carInventory = new HashMap<>();

    /**
     * initial local DB
     */
    @PostConstruct
    public void initialCars() {

        CarType toyotaCamry = CarType.builder().name("Toyota Camry").brand("Toyota").build();

        Car camry1 = Car.builder().carId("1")
                .carType(toyotaCamry)
                .carNumber("No.1111").status(CarStatus.Available).condition("new").build();
        Car camry2 = Car.builder().carId("2")
                .carType(toyotaCamry)
                .carNumber("No.2222").status(CarStatus.Available).condition("old").build();

        cars.put(camry1.getCarId(), camry1);
        cars.put(camry2.getCarId(), camry2);

        List<Car> part1 = new ArrayList<>();
        part1.add(camry1);
        part1.add(camry2);

        RentalPricePlan camryPricePlan = RentalPricePlan.builder().priceType(PriceType.PerDayPricing).priceUnit(300.00).currency("RMB").build();

        carInventory.put(toyotaCamry, CarInventory.builder().availableAmount(part1.size()).carType(toyotaCamry).rentalPricePlan(camryPricePlan).build());


        CarType bmw650 = CarType.builder().name("BMW 650").brand("BMW").build();

        Car bmw650_1 = Car.builder().carId("3").carType(bmw650)
                .carNumber("No.3333").status(CarStatus.Available).condition("new").build();
        Car bmw650_2 = Car.builder().carId("4").carType(bmw650)
                .carNumber("No.4444").status(CarStatus.Available).condition("old").build();
        cars.put(bmw650_1.getCarId(), bmw650_1);
        cars.put(bmw650_2.getCarId(), bmw650_2);

        List<Car> part2 = new ArrayList<>();
        part2.add(bmw650_1);
        part2.add(bmw650_2);

        RentalPricePlan Bmw650PricePlan = RentalPricePlan.builder().priceType(PriceType.PerDayPricing).priceUnit(1000.00).currency("RMB").build();

        carInventory.put(bmw650, CarInventory.builder().availableAmount(part2.size()).carType(bmw650).rentalPricePlan(Bmw650PricePlan).build());
    }


    @Override
    public Map<CarType, CarInventory> retrieveCarInventory() throws Exception {
        return carInventory;
    }

    @Override
    public Map<String, Car> retrieveCars() throws Exception {
        return cars;
    }

    @Override
    public void updateCarInventory(CarInventory carInventory) throws Exception {

    }

    @Override
    public void updateCars(Map<String, Car> cars) throws Exception {
        this.cars = cars;
    }

    @Override
    public Car retrieveById(String carId) throws Exception {
        return cars.get(carId);
    }

    @Override
    public void saveCar(Car car) throws Exception {

    }
}
