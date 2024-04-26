package com.foureve.labmanagementbackend.dao;

import com.foureve.labmanagementbackend.domain.entity.ApplyLab;
import com.foureve.labmanagementbackend.domain.entity.Lab;
import com.foureve.labmanagementbackend.domain.entity.Schedule;
import com.foureve.labmanagementbackend.domain.enums.ErrorEnum;
import com.foureve.labmanagementbackend.domain.enums.SectionEnum;
import com.foureve.labmanagementbackend.exception.BusinessException;
import com.foureve.labmanagementbackend.mapper.LabMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.foureve.labmanagementbackend.utils.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 实验室 服务实现类
 * </p>
 *
 * @author <a href="https://github.com/gasen1216">gasen</a>
 * @since 2024-04-25
 */
@Service
public class LabDao extends ServiceImpl<LabMapper, Lab> {

    @Resource
    private ScheduleDao scheduleDao;


    public List<Lab> selectSuitableLabByLabApply(ApplyLab applyLab, int weeks, int week, int section) {
        Long assignNum = applyLab.getStuCount();
        List<Lab> res = new ArrayList<>();

        //已用
        List<Long> UsedLabs = scheduleDao
                .lambdaQuery()
                .eq(Schedule::getSemesterId, applyLab.getSemesterId())
                .eq(Schedule::getWeeks, weeks)
                .eq(Schedule::getWeek, week)
                .eq(Schedule::getSection, SectionEnum.getSectionByCode(section))
                .list().stream().map(Schedule::getLabNumber).collect(Collectors.toList());

        List<Long> UsedLabList = null;
        if(UsedLabs.size() != 0) {
            //过滤已用类型
            UsedLabList = lambdaQuery()
                    .eq(Lab::getType, applyLab.getType())
                    .eq(Lab::getIsDelete, 0)
                    .in(Lab::getNumber, UsedLabs)
                    .list().stream().map(Lab::getNumber).collect(Collectors.toList());
        }



        //容量可用
        List<Lab> can = lambdaQuery()
                .eq(Lab::getType, applyLab.getLabType())
                .ge(Lab::getEquCount, assignNum)
                .eq(Lab::getIsDelete, 0)
                .orderByAsc(Lab::getEquCount)
                .list();

        if(UsedLabList!=null) {
            //完全可用
            for (Long usedLab : UsedLabList) {
                for (Lab lab : can) {
                    if (lab.getNumber().equals(usedLab)){
                        can.remove(lab);
                        break;
                    }
                }
            }
        }

        if (!can.isEmpty()){
            List<Lab> labList = new ArrayList<>();
            labList.add(can.get(0));
            return labList;
        }


        // 多容量可用
        List<Lab> labList;
        labList =  lambdaQuery()
                .eq(Lab::getType, applyLab.getLabType())
                .lt(Lab::getEquCount, assignNum)
                .eq(Lab::getIsDelete, 0)
                .orderByAsc(Lab::getEquCount)
                .list();

        if(UsedLabList!=null) {
            // 多完全可用
            //完全可用
            for (Long usedLab : UsedLabList) {
                for (Lab lab : labList) {
                    if (lab.getNumber().equals(usedLab)){
                        labList.remove(lab);
                        break;
                    }
                }
            }
        }

        // 挑出可用的
        for (Lab lab : labList) {
            if (assignNum > 0){
                res.add(lab);
                assignNum -= lab.getEquCount();
                updateById(lab);
            }
        }

        if(assignNum > 0) throw new BusinessException(ErrorEnum.NO_LAB_AVAILABLE);


        return res;


    }
}
