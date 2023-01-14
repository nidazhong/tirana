package com.ndz.wheatmall.common.enums;

import lombok.Getter;

/**
 * 业务异常
 * 错误编码，由5位数字组成，前2位为模块编码，后3位为业务编码
 * <p>
 * 如：10001（10代表系统模块，001代表业务代码）
 * </p>
 */
@Getter
public enum BizCodeEnum {

    // ---------- 与系统相关错误----------
    SUCCESS(0, "请求成功"),
    FAILED(1, "请求失败"),
    INTERNAL_SERVER_ERROR(500, "服务内部错误"),
    VALIDATE_ERROR(500, "参数校验失败"),
    RESPONSE_PACK_ERROR(500, "response返回包装失败"),

    // ---------- 与业务相关错误----------
    APP_ERROR(10001, "业务异常"),
    PRICE_ERROR(10002, "价格异常"),
    DB_RECORD_EXISTS(10003, "数据库记录已存在"),
    JSON_STR_ILLEGAL(10004, "非JSON字符串");


    private Integer code;
    private String msg;

    BizCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


}
