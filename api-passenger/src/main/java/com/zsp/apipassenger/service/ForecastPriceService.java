package com.zsp.apipassenger.service;

import com.zsp.apipassenger.remote.ServicePriceClient;
import com.zsp.dto.ResponseResult;
import com.zsp.request.ForecastPriceDTO;
import com.zsp.response.ForecastPriceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ForecastPriceService {

    @Autowired
    private ServicePriceClient servicePriceClient;

    public ResponseResult forecastPrice(String depLongitude, String depLatitude, String destLongitude, String destLatitude) {
        log.info("调用计价服务，计算价格");
        ForecastPriceDTO forecastPriceDTO = new ForecastPriceDTO();
        forecastPriceDTO.setDepLongitude(depLongitude);
        forecastPriceDTO.setDepLatitude(depLatitude);
        forecastPriceDTO.setDestLongitude(destLongitude);
        forecastPriceDTO.setDestLatitude(destLatitude);
        ResponseResult<ForecastPriceResponse> forecastPriceResponse = servicePriceClient.forecastPrice(forecastPriceDTO);
        double price = forecastPriceResponse.getData().getPrice();

        ForecastPriceResponse forecastPriceResponse1 = new ForecastPriceResponse();
        forecastPriceResponse1.setPrice(price);

        return ResponseResult.success(forecastPriceResponse1);
    }
}
