package com.zsp.apipassenger.remote;

import com.zsp.dto.ResponseResult;
import com.zsp.request.VerifyCodeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("service-passenger-user")
public interface ServicePassengerUserClient {

    @RequestMapping(method = RequestMethod.POST, value = "/user")
    public ResponseResult loginOrRegister(@RequestBody VerifyCodeDTO verifyCodeDTO);

    @RequestMapping(method = RequestMethod.GET, value = "/user/{phone}")
    public ResponseResult getUser(@PathVariable("phone") String phone);
}
