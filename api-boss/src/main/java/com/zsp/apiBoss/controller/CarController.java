package com.zsp.apiBoss.controller;

import com.zsp.apiBoss.service.CarService;
import com.zsp.dto.Car;
import com.zsp.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {

    @Autowired
    private CarService carServer;

    @PostMapping("/car")
    public ResponseResult car(@RequestBody Car car) {
        return carServer.addCar(car);
    }
}
