package com.foureve.labmanagementbackend.domain.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author GASEN
 * @date 2024/4/25 19:20
 * @classType description
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplyLabVo {
    private Long applicantId;

    private Long labNumber;

    private Integer state;

    private String message;

    private Date createTime;

    /**
     * 学期
     */
    private String semester;

    /**
     * 课名
     */
    private String scheduleName;

    /**
     * 实验室类型 0 软件，1 硬件，2 网络
     */
    private Integer labType;

    /**
     * 班级
     */
    private String classes;

    /**
     * 学生人数
     */
    private Long stuCount;

    /**
     * 起始周
     */
    private Integer beginWeeks;

    /**
     * 结束周
     */
    private Integer endWeeks;

    /**
     * 节次
     */
    private String section;
}
