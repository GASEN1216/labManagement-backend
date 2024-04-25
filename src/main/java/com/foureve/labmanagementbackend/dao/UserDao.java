package com.foureve.labmanagementbackend.dao;

import com.foureve.labmanagementbackend.domain.dtos.UserDto;
import com.foureve.labmanagementbackend.domain.entity.User;
import com.foureve.labmanagementbackend.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author <a href="https://github.com/gasen1216">gasen</a>
 * @since 2024-04-25
 */
@Service
public class UserDao extends ServiceImpl<UserMapper, User> {

    public User selectUser(String account) {
        return lambdaQuery()
                .eq(User::getAccount, account)
                .eq(User::getIsDelete, 0)
                .one();
    }

    public User getByAccount(UserDto userDto) {
        return lambdaQuery()
                .eq(User::getAccount, userDto.getAccount())
                .eq(User::getIsDelete, 0)
                .one();
    }


    public boolean updateByUser(User user) {
        return lambdaUpdate()
                .eq(User::getAccount, user.getAccount())
                .update(user);
    }

    public List<User> listUser(Integer role) {
        return lambdaQuery()
                .eq(User::getRole, role)
                .eq(User::getIsDelete, 0)
                .list();
    }

    public List<User> searchUser(String name, Integer role) {
        return lambdaQuery().eq(User::getRole, role)
                .eq(User::getIsDelete, 0)
                .like(User::getName, name)
                .list();
    }
}
