package org.hunter.carrental4.domain.model;

import lombok.Data;

@Data
public class Car {
    private CarId carId;
    private String brand;
    private String name;
}
