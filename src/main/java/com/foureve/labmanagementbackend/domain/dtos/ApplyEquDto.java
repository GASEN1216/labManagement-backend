package com.foureve.labmanagementbackend.domain.dtos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
public class ApplyEquDto{

    /**
     * 实验室编号
     */
    @TableField("lab_number")
    private Long labNumber;

    /**
     * 维修信息
     */
    @TableField("error_message")
    private String errorMessage;


}
