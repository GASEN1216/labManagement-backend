package com.foureve.labmanagementbackend.domain.enums;

import lombok.Getter;

/**
 * @author GASEN
 * @date 2024/4/25 20:34
 * @classType description
 */
@Getter
public enum ApplyLabStateEnum {

    UNSCHEDULED(0, "未排课"),
    SCHEDULED(1, "已排课"),
    UNAUDITED(2, "未审核"),
    PASS(3, "通过"),
    REJECT(4, "驳回"),
    USED_UP(5, "使用完毕");

    Integer code;
    String msg;
    ApplyLabStateEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


}
