package org.hunter.carrental4.domain.model.entity;

import lombok.Data;

@Data
public class PaymentItem {
    private String id;
    private String name;
    private Integer total;
    private Double price;
}
