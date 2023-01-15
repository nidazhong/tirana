package com.ndz.wheatmall.controller.base;

import com.ndz.wheatmall.common.constant.MybatisPlusConstant;
import com.ndz.wheatmall.common.page.PageData;
import com.ndz.wheatmall.service.base.LogErrorService;
import com.ndz.wheatmall.vo.base.LogErrorVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/base/logError")
@Api(tags="异常日志模块")
public class LogErrorController {

    @Autowired
    LogErrorService logErrorService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = MybatisPlusConstant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
            @ApiImplicitParam(name = MybatisPlusConstant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
            @ApiImplicitParam(name = MybatisPlusConstant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
            @ApiImplicitParam(name = MybatisPlusConstant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    public PageData<LogErrorVO> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        return logErrorService.page(params);
    }

//    @GetMapping("export")
//    @ApiOperation("导出")
//    @LogOperation("导出")
//    @RequiresPermissions("sys:log:error")
//    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
//        List<LogErrorVO> list = sysLogErrorService.list(params);
//
//        ExcelUtils.exportExcelToTarget(response, null, list, SysLogErrorExcel.class);
//    }
}
