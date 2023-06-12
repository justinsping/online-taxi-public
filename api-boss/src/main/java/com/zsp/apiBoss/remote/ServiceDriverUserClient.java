package com.zsp.apiBoss.remote;


import com.zsp.dto.Car;
import com.zsp.dto.DriverCarBindRelationship;
import com.zsp.dto.DriverUser;
import com.zsp.dto.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("service-driver-user")
public interface ServiceDriverUserClient {

    @RequestMapping(method = RequestMethod.POST, value = "/addDriverUser")
    ResponseResult addDriverUser(@RequestBody DriverUser driverUser);

    @RequestMapping(method = RequestMethod.PUT, value = "/addDriverUser")
    ResponseResult updateDriverUser(@RequestBody DriverUser driverUser);

    @RequestMapping(method = RequestMethod.POST, value = "/car")
    ResponseResult addCar(@RequestBody Car car);

    @RequestMapping(method = RequestMethod.POST, value = "/driver-car-bind-relationship/bind")
    ResponseResult bind(@RequestBody DriverCarBindRelationship driverCarBindRelationship);

    @RequestMapping(method = RequestMethod.POST, value = "/driver-car-bind-relationship/unbind")
    ResponseResult unbind(DriverCarBindRelationship driverCarBindRelationship);
}
