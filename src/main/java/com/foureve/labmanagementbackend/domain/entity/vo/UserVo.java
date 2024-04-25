package com.foureve.labmanagementbackend.domain.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 返回前端安全User数据
 */
@Data
public class UserVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学号 工号
     */
    private Long account;

    /**
     * 用户名
     */
    private String name;

    /**
     * 角色 0 管理员，1 学生，2 教师，3 实验员
     */
    private Integer role;

    /**
     *  职称
     */
    private String reputation;

    /**
     * 专业
     */
    private String major;

    /**
     * 班级
     */
    private String classes;

}
