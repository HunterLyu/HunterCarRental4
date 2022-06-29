package org.hunter.carrental4.domain.model.entity;

import lombok.Data;

@Data
public abstract class Person {
    private PersonalId id;
    private String name;
    private String sex;
    private Integer age;
    private String address;
    private String phoneNo;
}
