package com.foureve.labmanagementbackend.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.foureve.labmanagementbackend.domain.entity.Semester;
import com.foureve.labmanagementbackend.mapper.SemesterMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SemesterDao extends ServiceImpl<SemesterMapper, Semester> {
    public List<Semester> listSemester() {
        return lambdaQuery().list();
    }
}
