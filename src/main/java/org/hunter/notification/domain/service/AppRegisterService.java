package org.hunter.notification.domain.service;

import org.hunter.carrental4.common.model.Result;
import org.hunter.notification.domain.model.AppRegistration;

public interface AppRegisterService {

    Result<Boolean> registerApp(AppRegistration appRegistration);
}
