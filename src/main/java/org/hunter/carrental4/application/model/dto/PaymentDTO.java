package org.hunter.carrental4.application.model.dto;

import lombok.Data;
import org.hunter.carrental4.common.model.enums.PaymentType;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class PaymentDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 5600533578219464611L;
    private String paymentId;
    private String name;

    private List<PaymentItemDTO> paymentItems;

    private Double totalAmount;

    private PaymentType payType;

    private String creditCardNo;
}
