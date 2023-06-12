package com.zsp.serviceverifycode.controller;

import com.zsp.dto.ResponseResult;
import com.zsp.response.NumCodeResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NumCodeController {

    @GetMapping("/numberCode/{size}")
    public ResponseResult numberCode(@PathVariable int size) {

        // 生成对应位数的随机验证码

        double mathRandom = (Math.random() * 9 + 1) * (Math.pow(10, size - 1));
        int resultInt = (int)mathRandom;

        NumCodeResponse numCodeResponse = new NumCodeResponse();
        numCodeResponse.setNumCode(resultInt);

        return ResponseResult.success(numCodeResponse);
    }
}
