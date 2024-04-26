package com.foureve.labmanagementbackend.dao;

import com.foureve.labmanagementbackend.Holder.RequestHolder;
import com.foureve.labmanagementbackend.domain.dtos.ApplyEquDto;
import com.foureve.labmanagementbackend.domain.entity.ApplyEqu;
import com.foureve.labmanagementbackend.domain.entity.ApplyLab;
import com.foureve.labmanagementbackend.domain.entity.vo.ApplyEquVo;
import com.foureve.labmanagementbackend.domain.entity.vo.ApplyLabVo;
import com.foureve.labmanagementbackend.domain.enums.ApplyEquStateEnum;
import com.foureve.labmanagementbackend.mapper.ApplyEquMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 设备维护申请表 服务实现类
 * </p>
 *
 * @author <a href="https://github.com/gasen1216">gasen</a>
 * @since 2024-04-25
 */
@Service
public class ApplyEquDao extends ServiceImpl<ApplyEquMapper, ApplyEqu> {

    public List<ApplyEquVo> getListById(Long userId) {
        // 查询数据库apply-lab
        List<ApplyEqu> list = lambdaQuery().eq(ApplyEqu::getApplicantId, userId).list();
        return toApplyEquVoList(list);
    }

    private List<ApplyEquVo> toApplyEquVoList(List<ApplyEqu> list) {
        return list.stream().map(ApplyEqu::toApplyEquVo).collect(Collectors.toList());
    }
    public void addApplyEquByTeacher(ApplyEquDto applyEquDto) {
        Long userId = RequestHolder.get().getUserId();
        ApplyEqu applyEqu = new ApplyEqu();
        applyEqu.setApplicantId(userId);
        applyEqu.setLabNumber(applyEquDto.getLabNumber());
        applyEqu.setErrorMessage(applyEquDto.getErrorMessage());
        applyEqu.setState(ApplyEquStateEnum.WAIT_APPROVAL.getCode());
        save(applyEqu);
    }
}
