package com.zsp.apipassenger.controller;

import com.zsp.apipassenger.service.UserService;
import com.zsp.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public ResponseResult getUser(HttpServletRequest request) {
        String accessToken = request.getHeader("Authorization");
        return userService.getUser(accessToken);
    }

}
