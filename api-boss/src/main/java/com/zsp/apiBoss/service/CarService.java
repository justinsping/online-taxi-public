package com.zsp.apiBoss.service;

import com.zsp.apiBoss.remote.ServiceDriverUserClient;
import com.zsp.dto.Car;
import com.zsp.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    public ResponseResult addCar(Car car) {
        return serviceDriverUserClient.addCar(car);
    }
}
