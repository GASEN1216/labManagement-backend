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
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 学号 工号
     */
    @TableField("account")
    private Long account;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 用户名
     */
    @TableField("name")
    private String name;

    /**
     * 角色 0 管理员，1 学生，2 教师，3 实验员
     */
    @TableField("role")
    private Integer role;

    /**
     *  职称
     */
    @TableField("reputation")
    private String reputation;

    /**
     * 专业
     */
    @TableField("major")
    private String major;

    /**
     * 班级
     */
    @TableField("classes")
    private String classes;

    /**
     * 逻辑删除
     */
    @TableField("is_delete")
    private Integer isDelete;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;

    /**
     * 盐值
     */
    @TableField("salt")
    private String salt;


}
