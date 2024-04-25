package com.foureve.labmanagementbackend.service.impl;

import com.foureve.labmanagementbackend.dao.UserDao;
import com.foureve.labmanagementbackend.domain.dtos.LoginDto;
import com.foureve.labmanagementbackend.domain.entity.User;
import com.foureve.labmanagementbackend.domain.vo.resp.ApiResult;
import com.foureve.labmanagementbackend.service.UserService;
import com.foureve.labmanagementbackend.service.adapter.UserAdapter;
import com.foureve.labmanagementbackend.utils.AssertUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;


/**
 * @author GASEN
 * @date 2024/4/25 14:06
 * @classType description
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public ApiResult login(LoginDto loginDto) {

        // 判断前端传来的数据一致性
        AssertUtil.isNotEmpty(loginDto.getPassword(), loginDto.getAccount(), "账户或密码为空");

        // 数据库中查找用户
        User user = userDao.selectUser(loginDto.getAccount());
        AssertUtil.isNotEmpty(user, "用户不存在");

        // 对比密码
        comparePassword(loginDto, user);

        // 返回前端数据
        return UserAdapter.returnVo(user);


    }

    public void comparePassword(LoginDto loginDto, User user) {
        String password = loginDto.getPassword();
        String salt = user.getSalt();
        password = DigestUtils.md5DigestAsHex((password + salt).getBytes());
        AssertUtil.equal(password, user.getPassword(), "密码错误");
    }

    public static void main(String[] args) {
        String password = "123456";
        String s = DigestUtils.md5DigestAsHex((password + 123456).getBytes());
        System.out.println(s);
    }
}
