package com.foureve.labmanagementbackend.dao;

import com.foureve.labmanagementbackend.domain.entity.User;
import com.foureve.labmanagementbackend.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
