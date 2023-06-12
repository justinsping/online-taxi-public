package com.zsp.serviceDriverUser.controller;


import com.zsp.dto.DriverCarBindRelationship;
import com.zsp.dto.ResponseResult;
import com.zsp.serviceDriverUser.service.DriverCarBindRelationshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 张世平
 * @since 2022-09-19
 */
@RestController
@RequestMapping("/driver-car-bind-relationship")
public class DriverCarBindRelationshipController {

    @Autowired
    private DriverCarBindRelationshipService driverCarBindRelationshipService;

    @PostMapping("/bind")
    public ResponseResult bind (@RequestBody DriverCarBindRelationship driverCarBindRelationship) {
        return driverCarBindRelationshipService.bind(driverCarBindRelationship);
    }

    @PostMapping("/unbind")
    public ResponseResult unbind (@RequestBody DriverCarBindRelationship driverCarBindRelationship) {
        return driverCarBindRelationshipService.unbind(driverCarBindRelationship);
    }
}
