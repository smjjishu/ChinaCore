package com.example.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan(basePackages = {"com.example.project.mysql"})
@EnableSwagger2
public class ChinaCoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChinaCoreApplication.class, args);
    }
}
