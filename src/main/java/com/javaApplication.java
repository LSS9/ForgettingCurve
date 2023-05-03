package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@MapperScan("com.project.mapper")
@EnableJdbcRepositories(basePackages = "com.project.repoistry")
public class javaApplication {

    public static void main(String[] args) {
        SpringApplication.run(javaApplication.class, args);
    }







}
