package com.zsp.apiDriver.service;

import com.zsp.apiDriver.remote.ServiceDriverUserClient;
import com.zsp.apiDriver.remote.ServiceVerifyCodeClient;
import com.zsp.constant.CommonStatusEnum;
import com.zsp.constant.DriverCarConstants;
import com.zsp.constant.IdentityConstant;
import com.zsp.dto.ResponseResult;
import com.zsp.response.DriverUserExistResponse;
import com.zsp.response.NumCodeResponse;
import com.zsp.util.RedisPrefixUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class VerificationCodeService {

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    @Autowired
    private ServiceVerifyCodeClient serviceVerifyCodeClient;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public ResponseResult checkAndSendVerificationCode(String driverPhone) {
        // 查询 service-driver-user 该手机号是否存在
        ResponseResult<DriverUserExistResponse> driverUserExistResponseResponseResult = serviceDriverUserClient.checkDriverUser(driverPhone);
        DriverUserExistResponse data = driverUserExistResponseResponseResult.getData();
        log.info(data.toString());

        int isExist = data.getIsExist();

        if (isExist == DriverCarConstants.DRIVER_NOT_EXIST) {
            return ResponseResult.fail(CommonStatusEnum.DRIVER_NOT_EXIST.getCode(), CommonStatusEnum.DRIVER_NOT_EXIST.getValue());
        }

        // 获取验证码
        ResponseResult<NumCodeResponse> numberCode = serviceVerifyCodeClient.getNumberCode(6);
        log.info((numberCode.toString()));
        int numCode = numberCode.getData().getNumCode();



        // 存入redis
        String key = RedisPrefixUtils.generatorKeyByPhone(driverPhone, IdentityConstant.DRIVER_IDENTITY);
        stringRedisTemplate.opsForValue().set(key, numCode+"", 2, TimeUnit.MINUTES);

        // 调用第三方发送验证码


        return ResponseResult.success("");
    }
}
