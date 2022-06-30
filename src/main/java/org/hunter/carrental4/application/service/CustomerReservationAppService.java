package org.hunter.carrental4.application.service;

import org.hunter.carrental4.application.dto.BookingRecordDTO;
import org.hunter.carrental4.application.dto.CustomerReservationDTO;
import org.hunter.carrental4.common.model.Result;

import java.util.Collection;

/**
 * car reservation operation.
 */
public interface CustomerReservationAppService {

    Result<CustomerReservationDTO> reserveCar(BookingRecordDTO bookingRecordDTO);

    Result<CustomerReservationDTO> cancel(String reservationId);

    Result<Collection<CustomerReservationDTO>> listAll();
}
