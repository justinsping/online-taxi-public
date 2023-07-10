package com.zsp.servicemap.service;

import com.zsp.dto.ResponseResult;
import com.zsp.request.TrackPointUploadRequest;
import com.zsp.response.TrackResponse;
import com.zsp.servicemap.remote.TrackClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrackService {

    @Autowired
    private TrackClient trackClient;

    public ResponseResult<TrackResponse> add(String tid) {
        return trackClient.add(tid);
    }

    public ResponseResult pointUpload(TrackPointUploadRequest trackPointUploadRequest) {
        return trackClient.pointUpload(trackPointUploadRequest);
    }
}
