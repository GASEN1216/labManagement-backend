package com.foureve.labmanagementbackend.config;

import com.foureve.labmanagementbackend.interceptor.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
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

//    @Bean("TeacherRoleInterceptor")
//    public HandlerInterceptor getTeacherRoleInterceptor() {
//        return new TeacherRoleInterceptor();
//    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //全局请求拦截器
        registry.addInterceptor(new JwtTokenUserInterceptor())
                .addPathPatterns("/capi/**")
                .excludePathPatterns("/capi/user/login");
//        //教师身份拦截器
//        registry.addInterceptor(new TeacherRoleInterceptor())
//                .addPathPatterns("/capi/teacher/**");
//        //学生身份拦截器
//        registry.addInterceptor(new StudentRoleInterceptor())
//                .addPathPatterns("/capi/student/**");
//        //实验员身份拦截器
//        registry.addInterceptor(new LabMemberRoleInterceptor())
//                .addPathPatterns("/capi/labMember/**");
//        //管理员身份拦截器
//        registry.addInterceptor(new AdminRoleInterceptor())
//                .addPathPatterns("/capi/admin/**");
    }

}
