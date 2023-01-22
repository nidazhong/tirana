package com.ndz.wheat.mini.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 指数控制器
 * web 包处理非前后端分离的请求，也就是@Controll的请求
 * @author nidazhong
 * @date 2023/01/16
 */
@Controller
public class WebIndexController {

    /**
     *  欢迎页
     * @return {@link String}
     */
    @GetMapping({"/", "/index.html","/index"})
    public String indexPage() {
        // 使用thymeleaf，默认会加上静态资源前后缀
        // 默认在classpath:/templates/ 中找资源
        return "index";
    }




}
