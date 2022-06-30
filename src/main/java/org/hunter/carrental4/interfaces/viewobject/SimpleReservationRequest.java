package org.hunter.carrental4.interfaces.viewobject;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel(description = "simple customer reservation request")
public class SimpleReservationRequest {

    @ApiModelProperty(value ="reservationId", required = true)
    private String reservationId;
    @ApiModelProperty(value = "any car damage found", required = false)
    private String damage;


}
