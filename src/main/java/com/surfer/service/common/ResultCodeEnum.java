package com.surfer.service.common;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2024/12/17 23:26
 * description 统一返回结果状态信息类
 */
public enum ResultCodeEnum {

    SUCCESS(200,"success"),
    USERNAME_ERROR(501,"usernameError"),
    PASSWORD_ERROR(503,"passwordError"),
    NOT_LOGIN(504,"notLogin"),
    USERNAME_USED(505,"userNameUsed");

    private final Integer code;

    private final String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
