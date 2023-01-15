package com.ndz.wheatmall.utils;

import com.ndz.wheatmall.common.bean.ApiResult;
import com.ndz.wheatmall.common.enums.BizCodeEnum;
import com.ndz.wheatmall.common.enums.StateEnum;

/**
 * 统一返回工具类
 */
public class ApiResultUtils {
    public ApiResultUtils() {}

    public static <T> ApiResult<T> makeCustomizeMsg(int status, String msg, T data) {
        ApiResult<T> rm = new ApiResult<>(status, msg);
        rm.setData(data);
        return rm;
    }

    public static <T> ApiResult<T> makeSuccessMsg(String msg, T data) {
        return new ApiResult<>(StateEnum.SUCCESS.getCode(), msg, data);
    }

    public static <T> ApiResult<T> makeSuccessMsg(String message) {
        return new ApiResult<>(StateEnum.SUCCESS.getCode(), message,null);
    }

    public static <T> ApiResult<T> makeSuccessMsg(T data) {
        ApiResult<T> rm = new ApiResult<>();
        rm.setCode(StateEnum.SUCCESS.getCode());
        rm.setMsg(StateEnum.SUCCESS.getMsg());
        rm.setData(data);
        return rm;
    }

    public static <T> ApiResult<T> makeErrorMsg(Integer code, String msg, T data) {
        return new ApiResult<>(code, msg, data);
    }

    public static <T> ApiResult<T> makeErrorMsg(String message) {
        return new ApiResult<>(StateEnum.FAILED.getCode(), message, null);
    }

    public static <T> ApiResult<T> makeErrorMsg() {
        return new ApiResult<>(StateEnum.FAILED.getCode(), StateEnum.FAILED.getMsg(), null);
    }
}
