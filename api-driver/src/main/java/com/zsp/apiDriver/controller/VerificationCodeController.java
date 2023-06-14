package com.zsp.apiDriver.controller;

import com.zsp.apiDriver.service.VerificationCodeService;
import com.zsp.dto.ResponseResult;
import com.zsp.request.VerifyCodeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class VerificationCodeController {

    @Autowired
    private VerificationCodeService verificationCodeService;

    @GetMapping("/verification-code")
    public ResponseResult verificationCode(@RequestBody VerifyCodeDTO verifyCodeDTO) {
        String driverPhone = verifyCodeDTO.getDriverPhone();
        log.info("司机的号码" + driverPhone);
        return verificationCodeService.checkAndSendVerificationCode(driverPhone);
    }
}
