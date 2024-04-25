package com.foureve.labmanagementbackend.domain.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author GASEN
 * @date 2024/4/25 19:47
 * @classType description
 */
@Data
public class ApplyLabDto {

    @ApiModelProperty(value = "实验室编号", required = true)
    private Long labNumber;

    @ApiModelProperty(value = "申请信息", required = true)
    private String message;
}
