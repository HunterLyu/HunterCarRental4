package org.hunter.carrental4.application.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.hunter.carrental4.domain.model.valueobject.CarType;
import org.springframework.beans.BeanUtils;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@ApiModel(description = "Common car information")
public class CarTypeDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -57145529595638770L;

    @ApiModelProperty(value = "car brand")
    private String brand;
    @ApiModelProperty(value = "simple car name")
    private String name;

    public void fromDomainObj(CarType carType){
        if(carType != null){
            BeanUtils.copyProperties(carType, this);
        }
    }
}
