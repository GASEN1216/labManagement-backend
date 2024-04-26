package com.foureve.labmanagementbackend.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import com.foureve.labmanagementbackend.domain.entity.vo.ApplyEquVo;
import com.foureve.labmanagementbackend.domain.entity.vo.ApplyLabVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 设备维护申请表
 * </p>
 *
 * @author <a href="https://github.com/gasen1216">gasen</a>
 * @since 2024-04-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("apply_equ")
public class ApplyEqu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 状态 0 未维修，1 维修中，2已维修
     */
    @TableField("state")
    private Integer state;

    /**
     * 申请人id
     */
    @TableField("applicant_id")
    private Long applicantId;

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

    /**
     * 已维修后信息
     */
    @TableField("fixed_message")
    private String fixedMessage;

    @TableField("is_delete")
    private Integer isDelete;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;


    public ApplyEquVo toApplyEquVo() {
        ApplyEquVo applyEquVo = new ApplyEquVo();
        applyEquVo.setId(this.id);
        applyEquVo.setState(this.state);
        applyEquVo.setApplicantId(this.applicantId);
        applyEquVo.setLabNumber(this.labNumber);
        applyEquVo.setErrorMessage(this.errorMessage);
        applyEquVo.setFixedMessage(this.fixedMessage);
        applyEquVo.setCreateTime(this.createTime);
        return applyEquVo;
    }
}
