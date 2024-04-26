package com.foureve.labmanagementbackend.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author GASEN
 * @date 2024/4/25 14:47
 * @classType description
 */
@AllArgsConstructor
@Getter
public enum ErrorEnum {

    PASSWORD_ERROR(10001, "密码错误"),

    USER_NOT_EXIST(10002, "用户不存在"),

    SYSTEM_ERROR(10003, "系统出小差了，请稍后再试哦~~"),

    APPLY_Lab_NOT_EXIST(10004, "该申请单不存在或已被审核"),

    PARAM_ERROR(10005, "参数错误"),

    NOPOWER(10006, "权限不足")
    ;



    private Integer code;

    private String msg;




}
