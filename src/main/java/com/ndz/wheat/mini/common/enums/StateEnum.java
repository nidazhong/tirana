package com.ndz.wheat.mini.common.enums;

import lombok.Getter;

@Getter
public enum StateEnum implements StatusCode{
    SUCCESS(0, "请求成功"),
    FAILED(1, "请求失败"),
    INTERNAL_SERVER_ERROR(500, "服务内部错误"),
    VALIDATE_ERROR(500, "参数校验失败"),
    RESPONSE_PACK_ERROR(500, "response返回包装失败");


    private int code;
    private String msg;

    public static StateEnum get(int code) {
        StateEnum[] var1 = values();

        for (StateEnum p : var1) {
            if (code == p.getCode()) {
                return p;
            }
        }

        return FAILED;
    }

    StateEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


}
