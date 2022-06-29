package org.hunter.carrental4.common.model.enums;

import lombok.Getter;

@Getter
public enum CarStatus {
    Unavailable(0),
    Available(1),
    Occupied(2),
    Maintain(3);

    private int code;

    CarStatus(int code) {
        this.code = code;
    }


}
