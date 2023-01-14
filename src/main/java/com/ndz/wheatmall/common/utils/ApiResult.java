package com.ndz.wheatmall.common.utils;

import com.ndz.wheatmall.exception.ErrorCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * Controller统一结果返回
 */
public class ApiResult<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 编码：0表示成功，其他值表示失败
     */
    private int code = 0;
    /**
     * 消息内容
     */
    private String msg = "success";
    /**
     * 响应数据
     */
    private T data;

    public ApiResult<T> ok(T data) {
        this.setData(data);
        return this;
    }

    public boolean success(){
        return code == 0;
    }

    public ApiResult<T> error() {
        this.code = ErrorCode.INTERNAL_SERVER_ERROR;
        this.msg = MessageUtils.getMessage(this.code);
        return this;
    }

    public ApiResult<T> error(int code) {
        this.code = code;
        this.msg = MessageUtils.getMessage(this.code);
        return this;
    }

    public ApiResult<T> error(int code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }

    public ApiResult<T> error(String msg) {
        this.code = ErrorCode.INTERNAL_SERVER_ERROR;
        this.msg = msg;
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
