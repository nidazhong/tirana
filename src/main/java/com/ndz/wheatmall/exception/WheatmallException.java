/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.ndz.wheatmall.exception;


import com.ndz.wheatmall.common.utils.MessageUtils;

import java.io.Serial;

/**
 * Wheatmall自定义异常
 */
public class WheatmallException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 1L;

    private int code;
	private String msg;

	public WheatmallException(int code) {
		this.code = code;
		this.msg = MessageUtils.getMessage(code);
	}

	public WheatmallException(int code, String... params) {
		this.code = code;
		this.msg = MessageUtils.getMessage(code, params);
	}

	public WheatmallException(int code, Throwable e) {
		super(e);
		this.code = code;
		this.msg = MessageUtils.getMessage(code);
	}

	public WheatmallException(int code, Throwable e, String... params) {
		super(e);
		this.code = code;
		this.msg = MessageUtils.getMessage(code, params);
	}

	public WheatmallException(String msg) {
		super(msg);
		this.code = ErrorCode.INTERNAL_SERVER_ERROR;
		this.msg = msg;
	}

	public WheatmallException(String msg, Throwable e) {
		super(msg, e);
		this.code = ErrorCode.INTERNAL_SERVER_ERROR;
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}