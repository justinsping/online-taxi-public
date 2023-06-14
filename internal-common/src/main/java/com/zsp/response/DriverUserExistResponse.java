package com.zsp.response;

import lombok.Data;

@Data
public class DriverUserExistResponse {

    private int isExist;

    private String driverPhone;
}