package com.ndz.wheat.mini.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ndz.wheat.mini.common.bean.ApiResult;
import com.ndz.wheat.mini.common.enums.StateEnum;
import com.ndz.wheat.mini.common.enums.StatusCode;


/**
 * api统一返回工具类
 *
 * @author nidazhong
 * @date 2023/01/16
 */
public class ApiResultUtils {
    public ApiResultUtils() {}

    public static <T> ApiResult<T> makeCustomizeMsg(int status, String msg, T data) {
        ApiResult<T> rm = new ApiResult<>(status, msg);
        rm.setData(data);
        return rm;
    }

    public static <T> ApiResult<T> ok(String msg, T data) {
        return new ApiResult<>(StateEnum.SUCCESS.getCode(), msg, data);
    }

    public static <T> ApiResult<T> ok(String message) {
        return new ApiResult<>(StateEnum.SUCCESS.getCode(), message,null);
    }

    public static <T> ApiResult<T> ok(T data) {
        ApiResult<T> rm = new ApiResult<>(StateEnum.SUCCESS);
        rm.setData(data);
        return rm;
    }

    public static <T> ApiResult<T> ok() {
        return new ApiResult<>(StateEnum.SUCCESS.getCode(), StateEnum.SUCCESS.getMsg(),null);
    }

    /**
     * 对未进行错误枚举定义的使用关闭
     */
    public static <T> ApiResult<T> error(StatusCode errorStatusCode, T data) {
        return new ApiResult<>(errorStatusCode);
    }

    public static <T> ApiResult<T> error(Integer code, String msg, T data) {
        return new ApiResult<>(code, msg, data);
    }

    public static <T> ApiResult<T> error(String message) {
        return new ApiResult<>(StateEnum.FAILED.getCode(), message, null);
    }

    public static <T> ApiResult<T> error() {
        return new ApiResult<>(StateEnum.FAILED);
    }

    public static void out(HttpServletResponse response, ApiResult r) {
        ObjectMapper mapper = new ObjectMapper();
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        try {
            mapper.writeValue(response.getWriter(), r);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
