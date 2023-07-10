package com.zsp.apiDriver.controller;

import com.zsp.apiDriver.service.TrackPointService;
import com.zsp.dto.ResponseResult;
import com.zsp.request.ApiDriverTrackPointRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/track")
public class TrackPointController {

    @Autowired
    private TrackPointService trackPointService;

    @PostMapping("/uploadPoint")
    public ResponseResult upload(@RequestBody ApiDriverTrackPointRequest apiDriverTrackPointRequest) {
        return trackPointService.upload(apiDriverTrackPointRequest);
    }
}
