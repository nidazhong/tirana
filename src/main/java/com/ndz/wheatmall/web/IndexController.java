package com.ndz.wheatmall.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * web 包处理非前后端分离的请求，也就是@Controll的请求
 */
@Controller
public class IndexController {

    @GetMapping({"/", "/index.html"})
    public String indexPage() {
        // 使用thymeleaf，默认会加上静态资源前后缀
        // 默认在classpath:/templates/ 中找资源
        return "index";
    }
}
