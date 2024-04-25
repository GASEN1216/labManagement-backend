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
 * 
 * </p>
 *
 * @author <a href="https://github.com/gasen1216">gasen</a>
 * @since 2024-04-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("schedule")
public class Schedule implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 学期
     */
    @TableField("semester_id")
    private String semesterId;

    /**
     * 节次
     */
    @TableField("section")
    private String section;

    /**
     * 实验室编号
     */
    @TableField("lab_number")
    private Long labNumber;

    /**
     * 课名
     */
    @TableField("name")
    private String name;

    /**
     * 班级
     */
    @TableField("classes")
    private String classes;

    /**
     * 任课教师
     */
    @TableField("teacher_id")
    private Long teacherId;

    /**
     * 星期
     */
    @TableField("week")
    private Integer week;

    /**
     * 周次
     */
    @TableField("weeks")
    private Integer weeks;

    /**
     * 逻辑删除
     */
    @TableField("is_delete")
    private Integer isDelete;

    /**
     * 创建文档
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;


}
