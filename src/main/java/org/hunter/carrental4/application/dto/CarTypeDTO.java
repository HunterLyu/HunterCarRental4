package org.hunter.carrental4.application.dto;

import lombok.Builder;
import lombok.Data;
import org.hunter.carrental4.domain.model.valueobject.CarType;
import org.springframework.beans.BeanUtils;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
public class CarTypeDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -57145529595638770L;

    private String brand;
    private String name;

    public void fromDomainObj(CarType carType){
        if(carType != null){
            BeanUtils.copyProperties(carType, this);
        }
    }
}
