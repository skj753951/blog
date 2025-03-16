package com.kfc.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

    private String code;
    private String msg;
    private T data;

    /**
     * 成功响应
     */
    public static <T> Result<T> success(T data) {
        return new Result<T>("200", "success", data);
    }

    /**
     * 失败响应
     */
    public static <T> Result<T> fail(String code, String msg) {
        return new Result<T>(code, msg, null);
    }

    /**
     * 失败响应
     */
    public static <T> Result<T> fail(String code, String msg, T errorData) {
        return new Result<T>(code, msg, errorData);
    }

}
