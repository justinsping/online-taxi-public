package com.zsp.apipassenger.controller;


import com.zsp.apipassenger.service.TokenService;
import com.zsp.dto.ResponseResult;
import com.zsp.response.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @RequestMapping("/token-refresh")
    public ResponseResult refreshToken(@RequestBody TokenResponse tokenResponse) {

        String refreshTokenStr = tokenResponse.getRefreshToken();

        return tokenService.refreshToken(refreshTokenStr);

    }
}
