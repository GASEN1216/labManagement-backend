package com.foureve.labmanagementbackend.dao;

import com.foureve.labmanagementbackend.domain.entity.Course;
import com.foureve.labmanagementbackend.mapper.CourseMapper;
import com.foureve.labmanagementbackend.service.ICourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author <a href="https://github.com/gasen1216">gasen</a>
 * @since 2024-04-26
 */
@Service
public class CourseDao extends ServiceImpl<CourseMapper, Course> implements ICourseService {

}
