package com.ndz.wheat.mini.app.sys;


import com.ndz.wheat.mini.common.page.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ndz.wheat.mini.common.bean.ApiResult;
import com.ndz.wheat.mini.dto.sys.QuerySysUserDTO;
import com.ndz.wheat.mini.dto.sys.SysUserDTO;
import com.ndz.wheat.mini.service.sys.SysUserService;
import com.ndz.wheat.mini.utils.ApiResultUtils;
import com.ndz.wheat.mini.vo.sys.SysUserVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "用户管理")
@RestController
@RequestMapping("/admin/system/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation(value = "获取分页列表")
    @GetMapping("/{page}/{limit}")
    public ApiResult<PageData<SysUserVO>> page(
            @ApiParam(name = "page", value = "当前页码", required = true, example = "1")
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true, example = "10")
            @PathVariable Long limit,
            @ApiParam(name = "query", value = "查询对象", required = false)
            QuerySysUserDTO query) {

        return ApiResultUtils.ok(sysUserService.page(page, limit, query));
    }

    @ApiOperation(value = "获取用户")
    @GetMapping("/get/{id}")
    public ApiResult<SysUserVO> get(@PathVariable Long id) {
        SysUserVO user = sysUserService.getById(id);
        return ApiResultUtils.ok(user);
    }

    @ApiOperation(value = "保存用户")
    @PostMapping("/save")
    public ApiResult<Object> save(@RequestBody SysUserDTO user) {
        sysUserService.save(user);
        return ApiResultUtils.ok();
    }

    @ApiOperation(value = "更新用户")
    @PutMapping("/update")
    public ApiResult<Object> updateById(@RequestBody SysUserDTO user) {
        sysUserService.updateById(user);
        return ApiResultUtils.ok();
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("/remove/{id}")
    public ApiResult<Object> remove(@PathVariable Long id) {
        sysUserService.removeById(id);
        return ApiResultUtils.ok();
    }

    @ApiOperation(value = "更新状态")
    @GetMapping("updateStatus/{id}/{status}")
    public ApiResult<Object> updateStatus(@PathVariable Long id, @PathVariable Integer status) {
        sysUserService.updateStatus(id, status);
        return ApiResultUtils.ok();
    }
}
