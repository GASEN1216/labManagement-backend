package com.foureve.labmanagementbackend.controller;

import com.foureve.labmanagementbackend.domain.dtos.ApplyEquDto;
import com.foureve.labmanagementbackend.domain.dtos.TeaApplyLabDto;
import com.foureve.labmanagementbackend.domain.dtos.UpdateApplyLabDto;
import com.foureve.labmanagementbackend.domain.vo.resp.ApiResult;
import com.foureve.labmanagementbackend.service.ApplyEquService;
import com.foureve.labmanagementbackend.service.ApplyLabService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation("教师所有申请表列表")
    public ApiResult getApplyLabList() {
        return ApiResult.success(applyLabService.getApplyLabList());
    }

    @PostMapping("/applyLab/add")
    @ApiOperation("添加申请表")
    public ApiResult addApplyLab(@RequestBody TeaApplyLabDto applyLabDto) {
        applyLabService.addApplyLabByTeacher(applyLabDto);
        return ApiResult.success();
    }

    @PutMapping("/applyLab/update")
    @ApiOperation("更改未审核的申请表")
    public ApiResult updateApplyLab(@RequestBody UpdateApplyLabDto applyLabDto) {
        applyLabService.updateApplyLabByTeacher(applyLabDto);
        return ApiResult.success();
    }

    @GetMapping("/applyEqu/list")
    @ApiOperation("教师所有的维修表列表")
    public ApiResult getApplyEquList() {
        return ApiResult.success(applyEquService.getApplyEquList());
    }

    @PostMapping("/applyEqu/add")
    @ApiOperation("添加维修表")
    public ApiResult addApplyEqu(@RequestBody ApplyEquDto applyEquDto) {
        applyEquService.addApplyEquByTeacher(applyEquDto);
        return ApiResult.success();
    }



}
