package com.zsp.servicemap.remote;

import com.zsp.constant.AmapConfigConstants;
import com.zsp.dto.ResponseResult;
import com.zsp.response.TerminalResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class TerminalClient {

    @Value("${amap.key}")
    private String amapKey;

    @Value("${amap.sid}")
    private String amapSid;

    @Autowired
    private RestTemplate restTemplate;

    public ResponseResult add(String name) {
        StringBuilder url = new StringBuilder();
        url.append(AmapConfigConstants.TERMINAL_ADD_URL);
        url.append("?");
        url.append("key="+amapKey);
        url.append("&");
        url.append("sid="+amapSid);
        url.append("&");
        url.append("name="+name);

        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(url.toString(), null, String.class);
        String body = stringResponseEntity.getBody();
        JSONObject result = JSONObject.fromObject(body);
        JSONObject data = result.getJSONObject("data");
        String tid = data.getString("tid");

        TerminalResponse terminalResponse = new TerminalResponse();
        terminalResponse.setTid(tid);

        return ResponseResult.success(terminalResponse);
    }

    public ResponseResult aroundsearch(String center, Integer radius) {
        StringBuilder url = new StringBuilder();
        url.append(AmapConfigConstants.TERMINAL_AROUND_SEARCH_URL);
        url.append("?");
        url.append("key="+amapKey);
        url.append("&");
        url.append("sid="+amapSid);
        url.append("&");
        url.append("center="+center);
        url.append("&");
        url.append("radius="+radius);

        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(url.toString(), null, String.class);

        String body = stringResponseEntity.getBody();
        JSONObject result = JSONObject.fromObject(body);
        JSONObject data = result.getJSONObject("data");

        List<TerminalResponse> terminalResponseList = new ArrayList<>();

        JSONArray results = data.getJSONArray("results");

        for (int i = 0; i < results.size(); i++) {
            TerminalResponse terminalResponse1 = new TerminalResponse();
            JSONObject jsonObject = results.getJSONObject(i);
            String tid = jsonObject.getString("tid");
            JSONObject location = jsonObject.getJSONObject("location");
            String longitude = location.getString("longitude");
            String latitude = location.getString("latitude");

            terminalResponse1.setTid(tid);
            terminalResponse1.setLatitude(latitude);
            terminalResponse1.setLongitude(longitude);

            terminalResponseList.add(terminalResponse1);

        }
        return ResponseResult.success(terminalResponseList);
    }
}
