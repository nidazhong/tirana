package com.ndz.wheat.mini.common.enums;

import lombok.Getter;

/**
 * 业务异常
 * 错误编码，由5位数字组成，前2位为模块编码，后3位为业务编码
 * <p>
 * 如：10001（10代表系统模块，001代表业务代码）
 * </p>
 */
@Getter
public enum BizCodeEnum implements StatusCode{

    APP_ERROR(10001, "业务异常"),
    PRICE_ERROR(10002, "价格异常"),
    DB_RECORD_EXISTS(10003, "数据库记录已存在"),

    JSON_STR_ILLEGAL(10004, "非JSON字符串"),

    NODE_ERROR(10005,"树形菜单不存在"),
    ACCOUNT_ERROR(10006, "账号名称错误"),
    PASSWORD_ERROR(10007, "账号密码错误"), ACCOUNT_STOP(10008, "账号不可用");


    private int code;
    private String msg;

    BizCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }



}
