package com.foureve.labmanagementbackend.domain.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SemesterDto {

    @ApiModelProperty(value = "学期名称", required = true)
    private String name;

}
