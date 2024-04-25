package com.foureve.labmanagementbackend.controller;


import com.foureve.labmanagementbackend.domain.dtos.LoginDto;
import com.foureve.labmanagementbackend.domain.vo.resp.ApiResult;
import com.foureve.labmanagementbackend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@Api(tags = "用户相关接口")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    @ApiOperation("用户登录")
    public ApiResult login(@RequestBody LoginDto loginDto){
        return userService.login(loginDto);
    }

}

