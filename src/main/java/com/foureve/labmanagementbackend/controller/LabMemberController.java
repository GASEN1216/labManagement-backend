package com.foureve.labmanagementbackend.controller;

import com.foureve.labmanagementbackend.domain.dtos.FixedEquDto;
import com.foureve.labmanagementbackend.domain.vo.resp.ApiResult;
import com.foureve.labmanagementbackend.service.ApplyEquService;
import com.foureve.labmanagementbackend.service.ApplyLabService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author GASEN
 * @date 2024/4/27 17:29
 * @classType description
 */
@RestController
@RequestMapping("/capi/labMember")
@Api(tags = "实验员相关接口")
public class LabMemberController {
    @Resource
    private ApplyEquService applyEquService;

    @GetMapping("/applyEqu/list")
    @ApiOperation("管理员的实验室的所有保修表")
    public ApiResult getApplyEquList() {
        return ApiResult.success(applyEquService.getApplyEquListByLabMember());
    }

    @PostMapping("/applyEqu/start/{id}")
    @ApiOperation("开始维修")
    public ApiResult startApplyEqu(@PathVariable("id") Long id) {
        applyEquService.startApplyEqu(id);
        return ApiResult.success();
    }

    @PostMapping("/applyEqu/finished")
    @ApiOperation("结束维修")
    public ApiResult finishedApplyEqu(@RequestBody FixedEquDto fixedEquDto) {
        applyEquService.finishedApplyEqu(fixedEquDto);
        return ApiResult.success();
    }

}
