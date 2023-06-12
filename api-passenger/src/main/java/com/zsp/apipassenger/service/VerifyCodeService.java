package com.zsp.apipassenger.service;


import com.zsp.apipassenger.remote.ServicePassengerUserClient;
import com.zsp.apipassenger.remote.ServiceVerifyCodeClient;
import com.zsp.constant.CommonStatusEnum;
import com.zsp.constant.IdentityConstant;
import com.zsp.constant.TokenTypeConstant;
import com.zsp.dto.ResponseResult;
import com.zsp.request.VerifyCodeDTO;
import com.zsp.response.NumCodeResponse;
import com.zsp.response.TokenResponse;
import com.zsp.util.JwtUtils;
import com.zsp.util.RedisPrefixUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class VerifyCodeService {
    
    @Autowired
    private ServiceVerifyCodeClient serviceVerifyCodeClient;

    @Autowired
    private ServicePassengerUserClient servicePassengerUserClient;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public ResponseResult generatorCode(String passengerPhone) {
        // 调用验证码服务，获取验证码
        ResponseResult<NumCodeResponse> numberCodeResponse = serviceVerifyCodeClient.getNumberCode(6);
        int numberCode = numberCodeResponse.getData().getNumCode();

        // 存入redis 并设置过期时间
        String key = RedisPrefixUtils.generatorKeyByPhone(passengerPhone);
        stringRedisTemplate.opsForValue().set(key, numberCode+"", 2, TimeUnit.MINUTES);

        // 通过短信服务商，将相应的验证码发送到用户手机上
        return ResponseResult.success();
    }

    public ResponseResult checkVerifyCode(String passengerPhone, String verificationCode) {
        // 根据手机号，去redis获取对应的验证码
        // 生成key
        String key = RedisPrefixUtils.generatorKeyByPhone(passengerPhone);
        // 根据key获取value
        String codeRedis = stringRedisTemplate.opsForValue().get(key);

        // 校验验证码是否一致
        if(StringUtils.isBlank(codeRedis)) {
            return ResponseResult.fail(CommonStatusEnum.VERIFY_CODE_ERROR.getCode(),CommonStatusEnum.VERIFY_CODE_ERROR.getValue());
        }
        if(!verificationCode.trim().equals(codeRedis.trim())) {
            return ResponseResult.fail(CommonStatusEnum.VERIFY_CODE_ERROR.getCode(),CommonStatusEnum.VERIFY_CODE_ERROR.getValue());
        }

        // 判断是否是新用户，并进行对应的处理
        VerifyCodeDTO verifyCodeDTO = new VerifyCodeDTO();
        verifyCodeDTO.setPassengerPhone(passengerPhone);
        servicePassengerUserClient.loginOrRegister(verifyCodeDTO);


        // 颁发令牌 （accessToken 和 refreshToken)
        String accessToken = JwtUtils.generatorToken(passengerPhone, IdentityConstant.PASSENGER_IDENTITY, TokenTypeConstant.TOKEN_TYPE_ACCESS);
        String refreshToken = JwtUtils.generatorToken(passengerPhone, IdentityConstant.PASSENGER_IDENTITY, TokenTypeConstant.TOKEN_TYPE_REFRESH);
        // 把token保存到redis中
        String accessTokenKey = RedisPrefixUtils.generatorKeyToken(passengerPhone, IdentityConstant.PASSENGER_IDENTITY, TokenTypeConstant.TOKEN_TYPE_ACCESS);
        String refreshTokenKey = RedisPrefixUtils.generatorKeyToken(passengerPhone, IdentityConstant.PASSENGER_IDENTITY, TokenTypeConstant.TOKEN_TYPE_REFRESH);
        stringRedisTemplate.opsForValue().set(accessTokenKey, accessToken, 30, TimeUnit.DAYS);
        stringRedisTemplate.opsForValue().set(refreshTokenKey, refreshToken, 31, TimeUnit.DAYS);
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setAccessToken(accessToken);
        tokenResponse.setRefreshToken(refreshToken);

        return ResponseResult.success(tokenResponse);
    }
}
