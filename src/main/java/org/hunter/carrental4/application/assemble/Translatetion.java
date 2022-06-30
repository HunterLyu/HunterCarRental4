package org.hunter.carrental4.application.assemble;

import org.hunter.carrental4.application.dto.*;
import org.hunter.carrental4.domain.model.entity.BookingRecord;
import org.hunter.carrental4.domain.model.entity.Customer;
import org.hunter.carrental4.domain.model.entity.CustomerReservation;
import org.hunter.carrental4.domain.model.entity.PersonalId;
import org.hunter.carrental4.domain.model.valueobject.CarType;
import org.hunter.carrental4.domain.model.valueobject.RentalPricePlan;
import org.springframework.beans.BeanUtils;

public class Translatetion {
    public static CustomerReservationDTO model2Dto(CustomerReservation customerReservation, Customer customer) {
        if (null == customerReservation) {
            return null;
        }
        CustomerReservationDTO customerReservationDTO = CustomerReservationDTO.builder()
                .driverId(customerReservation.getDriverId())
                .customerId(customerReservation.getCustomerId())
                .pickUpEmployeeId(customerReservation.getPickUpEmployeeId())
                .returnEmployeeId(customerReservation.getReturnEmployeeId())
                .bookingStatus(customerReservation.getBookingStatus())
                .pickUpDate(customerReservation.getPickUpDate())
                .actualPickUpDate(customerReservation.getActualPickUpDate())
                .returnDate(customerReservation.getReturnDate())
                .actualReturnDate(customerReservation.getActualReturnDate())
                .id(customerReservation.getId())
                .carId(customerReservation.getCarId())
                .originalPrice(customerReservation.getOriginalPrice())
                .bookingRecordId(customerReservation.getBookingRecordId())
                .rentalPricePlanDTO(model2Dto(customerReservation.getRentalPricePlan()))
                .additionalPrice(customerReservation.getAdditionalPrice()).build();

        customerReservationDTO.setCustomerDTO(Translatetion.model2Dto(customer));

        return customerReservationDTO;
    }

    private static RentalPricePlanDTO model2Dto(RentalPricePlan rentalPricePlan){
        if (null == rentalPricePlan) {
            return null;
        }
        RentalPricePlanDTO rentalPricePlanDTO = RentalPricePlanDTO.builder().build();
        BeanUtils.copyProperties(rentalPricePlan, rentalPricePlanDTO);

        return rentalPricePlanDTO;
    }

    public static BookingRecord dto2Model(BookingRecordDTO bookingRecordDTO) {
        if (null == bookingRecordDTO) {
            return null;
        }
        BookingRecord bookingRecord = BookingRecord.builder().build();
        BeanUtils.copyProperties(bookingRecordDTO, bookingRecord);

        CarType carType = CarType.builder().build();
        CarTypeDTO carTypeDTO = bookingRecordDTO.getCarTypeDTO();
        if (carTypeDTO != null) {
            carType.setBrand(carTypeDTO.getBrand());
            carType.setName(carTypeDTO.getName());
        }

        bookingRecord.setCarType(carType);

        return bookingRecord;
    }

    public static CustomerDTO model2Dto(Customer customer) {
        if (customer == null) {
            return null;
        }
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);

        PersonalId id = customer.getId();
        if (id != null) {
            customerDTO.setIdCardNumber(id.getNumber());
            customerDTO.setPersonalIdIdType(id.getPersonalIdIdType());
        }

        return customerDTO;
    }
}
