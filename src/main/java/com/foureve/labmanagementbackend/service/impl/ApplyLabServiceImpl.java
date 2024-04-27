package com.foureve.labmanagementbackend.service.impl;

import com.foureve.labmanagementbackend.Holder.RequestHolder;
import com.foureve.labmanagementbackend.dao.ApplyLabDao;
import com.foureve.labmanagementbackend.domain.dtos.StuApplyLabDto;
import com.foureve.labmanagementbackend.domain.dtos.TeaApplyLabDto;
import com.foureve.labmanagementbackend.domain.dtos.UpdateApplyLabDto;
import com.foureve.labmanagementbackend.domain.entity.vo.ApplyLabVo;
import com.foureve.labmanagementbackend.service.ApplyLabService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author GASEN
 * @date 2024/4/25 14:04
 * @classType description
 */
@Service
public class ApplyLabServiceImpl implements ApplyLabService {
    @Resource
    private ApplyLabDao applyLabDao;



    @Override
    public List<ApplyLabVo> getApplyLabList() {
        Long userId = RequestHolder.get().getUserId();
        return applyLabDao.getListById(userId);
    }

    @Override
    public void addApplyLabByTeacher(TeaApplyLabDto applyLabDto) {
        applyLabDao.addApplyLabByTeacher(applyLabDto);
    }

    @Override
    public void updateApplyLabByTeacher(UpdateApplyLabDto applyLabDto) {
        applyLabDao.updateApplyLabByTeacher(applyLabDto);
    }

    @Override
    public void addApplyLabByStudent(StuApplyLabDto stuApplyLabDto) {
        applyLabDao.addApplyLabByStudent(stuApplyLabDto);

    }

    @Override
    public void updateApplyLabByStudent(StuApplyLabDto stuApplyLabDto) {
        applyLabDao.updateApplyLabByStudent(stuApplyLabDto);
    }

    @Override
    public void finishedApplyLab(Long id) {
        applyLabDao.finishedApplyLab(id);
    }


}
