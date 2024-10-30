package com.personcs.usercenter.Respons;

import com.personcs.usercenter.Model.domain.Users;
import lombok.Data;

@Data
public class BaseResponse<T> {
    private int code;
    private String message;
    private T data;
    private String description;
    public BaseResponse(int code, T data, String message, String description) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.description = description;
    }

    public BaseResponse(int code, T data, String message) {
        this(code, data, message, "");
    }

    public BaseResponse(int code, T data) {
        this(code, data, "", "");
    }

    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), null, errorCode.getMessage(), errorCode.getDescription());
    }

    public BaseResponse(ErrorCode errorCode, String message, String description) {
        this(errorCode.getCode(), null, message, description);
    }

    public BaseResponse(BaseResponse<T> user) {
        this(user.getCode(), user.getData(), user.getMessage(), user.getDescription());
    }

    public BaseResponse(ErrorCode errorCode, String message) {
        this(errorCode.getCode(), null, message, errorCode.getDescription());
    }
}
