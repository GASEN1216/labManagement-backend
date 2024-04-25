package com.foureve.labmanagementbackend.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.foureve.labmanagementbackend.domain.entity.User;
import com.foureve.labmanagementbackend.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class AdminDao extends ServiceImpl<UserMapper, User> {
}
