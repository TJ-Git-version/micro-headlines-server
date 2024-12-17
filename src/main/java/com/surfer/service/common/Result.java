package com.surfer.service.common;

import lombok.Getter;

import java.util.Date;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2024/12/17 23:18
 * description 全局统一返回结果类
 */
public class Result<T> {

    // 状态码
    @Getter
    private Integer code;

    // 操作结果描述信息
    private String message;

    // 返回数据
    private T data;

    // 返回结果的时间戳
    private Date timestamp;

    public Result() {
        code = 200;
        message = "ok";
        timestamp = new Date();
    }

    public static <T> Result<T> build(T data) {
        Result<T> result = new Result<>();
        if (data != null) {
            result.setData(data);
        }
        return result;
    }

    public static <T> Result<T> build(T data, Integer code, String message) {
        Result<T> result = build(data);
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> build(T data, ResultCodeEnum resultCodeEnum) {
        return build(data, resultCodeEnum.getCode(), resultCodeEnum.getMessage());
    }

    public static <T> Result<T> ok(T data) {
        return build(data, ResultCodeEnum.SUCCESS);
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
