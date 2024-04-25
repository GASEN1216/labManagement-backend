package com.foureve.labmanagementbackend.domain.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author GASEN
 * @date 2024/4/25 19:47
 * @classType description
 */
@Data
public class UpdateApplyLabDto {
    @ApiModelProperty(value = "申请单id", required = true)
    private Long id;

    @ApiModelProperty(value = "实验室编号", required = true)
    private Long labNumber;

    @ApiModelProperty(value = "申请信息", required = true)
    private String message;
}
