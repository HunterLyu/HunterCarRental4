package org.hunter.carrental4.interfaces;

import org.hunter.carrental4.common.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

public class BaseController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler
    public @ResponseBody Object exceptionHandler(Exception exception, HttpServletResponse response) {
        exception.printStackTrace();
        logger.error(exception.getMessage());
        return Result.fail(exception.getMessage(), "");
    }

}
