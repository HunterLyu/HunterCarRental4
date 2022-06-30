package org.hunter.carrental4.domain.service.impl;

import org.hunter.carrental4.common.model.Result;
import org.hunter.carrental4.domain.model.entity.BookingRecord;
import org.hunter.carrental4.domain.repository.BookingRecordRepository;
import org.hunter.carrental4.domain.service.BookingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class BookingRecordServiceImpl implements BookingRecordService {

    @Autowired
    private BookingRecordRepository bookingRecordRepository;

    @Override
    public Result<BookingRecord> saveBookingRecord(BookingRecord bookingRecord) {
        try {
            if (bookingRecord == null) {
                return Result.fail("invalid booking record", "");
            }

            //TODO: validate parameter

            bookingRecord.setId(UUID.randomUUID().toString());

            bookingRecordRepository.saveBookingRecord(bookingRecord);

        } catch (Exception e) {
            return Result.fail("call repository throw exception", "");
        }

        return Result.success(bookingRecord);
    }

    @Override
    public Result<Map<String, BookingRecord>> retrieveBookingRecord() {
        try {
            Map<String, BookingRecord> customerIdBookingRecordMap = bookingRecordRepository.retrieveBookingRecord();

            return Result.success(customerIdBookingRecordMap);
        } catch (Exception e) {
            return Result.fail("call repository throw exception", "");
        }
    }
}
