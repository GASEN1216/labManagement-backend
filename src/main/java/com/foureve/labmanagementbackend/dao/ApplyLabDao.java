package com.foureve.labmanagementbackend.dao;

import com.foureve.labmanagementbackend.Holder.RequestHolder;
import com.foureve.labmanagementbackend.domain.dtos.ApplyLabDto;
import com.foureve.labmanagementbackend.domain.dtos.UpdateApplyLabDto;
import com.foureve.labmanagementbackend.domain.entity.ApplyLab;
import com.foureve.labmanagementbackend.domain.entity.User;
import com.foureve.labmanagementbackend.domain.entity.vo.ApplyLabVo;
import com.foureve.labmanagementbackend.domain.enums.ApplyLabStateEnum;
import com.foureve.labmanagementbackend.domain.enums.ApplyLabTypeEnum;
import com.foureve.labmanagementbackend.domain.enums.ErrorEnum;
import com.foureve.labmanagementbackend.exception.BusinessException;
import com.foureve.labmanagementbackend.mapper.ApplyLabMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.foureve.labmanagementbackend.utils.AssertUtil;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 实验室申请表 服务实现类
 * </p>
 *
 * @author <a href="https://github.com/gasen1216">gasen</a>
 * @since 2024-04-25
 */
@Service
public class ApplyLabDao extends ServiceImpl<ApplyLabMapper, ApplyLab> {

    @Resource
    @Lazy
    private ApplyLabDao applyLabDao;

    //根据id查询申请表
    public List<ApplyLabVo> getListById(Long userId) {
        // 查询数据库apply-lab
        List<ApplyLab> list = lambdaQuery().eq(ApplyLab::getApplicantId, userId).list();
        return toApplyLabVoList(list);
    }

    public List<ApplyLabVo> toApplyLabVoList(List<ApplyLab> list) {
        return list.stream().map(applyLab -> {
            ApplyLabVo applyLabVo = new ApplyLabVo();
            applyLabVo.setLabNumber(applyLab.getLabNumber());
            applyLabVo.setState(applyLab.getState());
            applyLabVo.setMessage(applyLab.getMessage());
            applyLabVo.setCreateTime(applyLab.getCreateTime());
            applyLabVo.setApplicantId(applyLab.getApplicantId());
            return applyLabVo;
        }).collect(Collectors.toList());
    }

    public void addApplyLabByTeacher(ApplyLabDto applyLabDto) {
        Long userId = RequestHolder.get().getUserId();
        ApplyLab applyLab = new ApplyLab();
        applyLab.setLabNumber(applyLabDto.getLabNumber());
        applyLab.setApplicantId(userId);
        applyLab.setMessage(applyLabDto.getMessage());
        applyLab.setType(ApplyLabTypeEnum.TEACHER.getCode());
        applyLab.setState(ApplyLabStateEnum.UNSCHEDULED.getCode());
        save(applyLab);
    }

    public void updateApplyLabByTeacher(UpdateApplyLabDto applyLabDto) {
        Long userId = RequestHolder.get().getUserId();
        ApplyLab update = applyLabDao.lambdaQuery().eq(ApplyLab::getApplicantId, userId).eq(ApplyLab::getId, applyLabDto.getId()).eq(ApplyLab::getState, ApplyLabStateEnum.UNSCHEDULED.getCode()).one();
        AssertUtil.isNotEmpty(update, "该申请单不存在或已被审核");
        update.setLabNumber(applyLabDto.getLabNumber());
        update.setMessage(applyLabDto.getMessage());
        updateById(update);
    }
}
