package org.hunter.carrental4.application.service;

import org.hunter.carrental4.application.model.dto.BookingRecordDTO;
import org.hunter.carrental4.application.model.dto.CustomerReservationDTO;
import org.hunter.carrental4.common.model.Result;

public interface CarReservationAppService {

    Result<CustomerReservationDTO> reserveCar(BookingRecordDTO bookingRecordDTO);

    Result<CustomerReservationDTO> returnCar(String reservationId);

    Result<CustomerReservationDTO> pickUpCar(String reservationId, Object existingDamage);

    Result<CustomerReservationDTO> cancel(String reservationId);
}
