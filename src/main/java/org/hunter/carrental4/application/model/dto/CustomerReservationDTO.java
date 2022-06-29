package org.hunter.carrental4.application.model.dto;

import lombok.Builder;
import lombok.Data;
import org.hunter.carrental4.common.model.enums.BookingStatus;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
public class CustomerReservationDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 6917143466899627749L;
    private String id;

    private String customerId;

    private String employeeId;

    private String carId;

    private RentalPricePlanDTO pricePlan;

    private String driverId;


    private Double originalPrice;
    private Double additionalPrice;

    private Date pickUpDate;

    private Date rentDate;
    private Date oriReturnDate;
    private Date actualReturnDate;

    private BookingStatus bookingStatus;

    private String bookingRecordId;
}
