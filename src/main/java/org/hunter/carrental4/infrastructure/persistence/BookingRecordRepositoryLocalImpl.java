package org.hunter.carrental4.infrastructure.persistence;

import org.hunter.carrental4.domain.model.entity.BookingRecord;
import org.hunter.carrental4.domain.repository.BookingRecordRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class BookingRecordRepositoryLocalImpl implements BookingRecordRepository {
    private static Map<String, BookingRecord> records = new HashMap<>();

    @Override
    public void saveBookingRecord(BookingRecord bookingRecord) throws Exception {
        records.put(bookingRecord.getCustomerId(), bookingRecord);
    }

    @Override
    public Map<String, BookingRecord> retrieveBookingRecord() throws Exception {
        return records;
    }
}
