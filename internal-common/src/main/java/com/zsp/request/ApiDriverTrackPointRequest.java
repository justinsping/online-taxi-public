package com.zsp.request;

import lombok.Data;

@Data
public class ApiDriverTrackPointRequest {

    private Long carId;

    private PointDTO[] points;
}
