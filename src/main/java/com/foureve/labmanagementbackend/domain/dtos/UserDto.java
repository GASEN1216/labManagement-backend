package com.foureve.labmanagementbackend.domain.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserDto {


    @ApiModelProperty(value = "学号/工号", required = true)
    private String account;

    @ApiModelProperty(value = "密码", required = true)
    private String password;

    @ApiModelProperty(value = "姓名", required = true)
    private String name;

    @ApiModelProperty(value = "角色", required = true)
    private int role;

    @ApiModelProperty(value = "职务", required = false)
    private int reputation;

    @ApiModelProperty(value = "专业", required = false)
    private int major;

    @ApiModelProperty(value = "班级", required = false)
    private int classes;

}
