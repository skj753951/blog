package com.kfc.contant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum MessageConstant {
    ALREADY_EXISTS("100001","账号已经存在"),
    ACCOUNT_NOT_FOUND("100002","账号不存在"),
    PASSWORD_ERROR("100001","密码错误"),
    JWT_ERROR("200001","Invalid JWT token");




    private String code;

    private String message;




}
