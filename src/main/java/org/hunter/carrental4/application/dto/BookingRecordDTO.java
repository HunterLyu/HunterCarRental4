package org.hunter.carrental4.application.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.hunter.carrental4.common.model.enums.PersonalIdIdType;

import java.util.Date;

@Data
@Builder
@ApiModel(description = "User original booking data")
public class BookingRecordDTO {

    @ApiModelProperty(name = "createDate", required = false)
    private Date createDate;

    @ApiModelProperty(name = "ID", required = false)
    private String bookingRecordId;
    @ApiModelProperty(name = "your id card number", required = true)
    private String idCardNumber;
    @ApiModelProperty(name = "id card type", required = false)
    private PersonalIdIdType personalIdIdType;
    @ApiModelProperty(name = "your name", required = true)
    private String name;
    @ApiModelProperty(name = "sex", required = false)
    private String sex;
    @ApiModelProperty(name = "age", required = false)
    private Integer age;
    @ApiModelProperty(name = "address", required = false)
    private String address;
    @ApiModelProperty(name = "phoneNo", required = false)
    private String phoneNo;
    @ApiModelProperty(name = "licenseNo", required = false)
    private String licenseNo;

    @ApiModelProperty(name = "car info", required = true)
    private CarTypeDTO carTypeDTO;

    @ApiModelProperty(name = "pick up date", required = true)
    private Date pickUpdate;

    @ApiModelProperty(name = "return date", required = true)
    private Date returnDate;

}
