package com.foureve.labmanagementbackend.dao;

import com.foureve.labmanagementbackend.domain.entity.Schedule;
import com.foureve.labmanagementbackend.mapper.ScheduleMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author <a href="https://github.com/gasen1216">gasen</a>
 * @since 2024-04-25
 */
@Service
public class ScheduleDao extends ServiceImpl<ScheduleMapper, Schedule> {

    public List<Schedule> listSemester() {
        return lambdaQuery()
                .eq(Schedule::getIsDelete, 0)
                .list();
    }

    public List<Schedule> getSchedule(Long id) {
        return lambdaQuery().eq(Schedule::getSemesterId, id)
                .eq(Schedule::getIsDelete, 0)
                .orderByAsc(Schedule::getWeeks, Schedule::getWeek, Schedule::getSection)
                .list();
    }
}
