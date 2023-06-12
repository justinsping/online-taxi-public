package com.zsp.servicemap.service;

import com.zsp.dto.ResponseResult;
import com.zsp.response.DirectionResponse;
import com.zsp.servicemap.remote.MapDirectionClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DirectionService {

    @Autowired
    private MapDirectionClient mapDirectionClient;

    public ResponseResult driving(String depLongitude, String depLatitude, String destLongitude, String destLatitude) {
        log.info("service-map" + "服务");
        log.info(depLongitude);
        log.info(depLatitude);
        log.info(destLongitude);
        log.info(destLatitude);

        // 调用高德地图接口
        DirectionResponse direction = mapDirectionClient.direction(depLongitude, depLatitude, destLongitude, destLatitude);

        return ResponseResult.success(direction);
    }
}

