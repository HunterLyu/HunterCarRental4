package org.hunter.carrental4.application.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hunter.carrental4.common.model.enums.PersonalIdIdType;

import java.io.Serial;
import java.io.Serializable;

@Data
@ApiModel(description = "A person info")
public abstract class PersonDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -8537920959587831150L;
    @ApiModelProperty(value = "idCardNumber", required = false)
    private String idCardNumber;
    @ApiModelProperty(value = "personalIdIdType", allowableValues = "PrcId, PassPort", required = false)
    private PersonalIdIdType personalIdIdType;
    private String name;
    @ApiModelProperty(value = "customer name", required = false)
    private String sex;
    @ApiModelProperty(value = "customer sex", required = false)
    private Integer age;
    @ApiModelProperty(value = "customer address", required = false)
    private String address;
    @ApiModelProperty(value = "customer phoneNo", required = false)
    private String phoneNo;
}
