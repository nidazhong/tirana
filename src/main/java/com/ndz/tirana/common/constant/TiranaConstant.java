package com.ndz.tirana.common.constant;


public interface TiranaConstant {

    String TOKEN_HEADER = "Bearer ";
    String AUTH_HEADER = "Authorization";
    String REDIS_PREFIX = "wheat-auth-permission:";
    /**
     * 过滤器和拦截器不处理的路径
     */
    String[] SWAGGER_PATH = {"/swagger-resources/**",
            "/webjars/**", "/v2/**", "/swagger-ui.html/**", "/doc.html"};
}
