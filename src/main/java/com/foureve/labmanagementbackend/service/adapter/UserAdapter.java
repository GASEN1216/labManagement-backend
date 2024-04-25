package com.foureve.labmanagementbackend.service.adapter;

import com.foureve.labmanagementbackend.dao.UserDao;
import com.foureve.labmanagementbackend.domain.dtos.UserDto;
import com.foureve.labmanagementbackend.domain.entity.User;
import com.foureve.labmanagementbackend.domain.entity.vo.UserVo;
import com.foureve.labmanagementbackend.domain.vo.resp.ApiResult;
import com.foureve.labmanagementbackend.utils.AppJwtUtil;
import org.springframework.beans.BeanUtils;

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

//    public static User newUser(UserDto userDto){
//        User user = new User();
//        BeanUtils.copyProperties(userDto, user);
//        user.
//    }
}
