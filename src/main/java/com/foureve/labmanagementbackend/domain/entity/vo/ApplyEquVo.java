package com.foureve.labmanagementbackend.domain.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 设备维护申请表
 * </p>
 *
 * @author <a href="https://github.com/gasen1216">gasen</a>
 * @since 2024-04-25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplyEquVo {

    private Long id;

    /**
     * 状态 0 未维修，1 维修中，2已维修
     */
    private Integer state;

    /**
     * 申请人id
     */
    private Long applicantId;

    /**
     * 实验室编号
     */
    private Long labNumber;

    /**
     * 维修信息
     */
    private String errorMessage;

    /**
     * 已维修后信息
     */
    private String fixedMessage;


    private Date createTime;

}
