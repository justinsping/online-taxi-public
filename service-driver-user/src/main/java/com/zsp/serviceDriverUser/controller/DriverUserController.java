package com.zsp.serviceDriverUser.controller;

import com.zsp.dto.DriverUser;
import com.zsp.dto.ResponseResult;
import com.zsp.serviceDriverUser.service.DriverUserService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class DriverUserController {

    @Autowired
    private DriverUserService driverUserService;

    @PostMapping("/addDriverUser")
    public ResponseResult addUser(@RequestBody DriverUser driverUser) {
        log.info("addDriverUser");
        return driverUserService.addDriverUser(driverUser);
    }

    @PutMapping("/addDriverUser")
    public ResponseResult updateUser(@RequestBody DriverUser driverUser) {
        log.info(JSONObject.fromObject(driverUser).toString());
        return driverUserService.updateDriverUser(driverUser);
    }
}
