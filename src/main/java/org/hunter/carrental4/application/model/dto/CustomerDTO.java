package org.hunter.carrental4.application.model.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class CustomerDTO extends PersonDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -1723138762822240234L;
    private String customerId;
    private String licenseNo;
}
