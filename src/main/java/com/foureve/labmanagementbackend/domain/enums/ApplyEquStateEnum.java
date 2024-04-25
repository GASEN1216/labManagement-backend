package com.foureve.labmanagementbackend.domain.enums;

import lombok.Getter;

/**
 * @author GASEN
 * @date 2024/4/25 23:58
 * @classType description
 */
@Getter
public enum ApplyEquStateEnum {
    WAIT_APPROVAL(0,"未维修"),
    APPROVED(1,"维修中"),
    REJECTED(2,"已维修"),
    ;
    private Integer code;
    private String message;
    ApplyEquStateEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
