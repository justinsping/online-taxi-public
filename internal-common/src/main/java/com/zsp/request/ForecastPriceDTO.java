package com.zsp.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class ForecastPriceDTO implements Serializable {

    private String depLongitude;
    private String depLatitude;
    private String destLongitude;
    private String destLatitude;
}
