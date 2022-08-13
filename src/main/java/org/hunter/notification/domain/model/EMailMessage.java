package org.hunter.notification.domain.model;

import lombok.Data;

@Data
public class EMailMessage extends MessageBase {
    public final int RANGE_TYPE_USER_APP = 0;
    public final int RANGE_TYPE_USER = 1;

    private String email;

    private String templateId;

    private String messageContent;
}
