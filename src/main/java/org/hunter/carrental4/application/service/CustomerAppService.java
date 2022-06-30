package org.hunter.carrental4.application.service;

import org.hunter.carrental4.application.dto.BookingRecordDTO;
import org.hunter.carrental4.common.model.Result;

public interface CustomerAppService {

    /**
     * Find any existing customer or save new customer
     * @param bookingRecordDTO
     * @return customerId
     */
    Result<String> extractCustomer(BookingRecordDTO bookingRecordDTO);
}
