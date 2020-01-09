package com.exam.registration.exp;

import com.exam.registration.util.MsgUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author yhf
 * @classname GlobalExceptionHandler
 * @description TODO
 * @date 2020/1/9
 **/
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String handleException(Exception e) {
        e.printStackTrace();
        return MsgUtils.fail(e.getMessage());
    }
}