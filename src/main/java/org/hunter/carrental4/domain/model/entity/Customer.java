package org.hunter.carrental4.domain.model.entity;

import lombok.Data;

@Data
public class Customer extends Person{
    private String customerId;
    private String licenseNo;
}
