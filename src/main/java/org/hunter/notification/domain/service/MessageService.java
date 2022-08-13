package org.hunter.notification.domain.service;

import org.hunter.carrental4.common.model.Result;
import org.hunter.notification.domain.model.*;

import java.util.List;

public interface MessageService {

    Result<String> push(AppMessage appMessage);

    Result<String> push(SMSMessage smsMessage);

    Result<String> push(EMailMessage eMailMessage);

    Result<Boolean> confirmReceipt(String messageId);

    Result<Boolean> invalidMessage(String messageId);

    Result<List<MessageBase>> listMessage (MessageSearchRequest messageSearchRequest);
}
