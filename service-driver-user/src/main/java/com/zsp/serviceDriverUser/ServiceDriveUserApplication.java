package com.zsp.serviceDriverUser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.zsp.serviceDriverUser.mapper")
public class ServiceDriveUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceDriveUserApplication.class, args);
    }
}
