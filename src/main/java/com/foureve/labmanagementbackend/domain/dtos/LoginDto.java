package com.foureve.labmanagementbackend.domain.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginDto {

    @ApiModelProperty(value = "学号/工号", required = true)
    private String account;

    @ApiModelProperty(value = "密码", required = true)
    private String password;
}
