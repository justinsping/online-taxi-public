package com.zsp.serviceDriverUser.service;

import com.zsp.dto.Car;
import com.zsp.dto.ResponseResult;
import com.zsp.response.TerminalResponse;
import com.zsp.response.TrackResponse;
import com.zsp.serviceDriverUser.mapper.CarMapper;
import com.zsp.serviceDriverUser.remote.ServiceMapClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

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

    @Autowired
    private ServiceMapClient serviceMapClient;

    public ResponseResult addCar(Car car) {
        LocalDateTime now = LocalDateTime.now();
        car.setGmtCreate(now);
        car.setGmtModified(now);
        // 保存车辆
        carMapper.insert(car);

        // 获取此车辆的终端id：tid
        ResponseResult<TerminalResponse> terminalResult = serviceMapClient.addTerminal(car.getVehicleNo(), car.getId() + "");
        String tid = terminalResult.getData().getTid();
        car.setTid(tid);

        // 获取此车辆的轨迹id：trid
        ResponseResult<TrackResponse> trackResult = serviceMapClient.addTrack(tid);
        String trid = trackResult.getData().getTrid();
        String trname = trackResult.getData().getTrname();
        car.setTrid(trid);
        car.setTrname(trname);

        // 更新车辆
        carMapper.updateById(car);

        return ResponseResult.success("");
    }

    public ResponseResult<Car> getCarById(Long carId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", carId);

        List<Car> cars = carMapper.selectByMap(map);

        return ResponseResult.success(cars.get(0));
    }
}
