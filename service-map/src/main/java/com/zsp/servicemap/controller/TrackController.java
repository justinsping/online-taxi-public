package com.zsp.servicemap.controller;

import com.zsp.dto.ResponseResult;
import com.zsp.request.TrackPointUploadRequest;
import com.zsp.servicemap.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/track")
public class TrackController {

    @Autowired
    private TrackService trackService;

    @PostMapping("/add")
    public ResponseResult add(String tid) {
        return trackService.add(tid);
    }

    @PostMapping("/pointUpload")
    public ResponseResult pointUpload(@RequestBody TrackPointUploadRequest trackPointUploadRequest) {
        return trackService.pointUpload(trackPointUploadRequest);
    }
}
