package com.zsp.apiDriver.remote;

import com.zsp.dto.ResponseResult;
import com.zsp.response.NumCodeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("service-verifyCode")
public interface ServiceVerifyCodeClient {

    @RequestMapping(method = RequestMethod.GET, value = "/numberCode/{size}")
    ResponseResult<NumCodeResponse> getNumberCode(@PathVariable("size") int size);

}
