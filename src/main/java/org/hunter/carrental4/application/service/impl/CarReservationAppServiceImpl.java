package org.hunter.carrental4.application.service.impl;

import org.hunter.carrental4.application.model.dto.BookingRecordDTO;
import org.hunter.carrental4.application.model.dto.CustomerReservationDTO;
import org.hunter.carrental4.application.model.dto.SimpleCarInfo;
import org.hunter.carrental4.application.service.CarReservationAppService;
import org.hunter.carrental4.application.service.CustomerAppService;
import org.hunter.carrental4.common.model.Result;
import org.hunter.carrental4.domain.model.entity.BookingRecord;
import org.hunter.carrental4.domain.model.entity.CustomerReservation;
import org.hunter.carrental4.domain.model.vo.CarType;
import org.hunter.carrental4.domain.service.BookingRecordService;
import org.hunter.carrental4.domain.service.CarReservationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class CarReservationAppServiceImpl implements CarReservationAppService {

    @Autowired
    private CustomerAppService customerAppService;

    @Autowired
    private CarReservationService carReservationService;

    @Autowired
    private BookingRecordService bookingRecordService;

    private CustomerReservationDTO do2DTO(CustomerReservation customerReservation) {
        if (null == customerReservation) {
            return null;
        }
        CustomerReservationDTO customerReservationDTO = CustomerReservationDTO.builder()
                .driverId(customerReservation.getDriverId())
                .customerId(customerReservation.getCustomerId())
                .employeeId(customerReservation.getEmployeeId())
                .bookingStatus(customerReservation.getBookingStatus())
                .rentDate(customerReservation.getRentDate())
                .oriReturnDate(customerReservation.getOriReturnDate())
                .actualReturnDate(customerReservation.getActualReturnDate())
                .id(customerReservation.getId())
                .carId(customerReservation.getCarId())
                .originalPrice(customerReservation.getOriginalPrice())
                .additionalPrice(customerReservation.getAdditionalPrice()).build();

        return customerReservationDTO;
    }

    private BookingRecord toBookingRecord(BookingRecordDTO bookingRecordDTO) {
        if (null == bookingRecordDTO) {
            return null;
        }
        BookingRecord bookingRecord = BookingRecord.builder().build();
        BeanUtils.copyProperties(bookingRecordDTO, bookingRecord);

        CarType carType = CarType.builder().build();
        SimpleCarInfo simpleCarInfo = bookingRecordDTO.getSimpleCarInfo();
        if (simpleCarInfo != null) {
            carType.setBrand(simpleCarInfo.getBrand());
            carType.setName(simpleCarInfo.getName());
        }

        bookingRecord.setCarType(carType);

        return bookingRecord;
    }

    @Override
    public Result<CustomerReservationDTO> reserveCar(BookingRecordDTO bookingRecordDTO) {

        //TODO: validate bookingRecordDTO

        Result<String> customerIdResult = customerAppService.extractCustomer(bookingRecordDTO);

        if (!customerIdResult.isSuccess()) {
            return Result.fail(customerIdResult);
        }

        String customerId = customerIdResult.getModel();

        Result<BookingRecord> bookingRecordResult = bookingRecordService.saveBookingRecord(toBookingRecord(bookingRecordDTO));
        if (!bookingRecordResult.isSuccess()) {
            return Result.fail(bookingRecordResult);
        }

        String bookingRecordId = bookingRecordResult.getModel().getBookingRecordId();

        SimpleCarInfo carTypeDTO = bookingRecordDTO.getSimpleCarInfo();
        CarType carType = CarType.builder().brand(carTypeDTO.getBrand()).name(carTypeDTO.getName()).build();

        Result<CustomerReservation> customerReservationResult = carReservationService.reserveCar(customerId,
                bookingRecordId, carType, bookingRecordDTO.getRentDate(), bookingRecordDTO.getOriReturnDate());

        if (!customerReservationResult.isSuccess()) {
            return Result.fail(customerReservationResult);
        }


        return Result.success(do2DTO(customerReservationResult.getModel()));
    }

    @Override
    public Result<CustomerReservationDTO> returnCar(String reservationId) {
        return null;
    }

    @Override
    public Result<CustomerReservationDTO> pickUpCar(String reservationId, Object existingDamage) {
        return null;
    }

    @Override
    public Result<CustomerReservationDTO> cancel(String reservationId) {
        return null;
    }
}
