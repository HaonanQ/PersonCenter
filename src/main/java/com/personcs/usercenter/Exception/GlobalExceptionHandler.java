package com.personcs.usercenter.Exception;

import com.personcs.usercenter.Respons.BaseResponse;
import com.personcs.usercenter.Respons.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public BaseResponse businessExceptionHandler(BusinessException e) {
        log.error("businessException: " + e.getMessage(), e);
        return new BaseResponse(e.getCode(), e.getMessage(), e.getDescription());
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse runtimeExceptionHandler(RuntimeException e) {
        log.error("runtimeException", e);
        return new BaseResponse(ErrorCode.SYSTEM_ERROR, e.getMessage(), "无法处理请求，请联系管理员");
    }
}
