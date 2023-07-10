package com.zsp.apiDriver.remote;

import com.zsp.dto.ResponseResult;
import com.zsp.request.TrackPointUploadRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("service-map")
public interface ServiceMapClient {

    @RequestMapping(method = RequestMethod.POST, value = "/track/pointUpload")
    public ResponseResult upload(@RequestBody TrackPointUploadRequest trackPointUploadRequest);
}
