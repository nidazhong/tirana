package com.ndz.tirana.exception;

import com.ndz.tirana.common.enums.BizCodeEnum;
import com.ndz.tirana.common.enums.StatusCode;

import java.io.Serial;

/**
 * Wheatmall自定义异常, 业务异常
 */
public class ApiException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 1L;

    private Integer code;
	private String msg;


	// 手动设置异常
	public ApiException(StatusCode statusCode, String msg) {
		// message 用于用户设置抛出错误详情，例如：当前价格-5，小于0
		super(msg);
		// 状态码
		this.code = statusCode.getCode();
		// 状态码配套的msg
		this.msg = statusCode.getMsg();
	}

	/**
	 * 默认业务异常Code
	 */
	public ApiException(String msg) {
		super(msg);
		this.code = BizCodeEnum.APP_ERROR.getCode();
		this.msg = msg;
	}

	public ApiException(String msg, Throwable e) {
		super(msg, e);
		this.code = BizCodeEnum.APP_ERROR.getCode();
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

}