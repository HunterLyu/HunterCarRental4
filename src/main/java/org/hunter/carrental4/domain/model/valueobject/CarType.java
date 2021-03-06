package org.hunter.carrental4.domain.model.valueobject;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode
public class CarType {
    private String brand;
    private String name;
}
