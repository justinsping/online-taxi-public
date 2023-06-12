package com.zsp.servicemap.remote;

import com.zsp.constant.AmapConfigConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MapDicDistrictClient {

    @Value("${amap.key}")
    private String amapKey;

    @Autowired
    private RestTemplate restTemplate;

    public String dicDistrict(String keywords) {
        // https://restapi.amap.com/v3/config/district?keywords=北京&subdistrict=2&key=<用户的key>
        StringBuilder districtUrl = new StringBuilder();
        districtUrl.append(AmapConfigConstants.DISTRICT_URL);
        districtUrl.append("?");
        districtUrl.append("keywords=" + keywords);
        districtUrl.append("&");
        districtUrl.append("subdistrict=3");
        districtUrl.append("&");
        districtUrl.append("key=" + amapKey);

        ResponseEntity<String> forEntity = restTemplate.getForEntity(districtUrl.toString(), String.class);

        return forEntity.getBody();
    }

}
