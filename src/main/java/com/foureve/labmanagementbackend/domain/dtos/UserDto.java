package com.foureve.labmanagementbackend.domain.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserDto {


    @ApiModelProperty(value = "学号/工号", required = true)
    private Long account;

    @ApiModelProperty(value = "密码", required = false)
    private String password;

    @ApiModelProperty(value = "姓名", required = false)
    private String name;

    @ApiModelProperty(value = "角色", required = false)
    private int role;

    @ApiModelProperty(value = "职务", required = false)
    private String reputation;

    @ApiModelProperty(value = "专业", required = false)
    private String major;

    @ApiModelProperty(value = "班级", required = false)
    private String classes;

}
