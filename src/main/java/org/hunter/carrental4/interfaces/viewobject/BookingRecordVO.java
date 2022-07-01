package org.hunter.carrental4.interfaces.viewobject;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.hunter.carrental4.application.dto.CarTypeDTO;
import org.hunter.carrental4.common.model.enums.PersonalIdIdType;

@Data
@Builder
@ApiModel(description = "User original booking data")
public class BookingRecordVO {

    @ApiModelProperty(value = "ID", required = false)
    private String bookingRecordId;
    @ApiModelProperty(value = "your id card number", required = true)
    private String idCardNumber;
    @ApiModelProperty(value = "id card type", required = false)
    private PersonalIdIdType personalIdIdType;
    @ApiModelProperty(value = "your name", required = true)
    private String name;
    @ApiModelProperty(value = "sex", required = false)
    private String sex;
    @ApiModelProperty(value = "age", required = false)
    private Integer age;
    @ApiModelProperty(value = "address", required = false)
    private String address;
    @ApiModelProperty(value = "phone number", required = false)
    private String phoneNo;
    @ApiModelProperty(value = "license number", required = false)
    private String licenseNo;

    @ApiModelProperty(value = "car info", required = true)
    private CarTypeDTO carTypeDTO;

    @ApiModelProperty(value = "pick up date in format of yyyy-MM-dd HH:mm:ss", required = true)
    private String pickUpDateStr;

    @ApiModelProperty(value = "return date in format of yyyy-MM-dd HH:mm:ss", required = true)
    private String returnDateStr;

}
