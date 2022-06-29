package org.hunter.carrental4.application.model.dto;

import lombok.Builder;
import lombok.Data;
import org.hunter.carrental4.domain.model.entity.CarInventory;
import org.hunter.carrental4.domain.model.vo.CarType;
import org.hunter.carrental4.domain.model.vo.RentalPricePlan;
import org.springframework.beans.BeanUtils;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
public class CarInventoryDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 7813453287058290514L;

    private SimpleCarInfo carInfo;
    private Integer amount;
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
                this.carInfo = SimpleCarInfo.builder().build();
                carInfo.fromDomainObj(carType);
            }
        }
    }
}
