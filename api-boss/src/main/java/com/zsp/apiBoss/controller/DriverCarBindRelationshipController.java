package com.zsp.apiBoss.controller;

import com.zsp.apiBoss.service.DriverCarBindRelationshipService;
import com.zsp.dto.DriverCarBindRelationship;
import com.zsp.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/driver-car-bind-relationship")
public class DriverCarBindRelationshipController {

    @Autowired
    private DriverCarBindRelationshipService driverCarBindRelationshipService;

    @PostMapping("/bind")
    public ResponseResult bind(@RequestBody DriverCarBindRelationship driverCarBindRelationship) {
        return driverCarBindRelationshipService.bind(driverCarBindRelationship);
    }

    @PostMapping("/unbind")
    public ResponseResult unbind(@RequestBody DriverCarBindRelationship driverCarBindRelationship) {
        return driverCarBindRelationshipService.unbind(driverCarBindRelationship);
    }

}
