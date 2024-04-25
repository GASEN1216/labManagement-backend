package com.foureve.labmanagementbackend.interceptor;

import com.foureve.labmanagementbackend.Holder.RequestHolder;
import com.foureve.labmanagementbackend.domain.vo.req.RequestInfo;
import com.foureve.labmanagementbackend.utils.AppJwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * jwt令牌校验的拦截器
 */
@Component
@Slf4j
public class JwtTokenUserInterceptor implements HandlerInterceptor {

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        RequestHolder.remove();
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if(Objects.isNull(token)) {
            response.sendRedirect("/capi/user/login");
            return false;
        }
        //5. 判断token是否有效
        try{
            Claims claimsBody = AppJwtUtil.getClaimsBody(token);
            int result = AppJwtUtil.verifyToken(claimsBody);

            if(result == 1 || result == 2){
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                return false;
            }

            //获得token解析后中的用户信息
            Object userId = null;
            if (claimsBody != null) {
                userId = claimsBody.get("id");
            }
            RequestHolder.set(new RequestInfo(Long.valueOf(userId.toString()), token, null));
        }catch (Exception e){
            log.info("解析token失败："+e);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }
        //6. 放行
        return true;
    }
}