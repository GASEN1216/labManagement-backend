package com.foureve.labmanagementbackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@MapperScan("com.foureve.labmanagementbackend.mapper")
@EnableRedisHttpSession
public class LabManagementBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabManagementBackendApplication.class, args);
    }

}
