package com.foureve.labmanagementbackend.service.impl;

import com.foureve.labmanagementbackend.Holder.RequestHolder;
import com.foureve.labmanagementbackend.dao.ApplyEquDao;
import com.foureve.labmanagementbackend.domain.dtos.ApplyEquDto;
import com.foureve.labmanagementbackend.domain.entity.vo.ApplyEquVo;
import com.foureve.labmanagementbackend.service.ApplyEquService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author GASEN
 * @date 2024/4/25 14:03
 * @classType description
 */
@Service
public class ApplyEquServiceImpl implements ApplyEquService {
    @Resource
    private ApplyEquDao applyEquDao;


    @Override
    public void addApplyEquByTeacher(ApplyEquDto applyEquDto) {
        applyEquDao.addApplyEquByTeacher(applyEquDto);
    }

    @Override
    public List<ApplyEquVo> getApplyEquList() {
        Long userId = RequestHolder.get().getUserId();
        return applyEquDao.getListById(userId);
    }
}
