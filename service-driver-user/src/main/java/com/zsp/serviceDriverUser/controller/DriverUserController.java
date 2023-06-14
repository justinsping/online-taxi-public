package com.zsp.serviceDriverUser.controller;

import com.zsp.constant.DriverCarConstants;
import com.zsp.dto.DriverUser;
import com.zsp.dto.ResponseResult;
import com.zsp.response.DriverUserExistResponse;
import com.zsp.serviceDriverUser.service.DriverUserService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/check-driver/{driverPhone}")
    public ResponseResult<DriverUserExistResponse> getUser(@PathVariable("driverPhone") String driverPhone) {
        ResponseResult<DriverUser> driverUserByPhone = driverUserService.getDriverUserByPhone(driverPhone);
        DriverUser driverUserDb = driverUserByPhone.getData();

        DriverUserExistResponse response = new DriverUserExistResponse();

        int isExist = DriverCarConstants.DRIVER_EXIST;
        if (driverUserDb == null) {
            isExist = DriverCarConstants.DRIVER_NOT_EXIST;
            response.setDriverPhone(driverPhone);
            response.setIsExist(isExist);
        } else {
            response.setDriverPhone(driverUserDb.getDriverPhone());
            response.setIsExist(isExist);
        }

        return ResponseResult.success(response);

    }
}
