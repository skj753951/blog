package com.kfc.handler;

import com.kfc.exception.BusinessException;
import com.kfc.result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@RestControllerAdvice
public class BlogExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * 自定义业务异常
     */
    @ExceptionHandler(value = BusinessException.class)
    public Result<?> handleBusinessException(BusinessException e) {
        return Result.fail(e.getCode(), e.getMessage());
    }



    /**
     * 兜底异常
     */
    @ExceptionHandler(Exception.class)
    public Result<?> handleGlobalException(Exception e) {
        return Result.fail("500", "系统繁忙，请稍后再试");
    }
}
