package com.foureve.labmanagementbackend.domain.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ApproveApplyLabDto {

    @ApiModelProperty(value = "申请id", required = true)
    private Long id;

    @ApiModelProperty(value = "状态", required = true)
    private Integer approve;
}
