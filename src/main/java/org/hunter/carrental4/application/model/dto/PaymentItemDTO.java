package org.hunter.carrental4.application.model.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class PaymentItemDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1311361109431168695L;
    private String paymentItemId;
    private String name;
    private Integer total;
    private Double price;
}
