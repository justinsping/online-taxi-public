package com.zsp.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class PassengerUser implements Serializable {

    private Long id;

    private String passengerName;

    private String passengerPhone;

    private byte passengerGender;

    private byte state;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    private String passengerAvatarUrl;
}
