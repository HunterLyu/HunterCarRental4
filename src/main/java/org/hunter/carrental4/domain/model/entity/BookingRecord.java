package org.hunter.carrental4.domain.model.entity;

import lombok.Builder;
import lombok.Data;
import org.hunter.carrental4.common.model.enums.PersonalIdIdType;
import org.hunter.carrental4.domain.model.valueobject.CarType;

import java.util.Date;

@Data
@Builder
public class BookingRecord {
    private Date createDate;

    private String customerId;

    private String id;
    private String idNumber;
    private PersonalIdIdType personalIdIdType;
    private String name;
    private String sex;
    private Integer age;
    private String address;
    private String phoneNo;
    private String licenseNo;

    private CarType carType;

    private Date pickUpDate;
    private Date returnDate;
}
