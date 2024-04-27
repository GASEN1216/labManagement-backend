package com.foureve.labmanagementbackend.controller;

import com.foureve.labmanagementbackend.domain.dtos.StuApplyLabDto;
import com.foureve.labmanagementbackend.domain.dtos.TeaApplyLabDto;
import com.foureve.labmanagementbackend.domain.vo.resp.ApiResult;
import com.foureve.labmanagementbackend.service.ApplyLabService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author GASEN
 * @date 2024/4/27 17:56
 * @classType description
 */
@RestController
@RequestMapping("/capi/student")
@Api(tags = "学生相关接口")
public class StudentController {

    @Resource
    private ApplyLabService applyLabService;

    @GetMapping("/applyLab/list")
    @ApiOperation("学生所有的申请表")
    public ApiResult getApplyLabList() {
        return ApiResult.success(applyLabService.getApplyLabList());
    }

    @PostMapping("/applyLab/add")
    @ApiOperation("添加申请表")
    public ApiResult addApplyLab(@RequestBody StuApplyLabDto stuApplyLabDto) {
        applyLabService.addApplyLabByStudent(stuApplyLabDto);
        return ApiResult.success();
    }

    @PutMapping("/applyLab/update")
    @ApiOperation("更改未审核的申请表")
    public ApiResult updateApplyLab(@RequestBody StuApplyLabDto stuApplyLabDto) {
        applyLabService.updateApplyLabByStudent(stuApplyLabDto);
        return ApiResult.success();
    }

    @PutMapping("/applyLab/finished/{id}")
    @ApiOperation("使用完毕")
    public ApiResult finishedApplyLab(@PathVariable("id") Long id) {
        applyLabService.finishedApplyLab(id);
        return ApiResult.success();
    }

}
