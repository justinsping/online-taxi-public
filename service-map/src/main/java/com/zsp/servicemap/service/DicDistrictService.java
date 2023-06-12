package com.zsp.servicemap.service;

import com.zsp.constant.AmapConfigConstants;
import com.zsp.constant.CommonStatusEnum;
import com.zsp.dto.DicDistrict;
import com.zsp.dto.ResponseResult;
import com.zsp.servicemap.mapper.DicDistrictMapper;
import com.zsp.servicemap.remote.MapDicDistrictClient;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DicDistrictService {

    @Autowired
    private MapDicDistrictClient mapDicDistrictClient;

    @Autowired
    private DicDistrictMapper dicDistrictMapper;

    public ResponseResult initDicDistrict(String keywords) {
        // 请求高德，获取行政区域数据
        String dicDistrictResult = mapDicDistrictClient.dicDistrict(keywords);
        System.out.println(dicDistrictResult);

        //解析结果
        JSONObject dicDistrictJsonObject = JSONObject.fromObject(dicDistrictResult);
        int status = dicDistrictJsonObject.getInt(AmapConfigConstants.STATUS);
        if (status != 1) {
            return ResponseResult.fail(CommonStatusEnum.MAP_DISTRICT_ERROR.getCode(),CommonStatusEnum.MAP_DISTRICT_ERROR.getValue());
        }
        JSONArray countryJsonArray = dicDistrictJsonObject.getJSONArray(AmapConfigConstants.DISTRICTS);
        for(int country = 0; country < countryJsonArray.size(); country++) {
            JSONObject countryJsonObject = countryJsonArray.getJSONObject(country);
            String countryAddressCode = countryJsonObject.getString(AmapConfigConstants.ADCODE);
            String countryAddressName = countryJsonObject.getString(AmapConfigConstants.NAME);
            String countryLevel = countryJsonObject.getString(AmapConfigConstants.LEVEL);
            String countryParentAddressCode = "0";

            insertDicDistrict(countryAddressCode, countryAddressName, countryLevel, countryParentAddressCode);

            JSONArray provinceJsonArray = countryJsonObject.getJSONArray(AmapConfigConstants.DISTRICTS);
            for (int p = 0; p < provinceJsonArray.size(); p++) {
                JSONObject provinceJsonObject = provinceJsonArray.getJSONObject(p);
                String provinceAddressCode = provinceJsonObject.getString(AmapConfigConstants.ADCODE);
                String provinceName = provinceJsonObject.getString(AmapConfigConstants.NAME);
                String provinceLevel = provinceJsonObject.getString(AmapConfigConstants.LEVEL);
                String provinceParentAddressCode = countryAddressCode;

                insertDicDistrict(provinceAddressCode, provinceName, provinceLevel, provinceParentAddressCode);

                JSONArray cityJsonArray = provinceJsonObject.getJSONArray(AmapConfigConstants.DISTRICTS);
                for (int c = 0; c < cityJsonArray.size(); c++) {
                    JSONObject cityJsonObject = cityJsonArray.getJSONObject(c);
                    String cityAddressCode = cityJsonObject.getString(AmapConfigConstants.ADCODE);
                    String cityName = cityJsonObject.getString(AmapConfigConstants.NAME);
                    String cityLevel = cityJsonObject.getString(AmapConfigConstants.LEVEL);
                    String cityParentAddressCode = provinceAddressCode;

                    insertDicDistrict(cityAddressCode, cityName, cityLevel, cityParentAddressCode);
                    JSONArray districtArray = cityJsonObject.getJSONArray(AmapConfigConstants.DISTRICTS);
                    for (int d = 0;d < districtArray.size(); d++){
                        JSONObject districtJsonObject = districtArray.getJSONObject(d);
                        String districtAddressCode = districtJsonObject.getString(AmapConfigConstants.ADCODE);
                        String districtAddressName = districtJsonObject.getString(AmapConfigConstants.NAME);
                        String districtParentAddressCode = cityAddressCode;
                        String districtLevel = districtJsonObject.getString(AmapConfigConstants.LEVEL);

                        if(districtLevel.equals(AmapConfigConstants.STREET)){
                            continue;
                        }

                        insertDicDistrict(districtAddressCode,districtAddressName,districtLevel,districtParentAddressCode);

                    }
                }
            }

        }
        return null;
    }

    public void insertDicDistrict(String addressCode, String addressName, String level, String parentAddressCode) {
        DicDistrict dicDistrict = new DicDistrict();
        dicDistrict.setAddressCode(addressCode);
        dicDistrict.setAddressName(addressName);
        dicDistrict.setLevel(generateLevel(level));
        dicDistrict.setParentAddressCode(parentAddressCode);

        dicDistrictMapper.insert(dicDistrict);
    }

    public int generateLevel(String level) {
        int levelInt = 0;
        if (level.trim().equals("country")) {
            levelInt = 0;
        } else if (level.trim().equals("province")) {
            levelInt = 1;
        } else if (level.trim().equals("city")) {
            levelInt = 2;
        } else if (level.trim().equals("district")) {
            levelInt = 3;
        }
        return levelInt;
    }


}
