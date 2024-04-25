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
}
