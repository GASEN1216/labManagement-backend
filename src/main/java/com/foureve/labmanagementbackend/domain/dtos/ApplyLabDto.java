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

//    @ApiModelProperty(value = "实验室编号", required = true)
//    private Long labNumber;

    @ApiModelProperty(value = "申请信息", required = true)
    private String message;

    /**
     * 学期
     */
    @ApiModelProperty(value = "学期", required = true)
    private String semester;

    /**
     * 课名
     */
    @ApiModelProperty(value = "课名", required = true)
    private String scheduleName;

    /**
     * 实验室类型 0 软件，1 硬件，2 网络
     */
    @ApiModelProperty(value = "实验室类型", required = true)
    private Integer labType;

    /**
     * 班级
     */
    @ApiModelProperty(value = "班级", required = true)
    private String classes;

    /**
     * 学生人数
     */
    @ApiModelProperty(value = "学生人数", required = true)
    private Long stuCount;

    /**
     * 起始周
     */
    @ApiModelProperty(value = "起始周", required = true)
    private Integer beginWeeks;

    /**
     * 结束周
     */
    @ApiModelProperty(value = "结束周", required = true)
    private Integer endWeeks;

    /**
     * 节次
     */
    @ApiModelProperty(value = "节次", required = true)
    private String section;
}
