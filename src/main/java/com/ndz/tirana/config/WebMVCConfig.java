package com.ndz.tirana.config;

import com.ndz.tirana.common.constant.TiranaConstant;
import com.ndz.tirana.config.interceptor.LoginInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages = "com.ndz.tirana")
public class WebMVCConfig  implements WebMvcConfigurer {
    /**
     * 注册自定义拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(TiranaConstant.SWAGGER_PATH) // swagger 放开
                .excludePathPatterns("/admin/system/index/login");//开放登录路径
    }
}
