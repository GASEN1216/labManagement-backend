package com.foureve.labmanagementbackend.domain.enums;

import lombok.Getter;

/**
 * @author GASEN
 * @date 2024/4/25 20:24
 * @classType description
 */
@Getter
public enum ApplyLabTypeEnum {
    STUDENT(0, "申请学生"),
    TEACHER(1, "申请教师"),
    ;
    Integer code;
    String msg;
    ApplyLabTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
