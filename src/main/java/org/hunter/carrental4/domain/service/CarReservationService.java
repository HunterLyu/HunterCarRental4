package org.hunter.carrental4.domain.service;

import org.hunter.carrental4.common.model.Result;
import org.hunter.carrental4.domain.model.entity.CustomerReservation;
import org.hunter.carrental4.domain.model.vo.CarType;

import java.util.Date;

public interface CarReservationService {

    Result<CustomerReservation> reserveCar(String customerId, String bookingRecordId, CarType carType, Date rentDate, Date returnDate);

    Result<CustomerReservation> returnCar(String reservationId);

    Result<CustomerReservation> pickUpCar(String reservationId, Object existingDamage);

    Result<CustomerReservation> cancel(String reservationId);
}
