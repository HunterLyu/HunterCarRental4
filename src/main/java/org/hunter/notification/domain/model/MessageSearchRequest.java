package org.hunter.notification.domain.model;

import java.util.List;

public class MessageSearchRequest {
    private List<String> messageIds;

    private String userId;

    private String appNameSpace;

    private String appVersion;

    private String token;


    private int pageSize = 20;
    private int pageNumber = 1;
}
