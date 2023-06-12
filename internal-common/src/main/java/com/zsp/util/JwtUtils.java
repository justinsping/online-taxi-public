package com.zsp.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.zsp.constant.TokenTypeConstant;
import com.zsp.dto.TokenResult;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {

    // 盐
    private static final String SIGN = "CPSKOJLS!243";

    // key
    private static final String JWT_KEY_PHONE  = "phone";
    private static final String IDENTITY  = "identity";
    private static final String TOKEN_TYPE = "access";
    private static final String TiME = "time";
    // 生成 token
    public static String generatorToken(String passengerPhone, String identity, String tokenType) {
        Map<String, String> map = new HashMap<>();
        map.put(JWT_KEY_PHONE, passengerPhone);
        map.put(IDENTITY, identity);
        map.put(TOKEN_TYPE, tokenType);
        map.put(TiME, Calendar.getInstance().getTime().toString());

        JWTCreator.Builder builder = JWT.create();

        map.forEach((k, v) -> {
            builder.withClaim(k, v);
        });

        // 生成token
        String sign = builder.sign(Algorithm.HMAC256(SIGN));

        return sign;
    }

    // 解析 token
    public static TokenResult parseToken(String token) {
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        String phone = verify.getClaim(JWT_KEY_PHONE).asString();
        String identity = verify.getClaim(IDENTITY).asString();
        String type = verify.getClaim(TOKEN_TYPE).asString();

        TokenResult tokenResult = new TokenResult();
        tokenResult.setPhone(phone);
        tokenResult.setIdentity(identity);
        tokenResult.setType(type);
        return tokenResult;
    }

    // 检验 token
    public static TokenResult checkToken(String token) {
        TokenResult tokenResult = null;
        try {
            tokenResult = JwtUtils.parseToken(token);
        } catch (Exception e) {
        }
        return tokenResult;
    }

    public static void main(String[] args) {
        String s = JwtUtils.generatorToken("18296158487", "1", TokenTypeConstant.TOKEN_TYPE_ACCESS);
        System.out.println(s);
    }

}
