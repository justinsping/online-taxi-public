package com.zsp.apiBoss.service;

import com.zsp.apiBoss.remote.ServiceDriverUserClient;
import com.zsp.dto.DriverCarBindRelationship;
import com.zsp.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverCarBindRelationshipService {

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    public ResponseResult bind(DriverCarBindRelationship driverCarBindRelationship) {
        return serviceDriverUserClient.bind(driverCarBindRelationship);
    }

    public ResponseResult unbind(DriverCarBindRelationship driverCarBindRelationship) {
        return serviceDriverUserClient.unbind(driverCarBindRelationship);
    }
}
