package com.ndz.tirana.app.sys;

import java.util.Map;

import com.ndz.tirana.common.constant.MybatisConstant;
import com.ndz.tirana.vo.sys.LogErrorVO;
import com.ndz.tirana.common.bean.ApiResult;
import com.ndz.tirana.utils.ApiResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ndz.tirana.common.page.PageData;
import com.ndz.tirana.service.sys.SysErrorLogService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/admin/system/sysErrorLog")
@Api(tags="异常日志模块")
public class SysErrorRecordLogController {

    @Autowired
    SysErrorLogService logErrorService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = MybatisConstant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
            @ApiImplicitParam(name = MybatisConstant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
            @ApiImplicitParam(name = MybatisConstant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
            @ApiImplicitParam(name = MybatisConstant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    public ApiResult<PageData<LogErrorVO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        return ApiResultUtils.ok(logErrorService.page(params));
    }

    @ApiOperation(value = "详情")
    @GetMapping("info/{id}")
    public ApiResult<LogErrorVO> get(@PathVariable Long id) {
        LogErrorVO errorLog = logErrorService.getById(id);
        return ApiResultUtils.ok(errorLog);
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
