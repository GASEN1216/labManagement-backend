package com.foureve.labmanagementbackend.controller;
import java.util.Date;


import com.foureve.labmanagementbackend.dao.UserDao;
import com.foureve.labmanagementbackend.domain.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author <a href="https://github.com/gasen1216">gasen</a>
 * @since 2024-04-25
 */
@RestController
@RequestMapping("/capi/user")
public class UserController {

    @Resource
    private UserDao userDao;

    @GetMapping
    public Integer test() {
        User user = new User();
        user.setAccount(202125310207L);
        user.setPassword("123456");
        user.setName("gasen");
        user.setRole(0);
        user.setMajor("计算机");
        user.setClasses("二班");
        user.setSalt("123");


        userDao.save(user);
        return userDao.lambdaQuery().count();
    }
}

