package com.zsp.servicemap.remote;

import com.zsp.constant.AmapConfigConstants;
import com.zsp.response.DirectionResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class MapDirectionClient {

    @Value("${amap.key}")
    private String amapKey;

    @Autowired
    private RestTemplate restTemplate;

    public DirectionResponse direction(String depLongitude, String depLatitude, String destLongitude, String destLatitude) {
        // 组装请求调用url
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(AmapConfigConstants.DIRECTION_URL);
        urlBuilder.append("?");
        urlBuilder.append("origin=" + depLongitude + "," + depLatitude);
        urlBuilder.append("&");
        urlBuilder.append("destination=" + depLongitude + "," + depLatitude);
        urlBuilder.append("&");
        urlBuilder.append("extensions=base");
        urlBuilder.append("&");
        urlBuilder.append("output=json");
        urlBuilder.append("&");
        urlBuilder.append("key="+amapKey);
        log.info(urlBuilder.toString());

        // 调用高德接口
        ResponseEntity<String> directionEntity = restTemplate.getForEntity(urlBuilder.toString(), String.class);
        String directionEntityBody = directionEntity.getBody();
        log.info("高德接口返回值" + directionEntityBody);

        // 解析接口
        DirectionResponse directionResponse = parseDirectionEntity(directionEntityBody);
        return directionResponse;
    }

    private DirectionResponse parseDirectionEntity(String directionEntityBody) {
        DirectionResponse directionResponse = null;

        JSONObject result = JSONObject.fromObject(directionEntityBody);
        if (result.has("status")) {
            int status = result.getInt("status");
            if (status == 1) {
                if (result.has("route")) {
                    JSONObject routeObject = result.getJSONObject("route");
                    JSONArray pathsArray = routeObject.getJSONArray("paths");
                    JSONObject pathObject = pathsArray.getJSONObject(0);
                    directionResponse = new DirectionResponse();

                    if (pathObject.has("distance")) {
                        int distance = pathObject.getInt("distance");
                        directionResponse.setDistance(distance);
                    }

                    if (pathObject.has("duration")) {
                        int duration = pathObject.getInt("duration");
                        directionResponse.setDuration(duration);
                    }
                }
            }
        }
        return directionResponse;
    }
}
