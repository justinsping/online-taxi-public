package com.zsp.servicepassengeruser.controller;

import com.zsp.dto.ResponseResult;
import com.zsp.request.VerifyCodeDTO;
import com.zsp.servicepassengeruser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/user")
    public ResponseResult loginOrRegister(@RequestBody VerifyCodeDTO verifyCodeDTO) {

        String passengerPhone = verifyCodeDTO.getPassengerPhone();
        System.out.println("手机号" + passengerPhone);

        return userService.loginOrRegister(passengerPhone);
    }

    @GetMapping("/user/{phone}")
    public ResponseResult getUser(@PathVariable ("phone") String phone) {
        return userService.getUser(phone);
    }

}
