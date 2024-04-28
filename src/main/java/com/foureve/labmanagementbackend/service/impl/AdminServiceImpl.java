package com.foureve.labmanagementbackend.service.impl;

import com.foureve.labmanagementbackend.Holder.RequestHolder;
import com.foureve.labmanagementbackend.cache.CourseCache;
import com.foureve.labmanagementbackend.dao.*;
import com.foureve.labmanagementbackend.domain.dtos.ApproveApplyLabDto;
import com.foureve.labmanagementbackend.domain.dtos.SemesterDto;
import com.foureve.labmanagementbackend.domain.dtos.UserDto;
import com.foureve.labmanagementbackend.domain.entity.*;
import com.foureve.labmanagementbackend.domain.entity.vo.ApplyLabVo;
import com.foureve.labmanagementbackend.domain.entity.vo.UserVo;
import com.foureve.labmanagementbackend.domain.enums.ApplyLabStateEnum;
import com.foureve.labmanagementbackend.domain.enums.ErrorEnum;
import com.foureve.labmanagementbackend.domain.enums.RoleEnum;
import com.foureve.labmanagementbackend.domain.enums.SectionEnum;
import com.foureve.labmanagementbackend.domain.vo.req.RequestInfo;
import com.foureve.labmanagementbackend.domain.vo.resp.ApiResult;
import com.foureve.labmanagementbackend.service.AdminService;
import com.foureve.labmanagementbackend.service.adapter.SemesterAdapter;
import com.foureve.labmanagementbackend.service.adapter.UserAdapter;
import com.foureve.labmanagementbackend.utils.AssertUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private ScheduleDao scheduleDao;

    @Autowired
    private SemesterDao semesterDao;

    @Autowired
    private UserDao userDao;


    /**
     * 查询当前平台已有的学期
     *
     * @return
     */
    @Override
    public ApiResult listSemester() {
        ;
        List<Semester> semesters = semesterDao.listSemester();
        AssertUtil.isNotEmpty(semesters, "当前平台没有学期");
        List<String> list = semesters.stream().map(Semester::getName).distinct().collect(Collectors.toList());
        return ApiResult.success(list);
    }

    /**
     * 添加学期
     *
     * @return
     */
    @Override
    public ApiResult addSemester(SemesterDto semesterDto) {

        // 只能唯一学期, 去表中查，不能唯一
        List<Semester> semesters = semesterDao.listSemester();
        semesters.stream().forEach(semester -> {
            AssertUtil.notEqual(semester.getName(), semesterDto.getName(), "当前学期已存在");
        });

        // 唯一，加入
        Semester semester = SemesterAdapter.newSemester(semesterDto);
        semesterDao.save(semester);
        return ApiResult.success();
    }

    /**
     * 设置当前学期
     *
     * @return
     */
    @Override
    public ApiResult setSemester(SemesterDto semesterDto) {

        // 表中要有该学期
        List<Semester> semesters = semesterDao.listSemester();
        for (Semester semester : semesters) {
            if (semester.getName().equals(semesterDto.getName())) {
                // 存在，设置为当前的学期
                Semester currentSemester = SemesterAdapter.newSemester(semesterDto);
                RequestInfo requestInfo = new RequestInfo(RequestHolder.get().getUserId(), RequestHolder.get().getToken(), currentSemester.getName());
                RequestHolder.set(requestInfo);
                log.info("当前设置的requestInfo为：{}", requestInfo);
                return ApiResult.success();
            }

        }
        return ApiResult.fail(ErrorEnum.SYSTEM_ERROR.getCode(), "没有存在学期");

    }

    /**
     * 添加用户
     *
     * @param userDto
     * @param role
     * @return
     */
    public ApiResult addUser(UserDto userDto, Integer role) {

        NonUserAccount(userDto, "前端参数异常");

        User user = userDao.getByAccount(userDto);
        AssertUtil.isEmpty(user, "用户已存在");

        // 判断是否是当前加入的角色是否为role
        JudgeRole(userDto, role, "添加对应角色选角错误");

        user = UserAdapter.newUser(userDto);
        userDao.save(user);

        return ApiResult.success();

    }

    /**
     * 删除用户
     *
     * @param userDto
     * @param role
     * @return
     */
    @Override
    public ApiResult deleteUser(UserDto userDto, Integer role) {

        NonUserAccount(userDto, "前端参数异常");

        // 判断是否存在这个用户
        User user = getUser(userDto);

        JudgeRole(user, role, "只能删除对应角色");

        userDao.removeById(user.getId());

        return ApiResult.success();
    }

    /**
     * 修改用户
     *
     * @param userDto
     * @param role
     * @return
     */
    @Override
    public ApiResult updateUser(UserDto userDto, Integer role) {

        NonUserAccount(userDto, "前端参数异常");

        // 判断用户是否存在
        User user = getUser(userDto);

        JudgeRole(user, role, "只能修改对应角色");

        user = UserAdapter.updateUser(userDto, user);
        //userDao.updateById(user);
        userDao.updateByUser(user);

        return ApiResult.success(user);
    }

    /**
     * 列出用户
     *
     * @param role
     * @return
     */
    @Override
    public ApiResult listUser(Integer role) {
        List<User> userList = userDao.listUser(role);
        return ApiResult.success(userList);
    }

    /**
     * 根据角色和名字搜索用户
     *
     * @param name
     * @param role
     * @return
     */
    @Override
    public ApiResult searchUser(String name, Integer role) {
        List<User> userList = userDao.searchUser(name, role);
        List<UserVo> userVoList = userList.stream().map(user -> {
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(user, userVo);
            return userVo;
        }).collect(Collectors.toList());
        return ApiResult.success(userVoList);
    }

    @Resource
    private ApplyLabDao applyLabDao;

    @Resource
    private CourseDao courseDao;

    @Override
    @Transactional
    public ApiResult assignSchedule(Long applyLabId) {
        ApplyLab applyLab = applyLabDao.getById(applyLabId);
        AssertUtil.isNotEmpty(applyLab, "没有找到对应的申请");

        int[][][][] courseCache = CourseCache.courseCache;
        int semesterId = applyLab.getSemesterId().intValue();
        Integer beginWeeks = applyLab.getBeginWeeks() - 1;
        Integer endWeeks = applyLab.getEndWeeks() - 1;


        Schedule schedule = new Schedule();
        schedule.setName(applyLab.getScheduleName());
        schedule.setSemesterId((long) semesterId);
        schedule.setClasses(applyLab.getClasses());
        schedule.setTeacherId(applyLab.getApplicantId());

        int weeks = 0;
        int week = 0;
        int section = 0;


        int f = 0;
        for (int i = beginWeeks; i <= endWeeks; i++) {
            for (int j = 0; j < 7; j++) {
                for (int k = 0; k < 6; k++) {
                    if (courseCache[semesterId][i][j][k] == 0) {
                        schedule.setWeeks(i + 1);
                        schedule.setWeek(j + 1);
                        schedule.setSection(SectionEnum.getSectionByCode(k + 1));
                        courseDao.save(Course.builder().semesterId((long) semesterId).weeks(i + 1).week(j + 1).section(SectionEnum.getSectionByCode(k + 1)).build());
                        courseCache[semesterId][i][j][k] = 1;
                        weeks = i;
                        week = j;
                        section = k;
                        f = 1;
                        break;
                    }
                }
                if (f == 1) break;
            }
            if (f == 1) break;
        }

        if (f == 0) {
            return ApiResult.fail(ErrorEnum.SYSTEM_ERROR.getCode(), "课表安排出现问题了，请稍后");
        };


        List<Schedule> scheduleList = new ArrayList<>();

        List<Lab> labList = labDao.selectSuitableLabByLabApply(applyLab, weeks, week, section);
        if (labList.size() == 0) {
            return ApiResult.fail(ErrorEnum.SYSTEM_ERROR.getCode(), "没有找到合适的实验室");
        }
        if (labList.size() == 1) {
            schedule.setLabNumber(labList.get(0).getNumber());
            scheduleDao.save(schedule);
            applyLab.setState(ApplyLabStateEnum.SCHEDULED.getCode());
            applyLabDao.updateById(applyLab);
            return ApiResult.success(schedule);
        }
        for (int i = 0; i < labList.size(); i++) {
            Schedule s = new Schedule();
            BeanUtils.copyProperties(schedule, s);
            s.setLabNumber(labList.get(i).getNumber());
            scheduleList.add(s);
        }
        scheduleDao.saveBatch(scheduleList);
        applyLab.setState(ApplyLabStateEnum.SCHEDULED.getCode());
        applyLabDao.updateById(applyLab);
        return ApiResult.success(scheduleList);
    }


    @Override
    @Transactional
    public ApiResult assignStudentSchedule(ApproveApplyLabDto approveApplyLabDto) {
        Long applyLabId = approveApplyLabDto.getId();
        ApplyLab applyLab = applyLabDao.getById(applyLabId);
        AssertUtil.isNotEmpty(applyLab, "没有找到对应的申请");

        // 如果我不让通过
        if (Objects.equals(approveApplyLabDto.getApprove(), ApplyLabStateEnum.REJECT.getCode())){
            applyLab.setState(ApplyLabStateEnum.REJECT.getCode());
            applyLabDao.updateById(applyLab);
            return ApiResult.success();
        }

        int[][][][] courseCache = CourseCache.courseCache;
        int semesterId = applyLab.getSemesterId().intValue();
        Integer beginWeeks = applyLab.getBeginWeeks() - 1;
        Integer endWeeks = applyLab.getEndWeeks() - 1;


        Schedule schedule = new Schedule();
        schedule.setName(applyLab.getScheduleName());
        schedule.setSemesterId((long) semesterId);
        schedule.setClasses(applyLab.getClasses());
        schedule.setTeacherId(applyLab.getApplicantId());

        int weeks = 0;
        int week = 0;
        int section = 0;


        schedule.setSection(applyLab.getSection());

        int f = 0;
        for (int i = beginWeeks; i <= endWeeks; i++) {
            for (int j = 0; j < 7; j++) {
                int k = SectionEnum.getCodeBySection(applyLab.getSection()) - 1;
                if (courseCache[semesterId][i][j][k] == 0) {
                    schedule.setWeeks(i + 1);
                    schedule.setWeek(j + 1);
                    courseDao.save(Course.builder().semesterId((long) semesterId).weeks(i + 1).week(j + 1).section(applyLab.getSection()).build());
                    courseCache[semesterId][i][j][k] = 1;
                    weeks = i;
                    week = j;
                    section = k;
                    f = 1;
                    break;
                }
            }
            if (f == 1) break;
        }
        // 如果课表已经没了呢
        if (f == 0){
            applyLab.setState(ApplyLabStateEnum.REJECT.getCode());
            applyLabDao.updateById(applyLab);
            AssertUtil.isTrue(false, "课表安排出现问题了，请稍后");
        }

        Lab lab = labDao.selectSuitableLabByLabApplyForStu(applyLab, weeks, week, section);
        if (lab == null) {
            // 状态改为驳回
            applyLab.setState(ApplyLabStateEnum.REJECT.getCode());
            applyLabDao.updateById(applyLab);
        }

        // 实验室存在，给学生分配课表
        schedule.setLabNumber(lab.getNumber());
        scheduleDao.save(schedule);
        applyLab.setState(ApplyLabStateEnum.PASS.getCode());
        applyLabDao.updateById(applyLab);
        return ApiResult.success(schedule);

    }


    @Override
    public ApiResult getStuApplyLab() {

        List<ApplyLab> stuApplyLab = applyLabDao.getStuApplyLab();
        List<ApplyLabVo> applyLabVos = stuApplyLab.stream().map(applyLab -> {
            ApplyLabVo applyLabVo = new ApplyLabVo();
            BeanUtils.copyProperties(applyLab, applyLabVo);
            return applyLabVo;
        }).collect(Collectors.toList());
        return ApiResult.success(applyLabVos);
    }


    /**
     * 重置密码
     *
     * @param userDto
     * @param role
     * @return
     */
    @Override
    public ApiResult resetPassword(UserDto userDto, Integer role) {

        NonUserAccount(userDto, "前端参数异常");

        User user = getUser(userDto);

        JudgeRole(user, role, "只能重置对应角色的密码");

        user = UserAdapter.setPassword(userDto, user);
        userDao.updateById(user);

        return ApiResult.success();
    }

    @Autowired
    private LabDao labDao;


    private static void NonUserAccount(UserDto userDto, String msg) {
        if (userDto.getAccount() == null) {
            AssertUtil.isTrue(false, ErrorEnum.SYSTEM_ERROR, msg);
        }
    }

    private static void JudgeRole(UserDto userDto, Integer role, String msg) {
        if (userDto.getRole() != role) {
            AssertUtil.isTrue(false, ErrorEnum.SYSTEM_ERROR, msg);
        }
    }

    private static void JudgeRole(User user, Integer role, String msg) {
        if (user.getRole() != role) {
            AssertUtil.isTrue(false, ErrorEnum.SYSTEM_ERROR, msg);
        }
    }

    private User getUser(UserDto userDto) {
        User user = userDao.getByAccount(userDto);
        AssertUtil.isNotEmpty(user, "用户不存在");
        return user;
    }

}
