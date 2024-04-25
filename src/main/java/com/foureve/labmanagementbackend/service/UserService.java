package com.foureve.labmanagementbackend.service;

import com.foureve.labmanagementbackend.domain.dtos.LoginDto;
import com.foureve.labmanagementbackend.domain.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.foureve.labmanagementbackend.domain.vo.resp.ApiResult;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author <a href="https://github.com/gasen1216">gasen</a>
 * @since 2024-04-25
 */
public interface UserService {

    ApiResult login(LoginDto loginDto);
}
