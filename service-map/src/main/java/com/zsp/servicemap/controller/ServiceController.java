package com.zsp.servicemap.controller;

import com.zsp.dto.ResponseResult;
import com.zsp.servicemap.service.ServiceFromMapService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service")
@Slf4j
public class ServiceController {

    @Autowired
    private ServiceFromMapService serviceFromMapService;

    /**
     * 创建服务
     * @param name
     * @return
     */
    @PostMapping("/add")
    public ResponseResult add(String name) {
        return serviceFromMapService.add(name);
    }
}
