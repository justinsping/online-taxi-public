package com.zsp.servicemap.remote;

import com.zsp.constant.AmapConfigConstants;
import com.zsp.dto.ResponseResult;
import com.zsp.request.PointDTO;
import com.zsp.request.TrackPointUploadRequest;
import com.zsp.response.TrackResponse;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class TrackClient {

    @Value("${amap.key}")
    private String amapKey;

    @Value("${amap.sid}")
    private String amapSid;

    @Autowired
    private RestTemplate restTemplate;

    public ResponseResult<TrackResponse> add(String tid) {
        StringBuilder url = new StringBuilder();
        url.append(AmapConfigConstants.TRACK_ADD_URL);
        url.append("?");
        url.append("key=" + amapKey);
        url.append("&");
        url.append("sid=" + amapSid);
        url.append("&");
        url.append("tid=" + tid);

        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(url.toString(), null, String.class);
        String body = stringResponseEntity.getBody();
        JSONObject result = JSONObject.fromObject(body);
        JSONObject data = result.getJSONObject("data");
        // 轨迹id
        String trid = data.getString("trid");
        // 轨迹名称
        String trname = "";
        if (data.has("trname")) {
            trname = data.getString("trname");
        }


        TrackResponse trackResponse = new TrackResponse();
        trackResponse.setTrid(trid);
        trackResponse.setTrname(trname);

        return ResponseResult.success(trackResponse);
    }

    public ResponseResult pointUpload(TrackPointUploadRequest trackPointUploadRequest) {
        // &key=<用户的key>
        // 拼装请求的url
        StringBuilder url = new StringBuilder();
        url.append(AmapConfigConstants.TRACK_UPLOAD_URL);
        url.append("?");
        url.append("key="+amapKey);
        url.append("&");
        url.append("sid="+amapSid);
        url.append("&");
        url.append("tid="+trackPointUploadRequest.getTid());
        url.append("&");
        url.append("trid="+trackPointUploadRequest.getTrid());
        url.append("&");
        url.append("points=");
        PointDTO[] points = trackPointUploadRequest.getPoints();
        url.append("%5B");
        for (PointDTO p : points
        ) {
            url.append("%7B");
            String locatetime = p.getLocatetime();
            String location = p.getLocation();
            url.append("%22location%22");
            url.append("%3A");
            url.append("%22"+location+"%22");
            url.append("%2C");

            url.append("%22locatetime%22");
            url.append("%3A");
            url.append(locatetime);

            url.append("%7D");
        }
        url.append("%5D");

        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(URI.create(url.toString()), null, String.class);

        return ResponseResult.success();
    }
}
