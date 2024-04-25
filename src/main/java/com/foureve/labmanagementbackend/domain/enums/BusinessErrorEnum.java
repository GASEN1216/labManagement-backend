package com.foureve.labmanagementbackend.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Description: 业务校验异常码
 */
@AllArgsConstructor
@Getter
public enum BusinessErrorEnum {

    BUSINESS_ERROR(1001, "{0}"),

    SYSTEM_ERROR(1001, "系统出小差了，请稍后再试哦~~"),
    ;
    private Integer code;
    private String msg;


    public Integer getErrorCode() {
        return code;
    }


    public String getErrorMsg() {
        return msg;
    }
}

