package com.ndz.wheat.mini.app.sys;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ndz.wheat.mini.common.bean.ApiResult;
import com.ndz.wheat.mini.dto.sys.QuerySysUserDTO;
import com.ndz.wheat.mini.dto.sys.SysUserDTO;
import com.ndz.wheat.mini.service.sys.SysUserService;
import com.ndz.wheat.mini.utils.ApiResultUtils;
import com.ndz.wheat.mini.vo.sys.SysUserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "用户管理")
@RestController
@RequestMapping("/admin/system/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation(value = "获取分页列表")
    @GetMapping("/{page}/{limit}")
    public ApiResult page(
            @ApiParam(name = "page", value = "当前页码", required = true, example = "1")
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true, example = "10")
            @PathVariable Long limit,
            @ApiParam(name = "userQueryVo", value = "查询对象", required = false)
            QuerySysUserDTO query) {
        Page<SysUserVO> pageParam = new Page<>(page, limit);
        IPage<SysUserVO> pageModel = sysUserService.page(pageParam, query);
        return ApiResultUtils.ok(pageModel);
    }

    @ApiOperation(value = "获取用户")
    @GetMapping("/get/{id}")
    public ApiResult get(@PathVariable Long id) {
        SysUserVO user = sysUserService.getById(id);
        return ApiResultUtils.ok(user);
    }

    @ApiOperation(value = "保存用户")
    @PostMapping("/save")
    public ApiResult save(@RequestBody SysUserDTO user) {
        sysUserService.save(user);
        return ApiResultUtils.ok();
    }

    @ApiOperation(value = "更新用户")
    @PutMapping("/update")
    public ApiResult updateById(@RequestBody SysUserDTO user) {
        sysUserService.updateById(user);
        return ApiResultUtils.ok();
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("/remove/{id}")
    public ApiResult remove(@PathVariable Long id) {
        sysUserService.removeById(id);
        return ApiResultUtils.ok();
    }
}
