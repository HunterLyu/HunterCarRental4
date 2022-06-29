package org.hunter.carrental4.domain.model.entity;

import lombok.Data;
import org.hunter.carrental4.common.model.enums.PaymentType;

import java.util.List;

@Data
public class Payment {
    private String paymentId;

    private String name;

    private List<PaymentItem> paymentItems;

    private Double totalAmount;

    private PaymentType payType;

    private String creditCardNo;
}
