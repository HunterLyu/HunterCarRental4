package org.hunter.carrental4.domain.service;

import org.hunter.carrental4.common.model.Result;
import org.hunter.carrental4.domain.model.entity.BookingRecord;

import java.util.Map;

public interface BookingRecordService {
    Result<BookingRecord> saveBookingRecord(BookingRecord bookingRecord);

    Result<Map<String, BookingRecord>> retrieveBookingRecord();
}
