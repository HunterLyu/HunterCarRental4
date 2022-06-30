package org.hunter.carrental4.domain.service;

import org.hunter.carrental4.common.model.Result;
import org.hunter.carrental4.domain.model.entity.CustomerReservation;
import org.hunter.carrental4.domain.model.valueobject.CarType;

import java.util.Collection;
import java.util.Date;

public interface CarReservationService {

    Result<CustomerReservation> reserveCar(String customerId, String bookingRecordId, CarType carType, Date rentDate, Date returnDate);

    Result<CustomerReservation> returnCar(String reservationId, String employeeId, String newDamage);

    Result<CustomerReservation> pickUpCar(String reservationId, String employeeId, String existingDamage);

    Result<CustomerReservation> cancel(String reservationId);

    Result<Collection<CustomerReservation>> listAll();
}
