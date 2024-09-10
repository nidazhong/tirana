package com.ndz.tirana.utils;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ndz.tirana.common.constant.TiranaConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * http上下文
 *
 * @author nidazhong
 * @date 2023/01/16
 */
public class HttpContextUtils {

	public static HttpServletRequest getHttpServletRequest() {
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		if(requestAttributes == null){
			return null;
		}

		return ((ServletRequestAttributes) requestAttributes).getRequest();
	}

	public static Map<String, String> getParameterMap(HttpServletRequest request) {
		Enumeration<String> parameters = request.getParameterNames();

		Map<String, String> params = new HashMap<>();
		while (parameters.hasMoreElements()) {
			String parameter = parameters.nextElement();
			String value = request.getParameter(parameter);
			if (StringUtils.isNotBlank(value)) {
				params.put(parameter, value);
			}
		}

		return params;
	}

	public static String getDomain(){
		HttpServletRequest request = getHttpServletRequest();
		StringBuffer url = request.getRequestURL();
		return url.delete(url.length() - request.getRequestURI().length(), url.length()).toString();
	}

	public static String getOrigin(){
		HttpServletRequest request = getHttpServletRequest();
		return request.getHeader(HttpHeaders.ORIGIN);
	}

	public static String getToken(HttpServletRequest request){
		if (request == null) return null;
		String token = null;
        String bearerToken = request.getHeader(TiranaConstant.AUTH_HEADER);
        if (bearerToken != null && bearerToken.startsWith(TiranaConstant.TOKEN_HEADER)) {
            token =  bearerToken.substring(7);
        }
		return token;
	}
}