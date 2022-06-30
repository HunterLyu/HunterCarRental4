package org.hunter.carrental4.domain.model.entity;

import lombok.Builder;
import lombok.Data;
import org.hunter.carrental4.common.model.enums.CarStatus;
import org.hunter.carrental4.domain.model.valueobject.CarType;

@Data
@Builder
public class Car {
    private String carId;
    private CarType carType;
    private String carNumber;
    private CarStatus status;
    private String condition;
}
