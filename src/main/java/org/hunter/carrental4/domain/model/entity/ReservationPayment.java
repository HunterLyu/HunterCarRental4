package org.hunter.carrental4.domain.model.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReservationPayment extends Payment {
    private String reservationId;
    private String customerId;
}
