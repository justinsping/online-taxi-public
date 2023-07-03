package com.zsp.request;

import lombok.Data;

@Data
public class TrackPointUploadRequest {

    private String tid;

    private String trid;

    private PointDTO[] points;
}
