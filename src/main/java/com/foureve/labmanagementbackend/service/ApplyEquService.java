package com.foureve.labmanagementbackend.service;

import com.foureve.labmanagementbackend.domain.dtos.ApplyEquDto;
import com.foureve.labmanagementbackend.domain.entity.ApplyEqu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.foureve.labmanagementbackend.domain.entity.vo.ApplyEquVo;

import java.util.List;

/**
 * <p>
 * 设备维护申请表 服务类
 * </p>
 *
 * @author <a href="https://github.com/gasen1216">gasen</a>
 * @since 2024-04-25
 */
public interface ApplyEquService {
    List<ApplyEquVo> getApplyEquList();

    void addApplyEquByTeacher(ApplyEquDto applyEquDto);
}
