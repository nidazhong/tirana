package com.ndz.wheatmall.common.utils;

import com.ndz.wheatmall.exception.BizCodeEnum;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * Controller统一结果返回
 */
@Data
public class ApiResult<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 编码：0表示成功，其他值表示失败
     */
    private Integer code;
    /**
     * 消息内容
     */
    private String msg;
    /**
     * 响应数据
     */
    private T data;

    public ApiResult<T> ok(T data) {
        this.code = BizCodeEnum.SUCCESS.getCode();
        this.msg = BizCodeEnum.SUCCESS.getMsg();
        this.setData(data);
        return this;
    }

    public ApiResult<T> ok() {
        this.code = BizCodeEnum.SUCCESS.getCode();
        this.msg = BizCodeEnum.SUCCESS.getMsg();
        return this;
    }

    public boolean success(){
        return code == 0;
    }

    public ApiResult<T> error() {
        this.code = BizCodeEnum.FAILED.getCode();
        this.msg = BizCodeEnum.FAILED.getMsg();
        return this;
    }


    public ApiResult<T> error(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }

    public ApiResult<T> error(String msg) {
        this.code = BizCodeEnum.FAILED.getCode();
        this.msg = msg;
        return this;
    }

    public void error(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


}
