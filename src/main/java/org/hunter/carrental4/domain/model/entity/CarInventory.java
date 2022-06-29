package org.hunter.carrental4.domain.model.entity;

import lombok.Builder;
import lombok.Data;
import org.hunter.carrental4.domain.model.vo.RentalPricePlan;
import org.hunter.carrental4.domain.model.vo.CarType;

@Data
@Builder
public class CarInventory {
    private CarType carType;
    private Integer availableAmount;
    private RentalPricePlan rentalPricePlan;
}
