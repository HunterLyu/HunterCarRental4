package org.hunter.carrental4.application.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.hunter.carrental4.domain.model.entity.CarInventory;
import org.hunter.carrental4.domain.model.valueobject.CarType;
import org.hunter.carrental4.domain.model.valueobject.RentalPricePlan;
import org.springframework.beans.BeanUtils;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@ApiModel(description = "Car inventory objectr")
public class CarInventoryDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 7813453287058290514L;

    @ApiModelProperty(value = "common car information")
    private CarTypeDTO carInfo;
    @ApiModelProperty(value = "inventory amount of a car type")
    private Integer amount;
    @ApiModelProperty(value = "pricing plan for a a car type")
    private RentalPricePlanDTO pricePlan;

    public void fromDomainObj(CarInventory carInventory) {
        if (carInventory != null) {
            this.amount = carInventory.getAvailableAmount();

            RentalPricePlan pricePlanDO = carInventory.getRentalPricePlan();
            if (pricePlanDO != null) {
                this.pricePlan = RentalPricePlanDTO.builder().build();
                BeanUtils.copyProperties(pricePlanDO, this.pricePlan);
            }

            CarType carType = carInventory.getCarType();
            if (carType != null) {
                this.carInfo = CarTypeDTO.builder().build();
                carInfo.fromDomainObj(carType);
            }
        }
    }
}
