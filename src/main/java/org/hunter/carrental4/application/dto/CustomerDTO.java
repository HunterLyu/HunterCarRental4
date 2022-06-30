package org.hunter.carrental4.application.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@ApiModel(description = "Customer info")
public class CustomerDTO extends PersonDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -1723138762822240234L;
    @ApiModelProperty(value = "customer id", required = false)
    private String customerId;
    @ApiModelProperty(value = "customer license number", required = false)
    private String licenseNo;
}
