package com.zsp.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author 张世平
 * @since 2023-11-14
 */
@Data
public class PriceRule implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 城市编码
     */
    private String cityCode;

    /**
     * 车辆类型
     */
    private String vehicleType;

    /**
     * 起步价格
     */
    private Double startFare;

    /**
     * 起步公里数
     */
    private Integer startMile;

    /**
     * 计程单价
     */
    private Double unitPricePerMile;

    /**
     * 计时单价
     */
    private Double unitPricePerMinute;

    /**
     * 运价版本
     */
    private Integer fareVersion;

    /**
     * 运价类型
     */
    private String fareType;

}
