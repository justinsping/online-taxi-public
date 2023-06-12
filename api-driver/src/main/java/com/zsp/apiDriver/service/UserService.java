package com.zsp.apiDriver.service;

import com.zsp.apiDriver.remote.ServiceDriverUserClient;
import com.zsp.dto.DriverUser;
import com.zsp.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    public ResponseResult updateUser(DriverUser driverUser) {
        return serviceDriverUserClient.updateDriverUser(driverUser);
    }
}
