package com.youjian.blog.user.utils;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/***
 * Bean 验证异常处理器
 */
public class BindingResultExceptionHandler {
    /**
     * 将多个异常组合成一个字符串异常
     */
    public static String getMessage(BindingResult e) {
        List<ObjectError> errors = e.getAllErrors();
        return errors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(";"));
    }

    public static String getMessage(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        return constraintViolations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(";"));
    }
}
