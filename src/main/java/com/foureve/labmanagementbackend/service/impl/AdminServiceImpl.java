package com.foureve.labmanagementbackend.service.impl;

import com.foureve.labmanagementbackend.Holder.RequestHolder;
import com.foureve.labmanagementbackend.dao.AdminDao;
import com.foureve.labmanagementbackend.dao.ScheduleDao;
import com.foureve.labmanagementbackend.dao.SemesterDao;
import com.foureve.labmanagementbackend.dao.UserDao;
import com.foureve.labmanagementbackend.domain.dtos.SemesterDto;
import com.foureve.labmanagementbackend.domain.dtos.UserDto;
import com.foureve.labmanagementbackend.domain.entity.Semester;
import com.foureve.labmanagementbackend.domain.entity.User;
import com.foureve.labmanagementbackend.domain.entity.vo.UserVo;
import com.foureve.labmanagementbackend.domain.enums.ErrorEnum;
import com.foureve.labmanagementbackend.domain.enums.RoleEnum;
import com.foureve.labmanagementbackend.domain.vo.req.RequestInfo;
import com.foureve.labmanagementbackend.domain.vo.resp.ApiResult;
import com.foureve.labmanagementbackend.service.AdminService;
import com.foureve.labmanagementbackend.service.adapter.SemesterAdapter;
import com.foureve.labmanagementbackend.service.adapter.UserAdapter;
import com.foureve.labmanagementbackend.utils.AssertUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
     * @return
     */
    @Override
    public ApiResult listSemester() {;
        List<Semester> semesters = semesterDao.listSemester();
        AssertUtil.isNotEmpty(semesters,"当前平台没有学期");
        List<String> list = semesters.stream().map(Semester::getName).distinct().collect(Collectors.toList());
        return ApiResult.success(list);
    }

    /**
     * 添加学期
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
     * @param userDto
     * @param role
     * @return
     */
    public ApiResult addUser(UserDto userDto, Integer role){

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

    /**
     * 重置密码
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

    private static void NonUserAccount(UserDto userDto, String msg) {
        if (userDto.getAccount() == null){
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
