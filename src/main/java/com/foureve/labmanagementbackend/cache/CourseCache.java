package com.foureve.labmanagementbackend.cache;

import com.foureve.labmanagementbackend.dao.CourseDao;
import com.foureve.labmanagementbackend.dao.SemesterDao;
import com.foureve.labmanagementbackend.domain.enums.SectionEnum;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component
public class CourseCache {

    @Resource
    private SemesterDao semesterDao;
    @Resource
    private CourseDao courseDao;

    public static int[][][][] courseCache;

    @PostConstruct
    public void init() {
        Integer count = semesterDao.lambdaQuery().select().count();
        courseCache = new int[count][20][7][6];
        courseDao.lambdaQuery().select().list().forEach(course -> {
            courseCache[(int) (course.getSemesterId() - 1)][course.getWeeks()-1][course.getWeek()-1][SectionEnum.getCodeBySection(course.getSection())-1] = 1;
        });
    }



}
