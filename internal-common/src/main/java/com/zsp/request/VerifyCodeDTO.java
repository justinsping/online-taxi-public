package com.zsp.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VerifyCodeDTO {

    private String passengerPhone;

    private String verificationCode;

    private String driverPhone;
}
