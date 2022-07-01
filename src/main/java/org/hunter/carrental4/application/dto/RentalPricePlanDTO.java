package org.hunter.carrental4.application.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.hunter.carrental4.common.model.enums.PriceType;

import java.io.Serial;
import java.io.Serializable;


@Data
@Builder
@ApiModel(description = "A pricing plan to rental")
public class RentalPricePlanDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 866991305524431025L;

    @ApiModelProperty(value = "price type of 'PerDayPricing' or more")
    private PriceType priceType;

    /**
     * price per day or...
     */
    @ApiModelProperty(value = "price per price type unit, such as 100 RMB per day")
    private Double priceUnit;

    @ApiModelProperty(value = "currency")
    private String currency;

}
