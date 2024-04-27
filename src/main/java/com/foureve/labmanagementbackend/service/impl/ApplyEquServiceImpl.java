package com.foureve.labmanagementbackend.service.impl;

import com.foureve.labmanagementbackend.Holder.RequestHolder;
import com.foureve.labmanagementbackend.dao.ApplyEquDao;
import com.foureve.labmanagementbackend.dao.LabDao;
import com.foureve.labmanagementbackend.domain.dtos.ApplyEquDto;
import com.foureve.labmanagementbackend.domain.dtos.FixedEquDto;
import com.foureve.labmanagementbackend.domain.entity.ApplyEqu;
import com.foureve.labmanagementbackend.domain.entity.vo.ApplyEquVo;
import com.foureve.labmanagementbackend.service.ApplyEquService;
import com.foureve.labmanagementbackend.service.LabService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author GASEN
 * @date 2024/4/25 14:03
 * @classType description
 */
@Service
public class ApplyEquServiceImpl implements ApplyEquService {
    @Resource
    private ApplyEquDao applyEquDao;
    @Resource
    private LabDao labDao;


    @Override
    public void addApplyEquByTeacher(ApplyEquDto applyEquDto) {
        applyEquDao.addApplyEquByTeacher(applyEquDto);
    }

    @Override
    public List<ApplyEquVo> getApplyEquListByLabMember() {
        Long labmemberId = RequestHolder.get().getUserId();
        // 根据实验室管理员id获取实验室id
        List<Long> labs = labDao.getLabNumberByLabMemberId(labmemberId);
        if(labs.isEmpty()) return null;
        // 根据实验室id获取实验室所有维修申请
        List<ApplyEqu> equs = applyEquDao.getApplyEquListByLabNumber(labs);
        if(equs.isEmpty()) return null;
        // 过滤信息
        return equs.stream().map(ApplyEqu::toApplyEquVo).collect(Collectors.toList());
    }

    @Override
    public void startApplyEqu(Long id) {
        applyEquDao.startApplyEqu(id);
    }

    @Override
    public void finishedApplyEqu(FixedEquDto fixedEquDto) {
        applyEquDao.finishedApplyEqu(fixedEquDto);
    }

    @Override
    public List<ApplyEquVo> getApplyEquList() {
        Long userId = RequestHolder.get().getUserId();
        return applyEquDao.getListById(userId);
    }
}
