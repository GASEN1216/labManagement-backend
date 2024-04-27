package com.foureve.labmanagementbackend.service;

import com.foureve.labmanagementbackend.domain.dtos.StuApplyLabDto;
import com.foureve.labmanagementbackend.domain.dtos.TeaApplyLabDto;
import com.foureve.labmanagementbackend.domain.dtos.UpdateApplyLabDto;
import com.foureve.labmanagementbackend.domain.entity.vo.ApplyLabVo;

import java.util.List;

/**
 * <p>
 * 实验室申请表 服务类
 * </p>
 *
 * @author <a href="https://github.com/gasen1216">gasen</a>
 * @since 2024-04-25
 */
public interface ApplyLabService {

    List<ApplyLabVo> getApplyLabList();

    void addApplyLabByTeacher(TeaApplyLabDto applyLabDto);

    void updateApplyLabByTeacher(UpdateApplyLabDto applyLabDto);

    void addApplyLabByStudent(StuApplyLabDto stuApplyLabDto);

    void updateApplyLabByStudent(StuApplyLabDto stuApplyLabDto);

    void finishedApplyLab(Long id);
}
