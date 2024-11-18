package com.ndz.tirana.app.demo;

import com.ndz.tirana.common.bean.ApiResult;
import com.ndz.tirana.service.sys.MonitorDemoService;
import com.ndz.tirana.utils.ApiResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
//@Api(tags = "Monitor示例")
//@ApiSupport(author = "Jack Lee",order = 1)
@RequestMapping("/monitor")
@RestController
public class MonitorDemoController {
    @Autowired
    MonitorDemoService monitorDemoService;

    ////@ApiOperation(value = "调用示例",notes = "调用示例")
    ////@ApiOperationSupport(author = "作者xxx", order = 1)
    @PostMapping("/call")
    public ApiResult call(){
        monitorDemoService.myMethod();
        return ApiResultUtils.ok();
    }
}
