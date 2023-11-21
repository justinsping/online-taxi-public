package com.zsp.constant;

import lombok.Getter;

public enum CommonStatusEnum {


    /**
     * 司机和车辆：1500-1599
     */
    TERMINAL_EXIST(1799, "终端已存在"),
    DRIVER_NOT_EXIST(1699, "司机不存在"),
    DRIVER_CAR_BIND_NOT_EXISTS(1500,"司机和车辆绑定关系不存在"),
    MAP_DISTRICT_ERROR(1499, "请求行政错误"),
    PRICE_RULE_ERROR(1399, "计价规则错误"),
    PRICE_RULE_EMPTY(1999, "计价规则不存在"),
    PRICE_RULE_EXISTS(1899, "计价规则已存在"),
    PRICE_RULE_NOT_EDIT(1302,"计价规则没有变化"),
    USER_NO_EXIST(1299, "用户不存在"),
    TOKEN_ERROR(1199, "token错误"),
    VERIFY_CODE_ERROR(1099, "验证码不正确"),
    SUCCESS(1, "success"),
    FAIL(0, "fail")
    ;

    @Getter
    private int code;
    @Getter
    private String value;

    CommonStatusEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }
}
