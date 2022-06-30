package org.hunter.carrental4.application.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.hunter.carrental4.application.assemble.Translatetion;
import org.hunter.carrental4.application.dto.BookingRecordDTO;
import org.hunter.carrental4.application.dto.CarTypeDTO;
import org.hunter.carrental4.application.dto.CustomerReservationDTO;
import org.hunter.carrental4.application.service.CarReservationAppService;
import org.hunter.carrental4.application.service.CustomerAppService;
import org.hunter.carrental4.common.model.Result;
import org.hunter.carrental4.domain.model.entity.BookingRecord;
import org.hunter.carrental4.domain.model.entity.Customer;
import org.hunter.carrental4.domain.model.entity.CustomerReservation;
import org.hunter.carrental4.domain.model.valueobject.CarType;
import org.hunter.carrental4.domain.service.BookingRecordService;
import org.hunter.carrental4.domain.service.CarReservationService;
import org.hunter.carrental4.domain.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CarReservationAppServiceImpl implements CarReservationAppService {

    @Autowired
    private CustomerAppService customerAppService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CarReservationService carReservationService;

    @Autowired
    private BookingRecordService bookingRecordService;



    @Override
    public Result<CustomerReservationDTO> reserveCar(BookingRecordDTO bookingRecordDTO) {

        //TODO: validate bookingRecordDTO

        Result<String> customerIdResult = customerAppService.extractCustomer(bookingRecordDTO);

        if (!customerIdResult.isSuccess()) {
            return Result.fail(customerIdResult);
        }

        String customerId = customerIdResult.getModel();

        // Save customer original input.
        BookingRecord bookingRecord = Translatetion.dto2Model(bookingRecordDTO);
        Result<BookingRecord> bookingRecordResult = bookingRecordService.saveBookingRecord(bookingRecord);
        if (!bookingRecordResult.isSuccess()) {
            return Result.fail(bookingRecordResult);
        }

        String bookingRecordId = bookingRecordResult.getModel().getId();

        // reserve car.
        CarTypeDTO carTypeDTO = bookingRecordDTO.getCarTypeDTO();
        CarType carType = CarType.builder().brand(carTypeDTO.getBrand()).name(carTypeDTO.getName()).build();

        Result<CustomerReservation> customerReservationResult = carReservationService.reserveCar(customerId,
                bookingRecordId, carType, bookingRecordDTO.getPickUpdate(), bookingRecordDTO.getReturnDate());

        if (!customerReservationResult.isSuccess()) {
            return Result.fail(customerReservationResult);
        }

        CustomerReservation customerReservation = customerReservationResult.getModel();
        Result<Customer> customerResult = customerService.retrieveByCustomerId(customerReservation.getCustomerId());
        if (!customerResult.isSuccess()) {
            return Result.fail(customerResult);
        }

        return Result.success(Translatetion.model2Dto(customerReservation, customerResult.getModel()));
    }

    @Override
    public Result<CustomerReservationDTO> cancel(String reservationId) {
        if(StringUtils.isBlank(reservationId)){
            return Result.fail("reservationId is blak", "");
        }

        Result<CustomerReservation> cancelResult = carReservationService.cancel(reservationId);
        if(!cancelResult.isSuccess()){
            return Result.fail(cancelResult);
        }

        CustomerReservation customerReservation = cancelResult.getModel();

        Result<Customer> customerResult = customerService.retrieveByCustomerId(customerReservation.getCustomerId());
        if (!customerResult.isSuccess()) {
            return Result.fail(customerResult);
        }

        return Result.success(Translatetion.model2Dto(customerReservation, customerResult.getModel()));
    }

    @Override
    public Result<Collection<CustomerReservationDTO>> listAll() {
        Result<Collection<CustomerReservation>> collectionResult = carReservationService.listAll();
        if (!collectionResult.isSuccess()) {
            return Result.fail(collectionResult);
        }

        Result<Map<String, Customer>> mapResult = customerService.listAll();
        if (!mapResult.isSuccess()) {
            return Result.fail(mapResult);
        }

        Map<String, Customer> customerMap = mapResult.getModel();

        Collection<CustomerReservation> customerReservations = collectionResult.getModel();
        List<CustomerReservationDTO> customerReservationDTOS = customerReservations.stream()
                .map(customerReservation -> {

                    Customer customer = customerMap.get(customerReservation.getCustomerId());
                    CustomerReservationDTO customerReservationDTO = Translatetion.model2Dto(customerReservation, customer);

                    return customerReservationDTO;
                }).collect(Collectors.toList());

        return Result.success(customerReservationDTOS);
    }

}
