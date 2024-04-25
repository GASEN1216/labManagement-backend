package com.foureve.labmanagementbackend.controller;

import com.foureve.labmanagementbackend.domain.vo.resp.ApiResult;
import com.foureve.labmanagementbackend.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/capi/admin")
@Api("管理员相关接口")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/semester/list")
    @ApiOperation("列出平台已经有的学期")
    public ApiResult listSemester() {

        return null;
        //return adminService.listSemester(userId);
    }
}
