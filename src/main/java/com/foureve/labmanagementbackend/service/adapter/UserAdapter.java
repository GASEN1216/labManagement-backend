package com.foureve.labmanagementbackend.service.adapter;

import com.foureve.labmanagementbackend.domain.dtos.UserDto;
import com.foureve.labmanagementbackend.domain.entity.User;
import com.foureve.labmanagementbackend.domain.entity.vo.UserVo;
import com.foureve.labmanagementbackend.domain.vo.resp.ApiResult;
import com.foureve.labmanagementbackend.utils.AppJwtUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UserAdapter {

    public static ApiResult<Map> returnVo(User user)
    {
        Map<String, Object> map = new HashMap<>();
        map.put("token", AppJwtUtil.getToken(user.getId()));
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        map.put("user", userVo);
        return ApiResult.success(map);
    }

    public static User newUser(UserDto userDto){

        User user = new User();
        BeanUtils.copyProperties(userDto, user);

        // 生成默认密码
        String password = null;
        if (userDto.getPassword() != null) {
            password = DigestUtils.md5DigestAsHex((userDto.getPassword() + "123").getBytes());
            user.setPassword(password);
        }

        // 设置默认密码
        password = DigestUtils.md5DigestAsHex((user.getAccount() + "123").getBytes());
        user.setPassword(password);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setIsDelete(0);
        user.setSalt("123");

        return user;
    }

    public static User updateUser(UserDto userDto, User user) {
        if (userDto.getName() != null){
            user.setName(userDto.getName());
        }
        if (userDto.getMajor() != null){
            user.setMajor(userDto.getMajor());
        }
        if (userDto.getClasses() != null){
            user.setClasses(userDto.getClasses());
        }
        if (userDto.getReputation() != null){
            user.setReputation(userDto.getReputation());
        }
        user.setUpdateTime(new Date());
        return user;
    }

    public static User setPassword(UserDto userDto, User user) {

        if (userDto.getPassword() != null) {
            String password = DigestUtils.md5DigestAsHex((userDto.getPassword() + "123").getBytes());
            user.setPassword(password);
        }
        return user;
    }
}
