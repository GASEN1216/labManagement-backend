package com.foureve.labmanagementbackend.controller;

import com.foureve.labmanagementbackend.Holder.RequestHolder;
import com.foureve.labmanagementbackend.domain.dtos.SemesterDto;
import com.foureve.labmanagementbackend.domain.dtos.UserDto;
import com.foureve.labmanagementbackend.domain.vo.resp.ApiResult;
import com.foureve.labmanagementbackend.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/capi/admin")
@Api("管理员相关接口")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * 列出平台已经有的学期
     * @return
     */
    @GetMapping("/semester/list")
    @ApiOperation("列出平台已经有的学期")
    public ApiResult listSemester() {
        return adminService.listSemester();
    }

    /**
     * 添加新学期
     * @return
     */
    @PostMapping("/semester/add")
    @ApiOperation("添加新学期")
    public ApiResult addSemester(@RequestBody SemesterDto semesterDto) {
        return adminService.addSemester(semesterDto);
    }

    @PostMapping("/semester/set")
    @ApiOperation("设置当前平台的学期")
    public ApiResult setSemester(@RequestBody SemesterDto semesterDto) {
        return adminService.setSemester(semesterDto);
    }

//    @PostMapping("/platform/technician/add")
//    public ApiResult addTechnician(@RequestBody UserDto userDto) {
//        return adminService.addTechnician(userDto);
//    }


}
