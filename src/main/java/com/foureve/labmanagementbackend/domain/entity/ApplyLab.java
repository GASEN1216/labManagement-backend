package com.foureve.labmanagementbackend.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 实验室申请表
 * </p>
 *
 * @author <a href="https://github.com/gasen1216">gasen</a>
 * @since 2024-04-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("apply_lab")
public class ApplyLab implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 申请人类别 0 学生， 1 教师
     */
    @TableField("type")
    private Integer type;

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
     * 状态 0 未排课，1 已排课，2 未审核，3 通过，4 驳回，5 使用完毕
     */
    @TableField("state")
    private Integer state;

    /**
     * 申请信息
     */
    @TableField("message")
    private String message;

    /**
     * 学期
     */
    @TableField("semester_id")
    private Long semesterId;

    /**
     * 课名
     */
    @TableField("schedule_name")
    private String scheduleName;

    /**
     * 实验室类型 0 软件，1 硬件，2 网络
     */
    @TableField("lab_type")
    private Integer labType;

    /**
     * 班级
     */
    @TableField("classes")
    private String classes;

    /**
     * 学生人数
     */
    @TableField("stu_count")
    private Long stuCount;

    /**
     * 起始周
     */
    @TableField("begin_weeks")
    private Integer beginWeeks;

    /**
     * 结束周
     */
    @TableField("end_weeks")
    private Integer endWeeks;

    /**
     * 节次
     */
    @TableField("section")
    private String section;

    /**
     * 逻辑删除
     */
    @TableField("is_delete")
    private Integer isDelete;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;


}
