package com.zsp.apipassenger.service;

import com.zsp.constant.CommonStatusEnum;
import com.zsp.constant.IdentityConstant;
import com.zsp.constant.TokenTypeConstant;
import com.zsp.dto.ResponseResult;
import com.zsp.dto.TokenResult;
import com.zsp.response.TokenResponse;
import com.zsp.util.JwtUtils;
import com.zsp.util.RedisPrefixUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TokenService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public ResponseResult refreshToken(String refreshTokenStr) {
        // 解析token 生成对应的key
        TokenResult tokenResult = JwtUtils.checkToken(refreshTokenStr);
        String phone = tokenResult.getPhone();
        String identity = tokenResult.getIdentity();
        String type = tokenResult.getType();
        String refreshTokenKey = RedisPrefixUtils.generatorKeyToken(phone, identity, type);
        // 根据key 从redis获取对应的token
        String refreshTokenRedis = stringRedisTemplate.opsForValue().get(refreshTokenKey);

        // 检验token是否一致
        if (StringUtils.isBlank(refreshTokenRedis) || !refreshTokenStr.trim().equals(refreshTokenRedis.trim())) {
            // 为空或不一致
            return ResponseResult.fail(CommonStatusEnum.TOKEN_ERROR.getValue());
        }

        // 一致生成新的双token 并返回
        String accessToken = JwtUtils.generatorToken(phone, IdentityConstant.PASSENGER_IDENTITY, TokenTypeConstant.TOKEN_TYPE_ACCESS);
        String refreshToken = JwtUtils.generatorToken(phone, IdentityConstant.PASSENGER_IDENTITY, TokenTypeConstant.TOKEN_TYPE_REFRESH);
        // 把token保存到redis中
        String accessTokenKeyNew = RedisPrefixUtils.generatorKeyToken(phone, IdentityConstant.PASSENGER_IDENTITY, TokenTypeConstant.TOKEN_TYPE_ACCESS);
        String refreshTokenKeyNew = RedisPrefixUtils.generatorKeyToken(phone, IdentityConstant.PASSENGER_IDENTITY, TokenTypeConstant.TOKEN_TYPE_REFRESH);
        stringRedisTemplate.opsForValue().set(accessTokenKeyNew, accessToken, 30, TimeUnit.DAYS);
        stringRedisTemplate.opsForValue().set(refreshTokenKeyNew, refreshToken, 31, TimeUnit.DAYS);

        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setAccessToken(accessToken);
        tokenResponse.setRefreshToken(refreshToken);

        return ResponseResult.success(tokenResponse);
    }
}
