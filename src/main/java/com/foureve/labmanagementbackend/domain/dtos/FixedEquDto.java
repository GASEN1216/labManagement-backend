package com.foureve.labmanagementbackend.domain.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author GASEN
 * @date 2024/4/27 17:50
 * @classType description
 */
@Data
public class FixedEquDto {

    @ApiModelProperty(value = "维修单id", required = true)
    private Long id;

    @ApiModelProperty(value = "维修信息", required = true)
    private String fixedMessage;
}
