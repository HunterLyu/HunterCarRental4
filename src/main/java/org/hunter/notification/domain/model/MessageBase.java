package org.hunter.notification.domain.model;

import lombok.Data;

import java.util.Date;

@Data
public class MessageBase {
    public final int TIME_UNIT_MILLIONSECOND = 0;
    public final int TIME_UNIT_SECOND = 1;
    public final int TIME_UNIT_MINUTE = 2;
    public final int TIME_UNIT_HOUR = 3;
    public final int TIME_UNIT_DAY = 4;


    private String id;
    private String userId;

    private Integer messageType;

    private Date invalidDate;

    private Integer retryMax;
    private Integer retryTimeInterval;
    private Integer retryTimeIntervalUnit;
}
