package com.ndz.tirana.app.sys;

import com.ndz.tirana.vo.sys.SysLoginLogVO;
import com.ndz.tirana.common.bean.ApiResult;
import com.ndz.tirana.common.page.PageData;
import com.ndz.tirana.dto.sys.SysLoginLogQueryDTO;
import com.ndz.tirana.service.sys.AsyncLoginLogService;
import com.ndz.tirana.utils.ApiResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "SysLoginLog管理", tags = "SysLoginLog管理")
@RestController
@RequestMapping(value="/admin/system/sysLoginLog")
public class SysLoginRecordLogController {
    @Autowired
    private AsyncLoginLogService sysLoginLogService;

    @ApiOperation(value = "获取分页列表")
    @GetMapping("{page}/{limit}")
    public ApiResult<PageData<SysLoginLogVO>> page(
            @ApiParam(name = "page", value = "当前页码", required = true, example = "1")
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true, example = "10")
            @PathVariable Long limit,
            @ApiParam(name = "dto", value = "查询对象", required = false)
            SysLoginLogQueryDTO dto) {
        return ApiResultUtils.ok(sysLoginLogService.page(page, limit, dto));
    }


    @ApiOperation(value = "获取")
    @GetMapping("get/{id}")
    public ApiResult get(@PathVariable Long id) {
        SysLoginLogVO sysLoginLog = sysLoginLogService.getById(id);
        return ApiResultUtils.ok(sysLoginLog);
    }

}
