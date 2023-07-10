package com.zsp.apiDriver.service;

import com.zsp.apiDriver.remote.ServiceDriverUserClient;
import com.zsp.apiDriver.remote.ServiceMapClient;
import com.zsp.dto.Car;
import com.zsp.dto.ResponseResult;
import com.zsp.request.ApiDriverTrackPointRequest;
import com.zsp.request.TrackPointUploadRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrackPointService {

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    @Autowired
    private ServiceMapClient serviceMapClient;

    public ResponseResult upload(ApiDriverTrackPointRequest apiDriverTrackPointRequest) {
        // 获取carId
        Long carId = apiDriverTrackPointRequest.getCarId();

        // 通过carId 获取对应的tid trid 调用service-driver-user服务
        ResponseResult<Car> car = serviceDriverUserClient.getCarById(carId);
        String tid = car.getData().getTid();
        String trid = car.getData().getTrid();

        // 调用service-map服务上传轨迹点
        TrackPointUploadRequest trackPointRequest = new TrackPointUploadRequest();
        trackPointRequest.setTid(tid);
        trackPointRequest.setTrid(trid);
        trackPointRequest.setPoints(apiDriverTrackPointRequest.getPoints());

        return serviceMapClient.upload(trackPointRequest);
    }
}
