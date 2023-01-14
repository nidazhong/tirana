package com.ndz.wheatmall.exception;

import lombok.Getter;
@Getter
public enum ResultCode implements StatusCode{
    SUCCESS(0, "请求成功"),
    FAILED(1, "请求失败"),
    INTERNAL_SERVER_ERROR(500, "服务内部错误"),
    VALIDATE_ERROR(500, "参数校验失败"),
    RESPONSE_PACK_ERROR(500, "response返回包装失败");

    private Integer code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


}
