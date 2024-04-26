package com.foureve.labmanagementbackend.domain.enums;

import lombok.Getter;

@Getter
public enum WeekEnum {
    MONDAY(1, "周一"),
    TUESDAY(2, "周二"),
    WEDNESDAY(3, "周三"),
    THURSDAY(4, "周四"),
    FRIDAY(5, "周五"),
    SATURDAY(6, "周六"),
    SUNDAY(7, "周日");

    private Integer code;
    private String msg;

    WeekEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
