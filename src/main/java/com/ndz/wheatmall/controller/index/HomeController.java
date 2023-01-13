package com.ndz.wheatmall.controller.index;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HomeController {

    @GetMapping("/hello")
    public String hello() {
        log.debug("debug");
        log.info("info");
        log.warn("warn");
        log.error("error");
        return "helloWorld";
    }




}
