package com.foureve.labmanagementbackend.service;

import com.foureve.labmanagementbackend.domain.entity.Schedule;
import com.baomidou.mybatisplus.extension.service.IService;
import com.foureve.labmanagementbackend.domain.vo.resp.ApiResult;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author <a href="https://github.com/gasen1216">gasen</a>
 * @since 2024-04-25
 */
public interface ScheduleService  {

    List<Schedule> getSchedule(Long id);
}
