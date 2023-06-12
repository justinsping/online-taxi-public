package com.zsp.util;

public class RedisPrefixUtils {

    // 验证码redis的前缀
    public static String verifyCodePrefix = "passenger-verify-code-";

    // token redis的前缀
    public static String tokenPrefix = "token-";


    /**
     * 根据手机号，生成对应的key
     */
    public static String generatorKeyByPhone(String passengerPhone) {
        return verifyCodePrefix + passengerPhone;
    }

    /**
     * 根据手机号，用户身份，生成对应的key
     * @param passengerPhone
     * @param identity
     * @return
     */
    public static String generatorKeyToken(String passengerPhone, String identity, String tokenType) {
        return tokenPrefix + passengerPhone + "-" + identity + "-" + tokenType;
    }
}
