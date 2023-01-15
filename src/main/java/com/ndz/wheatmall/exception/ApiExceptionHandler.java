package com.ndz.wheatmall.exception;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson2.JSON;
import com.ndz.wheatmall.common.enums.BizCodeEnum;
import com.ndz.wheatmall.utils.ApiResult;
import com.ndz.wheatmall.utils.ExceptionUtils;
import com.ndz.wheatmall.utils.HttpContextUtils;
import com.ndz.wheatmall.utils.IpUtils;
import com.ndz.wheatmall.entity.base.LogErrorEntity;
import com.ndz.wheatmall.service.sys.LogErrorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import cn.hutool.core.map.MapUtil;




/**
 * 全局异常处理器
 *
 */
@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

	@Autowired
	private LogErrorService logErrorService;

	/**
	 * 处理自定义异常
	 */
	@ExceptionHandler(ApiException.class)
	public ApiResult<String> handleRenException(ApiException ex){
		log.error(ex.getMsg(), ex);
		ApiResult<String> ApiResult = new ApiResult<>();
		ApiResult.error(ex.getCode(), ex.getMsg(), ex.getMessage());
		return ApiResult;
	}

	/**
	 * 数据库记录已存在冲突
	 */
	@ExceptionHandler(DuplicateKeyException.class)
	public ApiResult<String> handleDuplicateKeyException(DuplicateKeyException ex){
		ApiResult<String> ApiResult = new ApiResult<>();
		ApiResult.error(BizCodeEnum.DB_RECORD_EXISTS.getMsg());
		return ApiResult;
	}

	@ExceptionHandler(Exception.class)
	public ApiResult<String> handleException(Exception ex){
		log.error(ex.getMessage(), ex);
		// 保存异常日志
		// TODO 配置中自定义开启/关闭落库
		this.saveLog(ex);
		return new ApiResult<String>().error();
	}

	/**
	 * 保存异常日志
	 */
	private void saveLog(Exception ex){
		LogErrorEntity errorLog = new LogErrorEntity();

		//请求相关信息
		HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
		errorLog.setIp(IpUtils.getIpAddr(request));
		errorLog.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));
		errorLog.setRequestUri(request.getRequestURI());
		errorLog.setRequestMethod(request.getMethod());
		Map<String, String> params = HttpContextUtils.getParameterMap(request);
		if(MapUtil.isNotEmpty(params)){
			errorLog.setRequestParams(JSON.toJSONString(params));
		}

		//异常信息
		errorLog.setErrorInfo(ExceptionUtils.getErrorStackTrace(ex));

		//保存
		logErrorService.save(errorLog);
	}
}