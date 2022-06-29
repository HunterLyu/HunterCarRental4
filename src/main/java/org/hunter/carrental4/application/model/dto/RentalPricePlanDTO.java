package org.hunter.carrental4.application.model.dto;

import lombok.Builder;
import lombok.Data;
import org.hunter.carrental4.common.model.enums.PriceType;

import java.io.Serial;
import java.io.Serializable;


@Data
@Builder
public class RentalPricePlanDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 866991305524431025L;

    private PriceType priceType;

    /**
     * price per day or...
     */
    private Double priceUnit;

    private String currency;

}
