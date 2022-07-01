package org.hunter.carrental4.domain.service.impl;

import org.hunter.carrental4.common.model.Result;
import org.hunter.carrental4.common.model.enums.BookingStatus;
import org.hunter.carrental4.common.model.enums.CarStatus;
import org.hunter.carrental4.domain.model.entity.*;
import org.hunter.carrental4.domain.model.valueobject.CarType;
import org.hunter.carrental4.domain.repository.CarRepository;
import org.hunter.carrental4.domain.repository.ReservationRepository;
import org.hunter.carrental4.domain.service.CarReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Service
public class CarReservationServiceImpl implements CarReservationService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public Result<CustomerReservation> reserveCar(String customerId, String bookingRecordId, CarType carType,
                                                  Date pickUpDate, Date returnDate) {
        CustomerReservation customerReservation = new CustomerReservation();
        customerReservation.setId(UUID.randomUUID().toString());
        customerReservation.setBookingRecordId(bookingRecordId);
        customerReservation.setBookingDate(new Date());
        try {
            //TODO: validate parameters

            // Retrieve customer info
            customerReservation.setCustomerId(customerId);
            customerReservation.setDriverId(customerId);

            //TODO: distributed lock to deduct inventory for a car type per UUID value.

            //TODO: star Spring transaction

            // Find available car
            Map<String, Car> carIdCarMap = carRepository.retrieveCars();
            boolean hasInventory = false;
            Car reserveCar = null;
            for (Car car : carIdCarMap.values()) {
                if (car.getCarType().equals(carType) && CarStatus.Available.equals(car.getStatus())) {
                    hasInventory = true;
                    car.setStatus(CarStatus.Occupied);
                    reserveCar = car;
                    break;
                }
            }

            if (!hasInventory) {
                return Result.fail("Not enough car inventory for rental", "");
            }

            customerReservation.setCarId(reserveCar.getCarId());

            // Update car inventory
            Map<CarType, CarInventory> carTypeCarInventoryMap = carRepository.retrieveCarInventory();
            CarInventory carInventory = carTypeCarInventoryMap.get(carType);


            carInventory.setAvailableAmount(carInventory.getAvailableAmount().intValue() - 1);

            //TODO: end Spring transaction

            //TODO: distributed unlock  to deduct inventory for a car type per UUID value.

            customerReservation.setRentalPricePlan(carInventory.getRentalPricePlan());
            customerReservation.setPickUpDate(pickUpDate);
            customerReservation.setReturnDate(returnDate);

            customerReservation.calculateOriPrice();
            customerReservation.setBookingStatus(BookingStatus.New);

            reservationRepository.saveReservation(customerReservation);

            //TODO: generate a original payment

            //TODO: Set up an schedule plan to cancel the reservation after its expired without pay.
        } catch (Exception e) {
            //TODO: distributed unlock to deduct inventory for a car type per UUID value
            return Result.fail("call repository throw exception", "");
        }

        return Result.success(customerReservation);
    }

    @Override
    public Result<CustomerReservation> returnCar(String reservationId, String employeeId, String newDamage) {
        CustomerReservation customerReservation = null;
        try {
            //TODO: validate parameters

            customerReservation = reservationRepository.retrieveById(reservationId);

            if(!customerReservation.getBookingStatus().equals(BookingStatus.PickUp)){
                return Result.fail("Cannot return car since customer doesn't occupy the car", "");
            }

            //TODO: star Spring transaction

            customerReservation.setActualReturnDate(new Date());
            customerReservation.calculateAdditionalPrice();
            if (customerReservation.getAdditionalPrice() != null && customerReservation.getAdditionalPrice() > 0) {
                //TODO: generate an additional payment
            }

            customerReservation.setBookingStatus(BookingStatus.Returned);
            customerReservation.setReturnEmployeeId(employeeId);

            // Car to maintain
            Car car = carRepository.retrieveById(customerReservation.getCarId());
            car.setStatus(CarStatus.Available);
            carRepository.saveCar(car);


            // Update car inventory
            Map<CarType, CarInventory> carTypeCarInventoryMap = carRepository.retrieveCarInventory();
            CarInventory carInventory = carTypeCarInventoryMap.get(car.getCarType());
            carInventory.setAvailableAmount(carInventory.getAvailableAmount() + 1);

            reservationRepository.saveReservation(customerReservation);

            //TODO: end Spring transaction
        } catch (Exception e) {
            return Result.fail("call repository throw exception", "");
        }

        return Result.success(customerReservation);
    }

    @Override
    public Result<CustomerReservation> pickUpCar(String reservationId, String employeeId, String existingDamage) {
        CustomerReservation customerReservation = null;
        try {
            //TODO: validate parameters

            customerReservation = reservationRepository.retrieveById(reservationId);

            //TODO: record any car existing damage

            if (customerReservation.getBookingStatus().getCode() >= BookingStatus.PickUp.getCode()) {
                return Result.fail("car already picked up or reservation is closed.", "");
            }

            customerReservation.setBookingStatus(BookingStatus.PickUp);
            customerReservation.setPickUpEmployeeId(employeeId);
            customerReservation.setActualPickUpDate(new Date());

            reservationRepository.saveReservation(customerReservation);
        } catch (Exception e) {
            return Result.fail("call repository throw exception", "");
        }

        return Result.success(customerReservation);
    }

    @Override
    public Result<CustomerReservation> cancel(String reservationId) {
        CustomerReservation customerReservation = null;
        try {
            //TODO: validate parameters

            customerReservation = reservationRepository.retrieveById(reservationId);

            BookingStatus bookingStatus = customerReservation.getBookingStatus();

            if (bookingStatus.getCode() >= BookingStatus.PickUp.getCode()) {
                return Result.fail("Cannot cancel the reservation since the already picked up or closed.", "");
            }

            //TODO: calculate any liquidated damages

            //TODO: propose refund payment

            //TODO: star Spring transaction

            customerReservation.setBookingStatus(BookingStatus.Cancelled);

            // Car to maintain
            Car car = carRepository.retrieveById(customerReservation.getCarId());
            car.setStatus(CarStatus.Available);
            carRepository.saveCar(car);


            // Update car inventory
            Map<CarType, CarInventory> carTypeCarInventoryMap = carRepository.retrieveCarInventory();
            CarInventory carInventory = carTypeCarInventoryMap.get(car.getCarType());
            carInventory.setAvailableAmount(carInventory.getAvailableAmount() + 1);

            //TODO: end Spring transaction

            reservationRepository.saveReservation(customerReservation);
        } catch (Exception e) {
            return Result.fail("call repository throw exception", "");
        }

        return Result.success(customerReservation);
    }

    @Override
    public Result<Collection<CustomerReservation>> listAll() {
        try {

            Collection<CustomerReservation> customerReservations = reservationRepository.retrieveAll();

            return Result.success(customerReservations);
        } catch (Exception e) {
            return Result.fail("call repository throw exception", "");
        }
    }
}
