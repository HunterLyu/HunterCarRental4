package org.hunter.carrental4.application.model.dto;

import lombok.Builder;
import lombok.Data;
import org.hunter.carrental4.common.model.enums.CarStatus;
import org.hunter.carrental4.domain.model.entity.Car;
import org.springframework.beans.BeanUtils;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
public class CarDTO  implements Serializable {
    @Serial
    private static final long serialVersionUID = -1861555408271779032L;

    private String carId;
    private SimpleCarInfo simpleCarInfo;
    private String carNumber;
    private CarStatus status;
    private String condition;

    public void fromDomainObj(Car car){
        if(car != null){
            BeanUtils.copyProperties(car, this);
        }
    }
}
