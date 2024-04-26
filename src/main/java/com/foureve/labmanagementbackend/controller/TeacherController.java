package com.foureve.labmanagementbackend.controller;

import com.foureve.labmanagementbackend.domain.dtos.ApplyEquDto;
import com.foureve.labmanagementbackend.domain.dtos.ApplyLabDto;
import com.foureve.labmanagementbackend.domain.dtos.UpdateApplyLabDto;
import com.foureve.labmanagementbackend.domain.entity.ApplyLab;
import com.foureve.labmanagementbackend.domain.vo.resp.ApiResult;
import com.foureve.labmanagementbackend.service.ApplyEquService;
import com.foureve.labmanagementbackend.service.ApplyLabService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 教师接口方法
 * @author GASEN
 * @date 2024/4/25 16:09
 * @classType description
 */
@RestController
@RequestMapping("/capi/teacher")
@Api(tags = "教师相关接口")
public class TeacherController {
    @Resource
    private ApplyLabService applyLabService;
    @Resource
    private ApplyEquService applyEquService;

    @GetMapping("/applyLab/list")
    public ApiResult getApplyLabList() {
        return ApiResult.success(applyLabService.getApplyLabList());
    }

    @PostMapping("/applyLab/add")
    public ApiResult addApplyLab(@RequestBody ApplyLabDto applyLabDto) {
        applyLabService.addApplyLabByTeacher(applyLabDto);
        return ApiResult.success();
    }

    @PutMapping("/applyLab/update")
    public ApiResult updateApplyLab(@RequestBody UpdateApplyLabDto applyLabDto) {
        applyLabService.updateApplyLabByTeacher(applyLabDto);
        return ApiResult.success();
    }

    @GetMapping("/applyEqu/list")
    public ApiResult getApplyEquList() {
        return ApiResult.success(applyEquService.getApplyEquList());
    }

    @PostMapping("/applyEqu/add")
    public ApiResult addApplyLab(@RequestBody ApplyEquDto applyEquDto) {
        applyEquService.addApplyEquByTeacher(applyEquDto);
        return ApiResult.success();
    }



}
