package com.zsp.apipassenger.controller;

import com.zsp.apipassenger.request.VerifyCodeDTO;
import com.zsp.apipassenger.service.VerifyCodeService;
import com.zsp.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class VerifyCodeController {

    @Autowired
    private VerifyCodeService verifyCodeService;

    @GetMapping("/verify-code")
    public ResponseResult verifyCode(@RequestBody VerifyCodeDTO verifyCodeDTO) {

        String passengerPhone = verifyCodeDTO.getPassengerPhone();
        System.out.println("手机号码" + passengerPhone);

        return verifyCodeService.generatorCode(passengerPhone);
    }

    @PostMapping("/verify-code-check")
    public ResponseResult checkVerifyCode(@RequestBody VerifyCodeDTO verifyCodeDTO) {

        String passengerPhone = verifyCodeDTO.getPassengerPhone();
        String verificationCode = verifyCodeDTO.getVerificationCode();

        return verifyCodeService.checkVerifyCode(passengerPhone, verificationCode);
    }
}
