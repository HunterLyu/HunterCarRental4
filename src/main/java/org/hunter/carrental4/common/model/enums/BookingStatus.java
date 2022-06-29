package org.hunter.carrental4.common.model.enums;

import lombok.Getter;

@Getter
public enum BookingStatus {
    New(0),
    Reserve(1),
    PrePaid(2),
    PickUp(3),
    Returned(4),
    RequirePostPay(5),
    PostPaid(6),
    Cancelled(7),
    Expired(8),
    Finish(9);

    private int code;

    BookingStatus(int code) {
        this.code = code;
    }
}
