package com.foureve.labmanagementbackend.service.impl;

import com.foureve.labmanagementbackend.dao.ScheduleDao;
import com.foureve.labmanagementbackend.domain.entity.Schedule;
import com.foureve.labmanagementbackend.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author GASEN
 * @date 2024/4/25 14:06
 * @classType description
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Resource
    private ScheduleDao scheduleDao;
    @Override
    public List<Schedule> getSchedule(Long id) {
        return scheduleDao.getSchedule(id);
    }
}
