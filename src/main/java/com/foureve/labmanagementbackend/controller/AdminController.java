package com.foureve.labmanagementbackend.controller;

import com.foureve.labmanagementbackend.Holder.RequestHolder;
import com.foureve.labmanagementbackend.dao.UserDao;
import com.foureve.labmanagementbackend.domain.dtos.SemesterDto;
import com.foureve.labmanagementbackend.domain.dtos.UserDto;
import com.foureve.labmanagementbackend.domain.entity.User;
import com.foureve.labmanagementbackend.domain.enums.ErrorEnum;
import com.foureve.labmanagementbackend.domain.enums.RoleEnum;
import com.foureve.labmanagementbackend.domain.vo.resp.ApiResult;
import com.foureve.labmanagementbackend.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/capi/admin")
@Api(tags = "管理员相关接口")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserDao userDao;


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
        ApiResult noneAuthorizer = getNoneAuthorizer();
        if (noneAuthorizer != null){
            return noneAuthorizer;
        }
        return adminService.addSemester(semesterDto);
    }

    private ApiResult getNoneAuthorizer() {
        Long userId = RequestHolder.get().getUserId();
        User user = userDao.getById(userId);
        // 如果不是管理员就报错了
        if (!Objects.equals(user.getRole(), RoleEnum.ADMIN.getCode())){
            return ApiResult.fail(ErrorEnum.SYSTEM_ERROR.getCode(), "您没有权限添加");
        }
        return null;
    }

    /**
     * 设置当前平台的学期
     * @return
     */
    @PostMapping("/semester/set")
    @ApiOperation("设置当前平台的学期")
    public ApiResult setSemester(@RequestBody SemesterDto semesterDto) {
        ApiResult noneAuthorizer = getNoneAuthorizer();
        if (noneAuthorizer != null){
            return noneAuthorizer;
        }
        return adminService.setSemester(semesterDto);
    }

    /**
     * 添加实验员
     * @return
     */
    @PostMapping("/platform/technician/add")
    @ApiOperation("添加实验员")
    public ApiResult addTechnician(@RequestBody UserDto userDto) {
        ApiResult noneAuthorizer = getNoneAuthorizer();
        if (noneAuthorizer != null){
            return noneAuthorizer;
        }
        return adminService.addUser(userDto, RoleEnum.LAB_MEMBER.getCode());
    }

    /**
     * 删除实验员
     * @return
     */
    @PostMapping("/platform/technician/delete")
    @ApiOperation("删除实验员")
    public ApiResult deleteTechnician(@RequestBody UserDto userDto) {
        ApiResult noneAuthorizer = getNoneAuthorizer();
        if (noneAuthorizer != null){
            return noneAuthorizer;
        }
        return adminService.deleteUser(userDto, RoleEnum.LAB_MEMBER.getCode());
    }

    /**
     * 修改实验员信息
     * @return
     */
    @PostMapping("/platform/technician/update")
    @ApiOperation("修改实验员信息")
    public ApiResult updateTechnician(@RequestBody UserDto userDto) {
        ApiResult noneAuthorizer = getNoneAuthorizer();
        if (noneAuthorizer != null){
            return noneAuthorizer;
        }
        return adminService.updateUser(userDto, RoleEnum.LAB_MEMBER.getCode());
    }

    /**
     * 重置密码
     * @return
     */
    @PostMapping("/platform/technician/resetPassword")
    @ApiOperation("重置密码")
    public ApiResult resetPassword(@RequestBody UserDto userDto) {
        ApiResult noneAuthorizer = getNoneAuthorizer();
        if (noneAuthorizer != null){
            return noneAuthorizer;
        }
        return adminService.resetPassword(userDto, RoleEnum.LAB_MEMBER.getCode());
    }

    @PostMapping("/platform/teacher/add")
    @ApiOperation("添加教师")
    public ApiResult addTeacher(@RequestBody UserDto userDto) {
        ApiResult noneAuthorizer = getNoneAuthorizer();
        if (noneAuthorizer != null){
            return noneAuthorizer;
        }
        return adminService.addUser(userDto, RoleEnum.TEACHER.getCode());
    }

    @PostMapping("/platform/teacher/delete")
    @ApiOperation("删除教师")
    public ApiResult deleteTeacher(@RequestBody UserDto userDto) {
        ApiResult noneAuthorizer = getNoneAuthorizer();
        if (noneAuthorizer != null){
            return noneAuthorizer;
        }
        return adminService.deleteUser(userDto, RoleEnum.TEACHER.getCode());
    }

    @PostMapping("/platform/teacher/update")
    @ApiOperation("修改教师信息")
    public ApiResult updateTeacher(@RequestBody UserDto userDto) {
        ApiResult noneAuthorizer = getNoneAuthorizer();
        if (noneAuthorizer != null){
            return noneAuthorizer;
        }
        return adminService.updateUser(userDto, RoleEnum.TEACHER.getCode());
    }

    @PostMapping("/platform/teacher/resetPassword")
    @ApiOperation("教师重置密码")
    public ApiResult resetTeacherPassword(@RequestBody UserDto userDto) {
        ApiResult noneAuthorizer = getNoneAuthorizer();
        if (noneAuthorizer != null){
            return noneAuthorizer;
        }
        return adminService.resetPassword(userDto, RoleEnum.TEACHER.getCode());
    }

    @PostMapping("/platform/student/add")
    @ApiOperation("添加学生")
    public ApiResult addStudent(@RequestBody UserDto userDto) {
        ApiResult noneAuthorizer = getNoneAuthorizer();
        if (noneAuthorizer != null){
            return noneAuthorizer;
        }
        return adminService.addUser(userDto, RoleEnum.STUDENT.getCode());
    }

    @PostMapping("/platform/student/delete")
    @ApiOperation("删除学生")
    public ApiResult deleteStudent(@RequestBody UserDto userDto) {
        ApiResult noneAuthorizer = getNoneAuthorizer();
        if (noneAuthorizer != null){
            return noneAuthorizer;
        }
        return adminService.deleteUser(userDto, RoleEnum.STUDENT.getCode());
    }

    @PostMapping("/platform/student/update")
    @ApiOperation("修改学生信息")
    public ApiResult updateStudent(@RequestBody UserDto userDto) {
        ApiResult noneAuthorizer = getNoneAuthorizer();
        if (noneAuthorizer != null){
            return noneAuthorizer;
        }
        return adminService.updateUser(userDto, RoleEnum.STUDENT.getCode());
    }

    @PostMapping("/platform/student/resetPassword")
    @ApiOperation("学生重置密码")
    public ApiResult resetStudentPassword(@RequestBody UserDto userDto) {
        ApiResult noneAuthorizer = getNoneAuthorizer();
        if (noneAuthorizer != null){
            return noneAuthorizer;
        }
        return adminService.resetPassword(userDto, RoleEnum.STUDENT.getCode());
    }

    @GetMapping("/platform/user/list")
    @ApiOperation("获取用户列表")
    public ApiResult listUser(@RequestParam(value = "role", required = false) Integer role) {
        ApiResult noneAuthorizer = getNoneAuthorizer();
        if (noneAuthorizer != null){
            return noneAuthorizer;
        }
        return adminService.listUser(role);
    }

    @GetMapping("/platform/user/search")
    @ApiOperation("搜索用户")
    public ApiResult searchUser(@RequestParam(value = "name", required = false) String name,
                                @RequestParam(value = "role", required = false) Integer role) {
        return adminService.searchUser(name, role);
    }

    /**
     * 将实验课安排到实验室
     */
    @PostMapping("/assign_lab/{id}")
    @ApiOperation("将实验课安排到实验室")
    public ApiResult assignSchedule(@PathVariable("id") Integer ApplyLabId) {
        ApiResult noneAuthorizer = getNoneAuthorizer();
        if (noneAuthorizer != null){
            return noneAuthorizer;
        }
        return adminService.assignSchedule(ApplyLabId);
    }


}
