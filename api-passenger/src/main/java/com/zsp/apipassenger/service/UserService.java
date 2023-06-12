package com.zsp.apipassenger.service;

import com.zsp.apipassenger.remote.ServicePassengerUserClient;
import com.zsp.dto.ResponseResult;
import com.zsp.dto.TokenResult;
import com.zsp.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private ServicePassengerUserClient servicePassengerUserClient;

    public ResponseResult getUser(String accessToken) {
        // 解析token
        TokenResult tokenResult = JwtUtils.parseToken(accessToken);
        String phone = tokenResult.getPhone();
        // 调用user服务
        ResponseResult user = servicePassengerUserClient.getUser(phone);


        return ResponseResult.success(user.getData());
    }
}
