package com.kfc.handler;

import com.kfc.exception.BusinessException;
import com.kfc.exception.UserException;
import com.kfc.result.Result;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.stream.Collectors;

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
     * 参数校验异常
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result<?> handleValidationException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<String> errors = bindingResult.getFieldErrors().stream().map(fieldError -> fieldError.getField() + ":" + fieldError.getDefaultMessage()).collect(Collectors.toList());
        return Result.fail(400, "参数校验失败", errors);
    }

    /**
     * 权限异常
     */
    @ExceptionHandler({AccessDeniedException.class, UserException.UserPermissionDeniedException.class})
    public Result<?> handleAuthException(Exception e) {
        return Result.fail(403, "无访问权限");
    }

    /**
     * 兜底异常
     */
    @ExceptionHandler(Exception.class)
    public Result<?> handleGlobalException(Exception e) {
        return Result.fail(500, "系统繁忙，请稍后再试");
    }
}
