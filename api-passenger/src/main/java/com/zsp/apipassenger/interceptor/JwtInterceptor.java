package com.zsp.apipassenger.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.zsp.dto.ResponseResult;
import com.zsp.dto.TokenResult;
import com.zsp.util.JwtUtils;
import com.zsp.util.RedisPrefixUtils;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    boolean result = true;
    String resultString = "";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("Authorization");

        TokenResult tokenResult = JwtUtils.checkToken(token);
        
        //检验token是否与保持的一致
        // 首先判断是否存在token
        if (tokenResult != null) {
            String phone = tokenResult.getPhone();
            String identity = tokenResult.getIdentity();
            String type = tokenResult.getType();

            String tokenKey = RedisPrefixUtils.generatorKeyToken(phone, identity, type);
            String redisToken = stringRedisTemplate.opsForValue().get(tokenKey);
            if ((StringUtils.isBlank(redisToken)) || !token.trim().equals(redisToken.trim())) {
                resultString = "access token invalid";
                result = false;
            }
        } else {
            resultString = "access token invalid";
            result = false;
        }


        if (!result) {
            PrintWriter out = response.getWriter();
            out.print(JSONObject.fromObject(ResponseResult.fail(resultString)).toString());
        }

        return result;
    }
}
