package com.ndz.wheatmall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * knife4j 对Swagger的增强
 */
@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfiguration {

    private static final String TERMS_OF_SERVICE_URL = "http://www.wheatmall.com";

    @Bean
    public Docket defaultApi2() {
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                // 配置文档信息
                .apiInfo(apiInfo("# 麦子商城APIs","1.0"))
                // 配置是否启用Swagger，如果是false，在浏览器将无法访问
                .enable(true)
                // 扫描所有，项目中的所有接口都会被扫描到
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.ndz.wheatmall.controller"))
                // 配置接口扫描过滤
                .paths(PathSelectors.ant("/**"))
                .build();
        return docket;
    }


    private ApiInfo apiInfo(String description,  String version) {
        String termsOfServiceUrl = "http://www.wheatmall.com"; // 组织链接
        Contact contact = new Contact("联系人名字", "http://xxx.xxx.com/联系人访问链接", "联系人邮箱");
        ApiInfo apiInfo = new ApiInfoBuilder()
                //
                .contact(contact)
                .description(description)
                .version(version)
                .termsOfServiceUrl(termsOfServiceUrl)
                .build();
        return apiInfo;
    }
}
