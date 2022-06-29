package org.hunter.carrental4.domain.repository;

import org.hunter.carrental4.domain.model.entity.BookingRecord;

import java.util.Map;

public interface BookingRecordRepository {
    void saveBookingRecord(BookingRecord bookingRecord) throws Exception;

    Map<String, BookingRecord> retrieveBookingRecord() throws Exception;
}
