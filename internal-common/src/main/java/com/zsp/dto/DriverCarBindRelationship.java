package com.zsp.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author 张世平
 * @since 2022-09-19
 */
@Data
public class DriverCarBindRelationship implements Serializable {

    private Long id;

    private Long driverId;

    private Long carId;

    /**
     * 绑定状态：1 绑定， 2 解绑
     */
    private Integer bindState;

    private LocalDateTime bindTime;

    private LocalDateTime unBindTime;
}
