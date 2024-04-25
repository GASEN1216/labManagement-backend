package com.foureve.labmanagementbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.foureve.labmanagementbackend.domain.entity.Semester;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

@Mapper
public interface SemesterMapper extends BaseMapper<Semester> {
}
