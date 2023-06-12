package com.zsp.apiDriver.remote;

import com.zsp.dto.DriverUser;
import com.zsp.dto.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("service-driver-user")
public interface ServiceDriverUserClient {

    @RequestMapping(method = RequestMethod.PUT, value = "/addDriverUser")
    public ResponseResult updateDriverUser(@RequestBody DriverUser driverUser);
}
