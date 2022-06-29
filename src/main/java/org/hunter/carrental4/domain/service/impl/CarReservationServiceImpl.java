package org.hunter.carrental4.domain.service.impl;

import org.hunter.carrental4.common.model.Result;
import org.hunter.carrental4.common.model.enums.BookingStatus;
import org.hunter.carrental4.common.model.enums.CarStatus;
import org.hunter.carrental4.domain.model.entity.*;
import org.hunter.carrental4.domain.model.vo.CarType;
import org.hunter.carrental4.domain.repository.CarRepository;
import org.hunter.carrental4.domain.repository.ReservationRepository;
import org.hunter.carrental4.domain.service.CarReservationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class CarReservationServiceImpl implements CarReservationService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public Result<CustomerReservation> reserveCar(String customerId, String bookingRecordId, CarType carType, Date rentDate, Date returnDate) {
        CustomerReservation customerReservation = new CustomerReservation();
        customerReservation.setId(UUID.randomUUID().toString());
        customerReservation.setBookingRecordId(bookingRecordId);
        customerReservation.setBookingDate(new Date());
        try {
            //TODO: validate parameters

            // Retrieve customer info
//            Customer customer = customerRepository.retrieveById(customerId);
            customerReservation.setCustomerId(customerId);
            customerReservation.setDriverId(customerId);


            // Find available car
            Map<String, Car> carIdCarMap = carRepository.retrieveCars();
            boolean hasInventory = false;
            Car reserveCar = null;
            for (Car car : carIdCarMap.values()) {
                if (car.getCarType().equals(carType) && CarStatus.Available.equals(car.getStatus())) {
                    hasInventory = true;
                    car.setStatus(CarStatus.Occupied);
                    reserveCar = car;
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
            //TODO: Use redis to deduct car inventory and roll back any oversold.

            customerReservation.setRentalPricePlan(carInventory.getRentalPricePlan());
            customerReservation.setRentDate(rentDate);
            customerReservation.setOriReturnDate(returnDate);

            customerReservation.calculateOriPrice();
            customerReservation.setBookingStatus(BookingStatus.New);

            reservationRepository.saveReservation(customerReservation);

            //TODO: generate a original payment
        } catch (Exception e) {
            return Result.fail("call repository throw exception", "");
        }

        return Result.success(customerReservation);
    }

    @Override
    public Result<CustomerReservation> returnCar(String reservationId) {
        CustomerReservation customerReservation = null;
        try {
            //TODO: validate parameters

            customerReservation = reservationRepository.retrieveById(reservationId);
            customerReservation.setActualReturnDate(new Date());
            customerReservation.calculateOriPrice();
            if (customerReservation.getAdditionalPrice() > 0) {
                //TODO: generate an additional payment
            }

            customerReservation.setBookingStatus(BookingStatus.Returned);

            // Car to maintain
            Car car = carRepository.retrieveById(customerReservation.getCarId());
            car.setStatus(CarStatus.Maintain);
            carRepository.saveCar(car);


            // Update car inventory
            Map<CarType, CarInventory> carTypeCarInventoryMap = carRepository.retrieveCarInventory();
            CarInventory carInventory = carTypeCarInventoryMap.get(car.getCarType());
            carInventory.setAvailableAmount(carInventory.getAvailableAmount() + 1);

            //TODO: Use redis to car inventory.

            reservationRepository.saveReservation(customerReservation);
        } catch (Exception e) {
            return Result.fail("call repository throw exception", "");
        }

        return Result.success(customerReservation);
    }

    @Override
    public Result<CustomerReservation> pickUpCar(String reservationId, Object existingDamage) {
        CustomerReservation customerReservation = null;
        try {
            //TODO: validate parameters

            customerReservation = reservationRepository.retrieveById(reservationId);

            //TODO: record any car existing damage

            customerReservation.setBookingStatus(BookingStatus.PickUp);


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
                return Result.fail("Already picked up, cannot cancel the reservation", "");
            }

            //TODO: calculate any liquidated damages
            //TODO: return payment

            customerReservation.setBookingStatus(BookingStatus.Cancelled);

            reservationRepository.saveReservation(customerReservation);
        } catch (Exception e) {
            return Result.fail("call repository throw exception", "");
        }

        return Result.success(customerReservation);
    }
}
