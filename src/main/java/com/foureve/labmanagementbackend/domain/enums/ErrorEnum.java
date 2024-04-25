package com.foureve.labmanagementbackend.domain.enums;

import lombok.Getter;

/**
 * @author GASEN
 * @date 2024/4/25 14:47
 * @classType description
 */
@Getter
public enum ErrorEnum {

    PASSWORD_ERROR(10001, "密码错误"),

    USER_NOT_EXIST(10002, "用户不存在"),

    SYSTEM_ERROR(10003, "系统出小差了，请稍后再试哦~~"),
    ;



    private Integer code;

    private String msg;


    ErrorEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


}
