package org.hunter.carrental4.application.model.dto;

import lombok.Builder;
import lombok.Data;
import org.hunter.carrental4.common.model.enums.PersonalIdIdType;

import java.util.Date;

@Data
@Builder
public class BookingRecordDTO {

    private String bookingRecordId;
    private String idNumber;
    private PersonalIdIdType personalIdIdType;
    private String name;
    private String sex;
    private Integer age;
    private String address;
    private String phoneNo;
    private String licenseNo;

    private SimpleCarInfo simpleCarInfo;

    private Date rentDate;
    private Date oriReturnDate;

}
