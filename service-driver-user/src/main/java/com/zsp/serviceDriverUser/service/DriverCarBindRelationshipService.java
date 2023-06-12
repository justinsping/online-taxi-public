package com.zsp.serviceDriverUser.service;

import com.zsp.constant.CommonStatusEnum;
import com.zsp.constant.DriverCarConstants;
import com.zsp.dto.DriverCarBindRelationship;
import com.zsp.dto.ResponseResult;
import com.zsp.serviceDriverUser.mapper.DriverCarBindRelationshipMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 张世平
 * @since 2022-09-19
 */
@Service
public class DriverCarBindRelationshipService {

    @Autowired
    private DriverCarBindRelationshipMapper driverCarBindRelationshipMapper;

    public ResponseResult bind(DriverCarBindRelationship driverCarBindRelationship) {
        LocalDateTime now = LocalDateTime.now();
        driverCarBindRelationship.setBindTime(now);

        driverCarBindRelationship.setBindState(DriverCarConstants.DRIVER_CAR_BIND);
        driverCarBindRelationshipMapper.insert(driverCarBindRelationship);

        return ResponseResult.success("");
    }

    public ResponseResult unbind(DriverCarBindRelationship driverCarBindRelationship) {
        LocalDateTime now = LocalDateTime.now();

        Map<String, Object> map = new HashMap<>();
        map.put("driver_id", driverCarBindRelationship.getDriverId());
        map.put("car_id",driverCarBindRelationship.getCarId());
        map.put("bind_state", DriverCarConstants.DRIVER_CAR_BIND);

        List<DriverCarBindRelationship> driverCarBindRelationships = driverCarBindRelationshipMapper.selectByMap(map);
        if (driverCarBindRelationships.isEmpty()) {
            return ResponseResult.fail(CommonStatusEnum.DRIVER_CAR_BIND_NOT_EXISTS.getCode(), CommonStatusEnum.DRIVER_CAR_BIND_NOT_EXISTS.getValue());
        }
        DriverCarBindRelationship driverCarBindRelationship1 = driverCarBindRelationships.get(0);
        driverCarBindRelationship1.setBindState(DriverCarConstants.DRIVER_CAR_UNBIND);
        driverCarBindRelationship1.setUnBindTime(now);

        driverCarBindRelationshipMapper.updateById(driverCarBindRelationship1);

        return ResponseResult.success("");
    }
}
