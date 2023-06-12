package com.zsp.serviceDriverUser.service;

import com.zsp.dto.Car;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zsp.dto.ResponseResult;
import com.zsp.serviceDriverUser.mapper.CarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 张世平
 * @since 2022-08-31
 */
@Service
public class CarService {

    @Autowired
    private CarMapper carMapper;

    public ResponseResult addCar(Car car) {
        LocalDateTime now = LocalDateTime.now();
        car.setGmtCreate(now);
        car.setGmtModified(now);
        carMapper.insert(car);
        return ResponseResult.success("");
    }
}
