package org.hunter.carrental4.application.model.dto;

import lombok.Data;
import org.hunter.carrental4.common.model.enums.PersonalIdIdType;

import java.io.Serial;
import java.io.Serializable;

@Data
public abstract class PersonDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -8537920959587831150L;
    private String number;
    private PersonalIdIdType personalIdIdType;
    private String name;
    private String sex;
    private Integer age;
    private String address;
    private String phoneNo;
}
