package com.youjian.blog.user.utils;

import com.youjian.blog.user.vo.ResponseResult;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({BindException.class, ConstraintViolationException.class})
    @ResponseBody
    public ResponseResult handlerEx(Exception exception) {
        String message = null;
        if (exception instanceof ConstraintViolationException) {
            message = BindingResultExceptionHandler.getMessage((ConstraintViolationException) exception);
        } else {
            message = BindingResultExceptionHandler.getMessage((BindingResult) exception);
        }
        return ResponseResult.fail(message);
    }
}
