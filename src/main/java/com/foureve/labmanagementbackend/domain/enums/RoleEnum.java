package com.foureve.labmanagementbackend.domain.enums;

import lombok.Getter;

/**
 * @author GASEN
 * @date 2024/4/25 20:07
 * @classType description
 */
@Getter
public enum RoleEnum {
    ADMIN(0, "管理员"),
    STUDENT(1, "学生"),
    TEACHER(2, "教师"),
    LAB_MEMBER(3, "实验员");

    private Integer code;
    private String msg;

    RoleEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
