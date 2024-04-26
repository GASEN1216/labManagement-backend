package com.foureve.labmanagementbackend.exception;

import com.foureve.labmanagementbackend.domain.enums.ErrorEnum;
import com.foureve.labmanagementbackend.domain.vo.resp.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author GASEN
 * @date 2024/4/25 14:50
 * @classType description
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ApiResult businessExceptionHandler(BusinessException e) {
        log.error("事务异常:"+e.getMessage(), e);
        return ApiResult.fail(e.getCode(),e.getDescription());
    }

    @ExceptionHandler(RuntimeException.class)
    public ApiResult RuntimeExceptionHandler(RuntimeException e) {
        log.error("运行时异常:", e);
        return ApiResult.fail(ErrorEnum.SYSTEM_ERROR.getCode(),ErrorEnum.SYSTEM_ERROR.getMsg());
    }
}
