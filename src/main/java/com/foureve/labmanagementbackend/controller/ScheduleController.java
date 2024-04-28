package com.foureve.labmanagementbackend.controller;

import com.foureve.labmanagementbackend.domain.vo.resp.ApiResult;
import com.foureve.labmanagementbackend.service.ScheduleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author GASEN
 * @date 2024/4/27 21:23
 * @classType description
 */
@RestController
@RequestMapping("/capi/schedule")
@Api(tags = "课表相关接口")
public class ScheduleController {

    @Resource
    private ScheduleService scheduleService;
    @GetMapping("/{id}")
    @ApiOperation("获取课表")
    public ApiResult getSchedule(@PathVariable Long id) {
        return ApiResult.success(scheduleService.getSchedule(id));
    }

}
