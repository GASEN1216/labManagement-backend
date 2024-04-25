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
 * 实验室
 * </p>
 *
 * @author <a href="https://github.com/gasen1216">gasen</a>
 * @since 2024-04-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("lab")
public class Lab implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 实验室编号
     */
    @TableField("number")
    private Long number;

    /**
     * 实验室名称
     */
    @TableField("name")
    private String name;

    /**
     * 实验室类型 0 软件，1 硬件，2 网络
     */
    @TableField("type")
    private Integer type;

    /**
     * 设备数量
     */
    @TableField("equ_count")
    private Long equCount;

    /**
     * 管理员id
     */
    @TableField("admin_id")
    private Long adminId;

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
