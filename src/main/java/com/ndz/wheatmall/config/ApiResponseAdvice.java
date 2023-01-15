package com.ndz.wheatmall.config;

import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ndz.wheatmall.common.annotation.NoApiResponse;
import com.ndz.wheatmall.common.bean.ApiResult;
import com.ndz.wheatmall.common.enums.StateEnum;
import com.ndz.wheatmall.exception.ApiException;
import com.ndz.wheatmall.utils.ApiResultUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 只对自己项目的包做统一返回，如不加basePackages，swagger等其他访问将异常
 */
@RestControllerAdvice(basePackages = {"com.ndz.wheatmall"}, annotations = RestControllerAdvice.class)
@Slf4j
public class ApiResponseAdvice implements ResponseBodyAdvice<Object>{


    /**
     *  如果是ApiResult返回类、类和方法上有NoApiResponse注解，均不包装
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.getParameterType() != ApiResult.class
                && AnnotationUtils.findAnnotation(returnType.getMethod(), NoApiResponse.class) == null
                && AnnotationUtils.findAnnotation(returnType.getDeclaringClass(), NoApiResponse.class) == null;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        // String类型不能直接包装
        if (body instanceof String) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                // 将数据包装在ResultVo里后转换为json串进行返回
                return objectMapper.writeValueAsString(ApiResultUtils.makeSuccessMsg(body));
            } catch (JsonProcessingException e) {
                throw new ApiException(StateEnum.RESPONSE_PACK_ERROR, e.getMessage());
            }
        }

        return ApiResultUtils.makeSuccessMsg(body);
    }
}
