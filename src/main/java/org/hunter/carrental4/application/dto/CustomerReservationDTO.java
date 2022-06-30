package org.hunter.carrental4.application.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.hunter.carrental4.common.model.enums.BookingStatus;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@ApiModel(description = "Customer reservation data")
public class CustomerReservationDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 6917143466899627749L;

    @ApiModelProperty(value = "ID", required = false)
    private String id;

    @ApiModelProperty(value = "customerId", required = false)
    private String customerId;

    @ApiModelProperty(value = "pickUpEmployeeId", required = false)
    private String pickUpEmployeeId;

    @ApiModelProperty(value = "returnEmployeeId", required = false)
    private String returnEmployeeId;

    @ApiModelProperty(value = "carId", required = false)
    private String carId;

    @ApiModelProperty(value = "car rental price plan", required = false)
    private RentalPricePlanDTO rentalPricePlanDTO;

    @ApiModelProperty(value = "driverId", required = false)
    private String driverId;

    @ApiModelProperty(value = "original price when make reservation", required = false)
    private Double originalPrice;
    @ApiModelProperty(value = "additional price when over returning or any", required = false)
    private Double additionalPrice;

    @ApiModelProperty(value = "schedule pick up date", required = false)
    private Date pickUpDate;

    @ApiModelProperty(value = "actual pick up date", required = false)
    private Date actualPickUpDate;

    @ApiModelProperty(value = "schedule date to return car", required = false)
    private Date returnDate;

    @ApiModelProperty(value = "actual date of returning car", required = false)
    private Date actualReturnDate;

    @ApiModelProperty(value = "bookingStatus", required = false)
    private BookingStatus bookingStatus;

    @ApiModelProperty(value = "bookingRecordId", required = false)
    private String bookingRecordId;

    @ApiModelProperty(value = "customer info", required = false)
    private CustomerDTO customerDTO;
}
