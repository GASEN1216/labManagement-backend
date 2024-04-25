package com.foureve.labmanagementbackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.foureve.labmanagementbackend.mapper")
public class LabManagementBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabManagementBackendApplication.class, args);
    }

}
