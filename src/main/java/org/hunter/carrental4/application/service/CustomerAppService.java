package org.hunter.carrental4.application.service;

import org.hunter.carrental4.application.model.dto.BookingRecordDTO;
import org.hunter.carrental4.common.model.Result;

public interface CustomerAppService {

    Result<String> extractCustomer(BookingRecordDTO bookingRecordDTO);
}
