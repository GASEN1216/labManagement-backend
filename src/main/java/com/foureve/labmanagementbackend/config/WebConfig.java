package com.foureve.labmanagementbackend.config;

import com.foureve.labmanagementbackend.interceptor.JwtTokenUserInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 注册拦截器类
 *
 * @author GASEN
 * @date 2024/3/1 22:50
 * @classType description
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //全局请求拦截器
        registry.addInterceptor(new JwtTokenUserInterceptor())
                .addPathPatterns("/capi/**")
                .excludePathPatterns("/capi/user/login");
    }

}
