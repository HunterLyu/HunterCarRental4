package org.hunter.carrental4.domain.model.vo;

import lombok.Builder;
import lombok.Data;
import org.hunter.carrental4.common.model.enums.PriceType;


@Data
@Builder
public class RentalPricePlan {
    private PriceType priceType;

    /**
     * price per day or...
     */
    private Double priceUnit;

    private String currency;

}
