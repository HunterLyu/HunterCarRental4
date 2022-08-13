package org.hunter.notification.domain.model;

import lombok.Data;

@Data
public class AppMessage extends MessageBase {
    public final int RANGE_TYPE_USER_APP = 0;
    public final int RANGE_TYPE_USER = 1;


    private Integer rangeType;

    private boolean securePush;

    private boolean checkReceipt;
    private boolean received;

    private boolean resendBeforeReceipt;
    private Integer resendMax;
    private Integer resendTimeInterval;
    private Integer resendTimeIntervalUnit;

    private String templateId;

    private String messageContent;
}
