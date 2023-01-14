package com.ndz.wheatmall.exception;

import lombok.Getter;

/**
 * 业务异常
 * 错误编码，由5位数字组成，前2位为模块编码，后3位为业务编码
 * <p>
 * 如：10001（10代表系统模块，001代表业务代码）
 * </p>
 *
 */
@Getter
public enum AppBizCode implements StatusCode{


    APP_ERROR(10001, "业务异常"),
    PRICE_ERROR(10002, "价格异常"),
    DB_RECORD_EXISTS(10003, "数据库记录已存在"),
    JSON_STR_ILLEGAL(10004, "非JSON字符串");


    private Integer code;
    private String msg;

    AppBizCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


//    @Override
//    public Integer getCode() {
//        return code;
//    }
//
//    @Override
//    public String getMsg() {
//        return msg;
//    }


}
