package com.foureve.labmanagementbackend.service;


import com.foureve.labmanagementbackend.domain.dtos.SemesterDto;
import com.foureve.labmanagementbackend.domain.vo.resp.ApiResult;

public interface AdminService {
    ApiResult listSemester();

    ApiResult addSemester(SemesterDto semesterDto);

    ApiResult setSemester(SemesterDto semesterDto);
}
