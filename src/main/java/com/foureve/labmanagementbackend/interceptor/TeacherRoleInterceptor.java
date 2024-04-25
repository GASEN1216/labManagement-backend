package com.foureve.labmanagementbackend.interceptor;

import com.foureve.labmanagementbackend.Holder.RequestHolder;
import com.foureve.labmanagementbackend.dao.UserDao;
import com.foureve.labmanagementbackend.domain.enums.ErrorEnum;
import com.foureve.labmanagementbackend.domain.enums.RoleEnum;
import com.foureve.labmanagementbackend.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @author GASEN
 * @date 2024/4/26 0:24
 * @classType description
 */
@Component
@Slf4j
public class TeacherRoleInterceptor implements HandlerInterceptor {
    @Resource
    private UserDao userDao;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Long userId = RequestHolder.get().getUserId();
        if (!Objects.equals(userDao.getById(userId).getRole(), RoleEnum.TEACHER.getCode())&& !Objects.equals(userDao.getById(userId).getRole(), RoleEnum.ADMIN.getCode())){
            throw new BusinessException(ErrorEnum.NOPOWER,"权限不足");
        }
        return true;
    }
}
