package org.hunter.carrental4.domain.model.entity;

import lombok.Data;
import org.hunter.carrental4.common.model.enums.BookingStatus;
import org.hunter.carrental4.domain.model.valueobject.RentalPricePlan;

import java.util.Date;

@Data
public class CustomerReservation {
    public final static long ONE_DAY_MILLISECONDS = 1000 * 60 * 60 * 24;

    private String id;

    private Date bookingDate;

    private String customerId;

    private String pickUpEmployeeId;
    private String returnEmployeeId;

    private String carId;

    private RentalPricePlan rentalPricePlan;

    private String driverId;

    private Double originalPrice;
    private Double additionalPrice;

    private Date actualPickUpDate;

    private Date pickUpDate;
    private Date returnDate;
    private Date actualReturnDate;

    private BookingStatus bookingStatus;

    private String bookingRecordId;

    public void calculateOriPrice(){
        if(pickUpDate == null || returnDate == null || rentalPricePlan == null){
            return;
        }
        long duration = returnDate.getTime() - pickUpDate.getTime();

        long days = duration / ONE_DAY_MILLISECONDS;
        if(duration % ONE_DAY_MILLISECONDS > 0){
            days++;
        }

        double price = rentalPricePlan.getPriceUnit() * days;
        this.originalPrice = price;
    }

    public void calculateAdditionalPrice(){
        if(pickUpDate == null || actualReturnDate == null || rentalPricePlan == null){
            return;
        }

        long duration = actualReturnDate.getTime() - pickUpDate.getTime();

        long days = duration / ONE_DAY_MILLISECONDS;
        if(duration % ONE_DAY_MILLISECONDS > 0){
            days++;
        }

        double actualPrice = rentalPricePlan.getPriceUnit() * days;

        if(this.originalPrice < actualPrice){
            this.additionalPrice = actualPrice - this.originalPrice;
        }
    }
}
