package com.ndz.wheat.mini.app.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ndz.wheat.mini.common.bean.ApiResult;
import com.ndz.wheat.mini.common.page.PageData;
import com.ndz.wheat.mini.dto.sys.SysOperLogQueryDTO;
import com.ndz.wheat.mini.service.sys.AsyncOperLogService;
import com.ndz.wheat.mini.utils.ApiResultUtils;
import com.ndz.wheat.mini.vo.sys.SysOperLogVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@Api(value = "操作日志管理", tags = "操作日志管理")
@RestController
@RequestMapping(value="/admin/system/sysOperLog")
public class SysOperLogController {
    @Autowired
    private AsyncOperLogService asyncOperLogService;

    @ApiOperation(value = "获取分页列表")
    @GetMapping("{page}/{limit}")
    public ApiResult<PageData<SysOperLogVO>> page(
            @ApiParam(name = "page", value = "当前页码", required = true, example = "1")
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true, example = "10")
            @PathVariable Long limit,
            @ApiParam(name = "dto", value = "查询对象", required = false)
            SysOperLogQueryDTO dto) {
        return ApiResultUtils.ok(asyncOperLogService.page(page, limit, dto));
    }


    @ApiOperation(value = "获取")
    @GetMapping("get/{id}")
    public ApiResult get(@PathVariable Long id) {
        SysOperLogVO sysOperLogVO = asyncOperLogService.getById(id);
        return ApiResultUtils.ok(sysOperLogVO);
    }

}
