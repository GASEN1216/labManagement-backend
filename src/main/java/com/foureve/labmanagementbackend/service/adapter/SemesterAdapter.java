package com.foureve.labmanagementbackend.service.adapter;

import com.foureve.labmanagementbackend.domain.dtos.SemesterDto;
import com.foureve.labmanagementbackend.domain.entity.Semester;

public class SemesterAdapter {
    public static Semester newSemester(SemesterDto semesterDto) {
        Semester semester = new Semester();
        semester.setName(semesterDto.getName());
        return semester;
    }
}
