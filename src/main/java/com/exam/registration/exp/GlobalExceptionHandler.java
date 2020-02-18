package com.exam.registration.exp;

import com.exam.registration.util.MsgUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author yhf
 * @classname GlobalExceptionHandler
 **/
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String handleException(Exception e) {
        e.printStackTrace();
        if (e.getMessage().startsWith("token")) {
            return MsgUtils.noLogin();
        }
        return MsgUtils.fail(e.getMessage());
    }
}