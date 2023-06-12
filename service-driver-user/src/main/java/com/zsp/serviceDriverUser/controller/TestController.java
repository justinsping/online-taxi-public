package com.zsp.serviceDriverUser.controller;

import com.zsp.dto.ResponseResult;
import com.zsp.serviceDriverUser.service.DriverUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private DriverUserService driverUserService;

    @RequestMapping("/test")
    public String test() {
        return "service driver user";
    }

    @RequestMapping("/test-db")
    public ResponseResult testDb() {
        return driverUserService.testGetDriverUser();
    }
}
