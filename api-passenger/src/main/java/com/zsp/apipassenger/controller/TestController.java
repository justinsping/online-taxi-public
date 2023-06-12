package com.zsp.apipassenger.controller;

import com.zsp.dto.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "test a api-passenger";
    }

    /**
     * 需要token
     * @return
     */
    @GetMapping("/authTest")
    public ResponseResult authTest() {
        return ResponseResult.success("auth test");
    }

    /**
     * 不需要token
     * @return
     */
    @GetMapping("/noauthTest")
    public ResponseResult noauthTest() {
        return ResponseResult.success("noauth test");
    }

}
