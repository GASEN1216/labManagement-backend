package com.foureve.labmanagementbackend.service;


import com.foureve.labmanagementbackend.domain.dtos.ApproveApplyLabDto;
import com.foureve.labmanagementbackend.domain.dtos.SemesterDto;
import com.foureve.labmanagementbackend.domain.dtos.UserDto;
import com.foureve.labmanagementbackend.domain.vo.resp.ApiResult;

public interface AdminService {
    ApiResult listSemester();

    ApiResult addSemester(SemesterDto semesterDto);

    ApiResult setSemester(SemesterDto semesterDto);

    ApiResult resetPassword(UserDto userDto, Integer code);

    ApiResult addUser(UserDto userDto, Integer code);

    ApiResult deleteUser(UserDto userDto, Integer code);

    ApiResult updateUser(UserDto userDto, Integer code);

    ApiResult listUser(Integer role);

    ApiResult searchUser(String name, Integer role);


    ApiResult assignSchedule(Long applyLabId);

    ApiResult getStuApplyLab();

    ApiResult assignStudentSchedule(ApproveApplyLabDto approveApplyLabDto);
}
