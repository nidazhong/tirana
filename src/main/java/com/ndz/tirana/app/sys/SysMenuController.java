package com.ndz.tirana.app.sys;

import java.util.List;

import com.ndz.tirana.vo.sys.SysMenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ndz.tirana.common.bean.ApiResult;
import com.ndz.tirana.dto.sys.AssginMenuDTO;
import com.ndz.tirana.dto.sys.SaveSysMenuDTO;
import com.ndz.tirana.service.sys.SysMenuService;
import com.ndz.tirana.utils.ApiResultUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "菜单管理")
@RestController
@RequestMapping("/admin/system/sysMenu")
public class SysMenuController {
    @Autowired
    private SysMenuService sysMenuService;

    @ApiOperation(value = "获取菜单")
    @GetMapping("findNodes")
    public ApiResult<List<SysMenuVO>> findNodes() {
        List<SysMenuVO> list = sysMenuService.findNodes();
        return ApiResultUtils.ok(list);
    }

    @ApiOperation(value = "新增菜单")
    @PostMapping("save")
    public ApiResult<Object> save(@RequestBody SaveSysMenuDTO permission) {
        sysMenuService.save(permission);
        return ApiResultUtils.ok();
    }

    @ApiOperation(value = "修改菜单")
    @PutMapping("update")
    public ApiResult<Object> updateById(@RequestBody SaveSysMenuDTO permission) {
        sysMenuService.updateById(permission);
        return ApiResultUtils.ok();
    }

    @ApiOperation(value = "删除菜单")
    @DeleteMapping("remove/{id}")
    public ApiResult<Object> remove(@PathVariable Long id) {
        sysMenuService.removeById(id);
        return ApiResultUtils.ok();
    }


    @ApiOperation(value = "根据角色获取菜单")
    @GetMapping("/toAssign/{roleId}")
    public ApiResult<Object> toAssign(@PathVariable Long roleId) {
        List<SysMenuVO> list = sysMenuService.findSysMenuByRoleId(roleId);
        return ApiResultUtils.ok(list);
    }

    @ApiOperation(value = "给角色分配权限")
    @PostMapping("/doAssign")
    public ApiResult<Object> doAssign(@RequestBody AssginMenuDTO dto) {
        sysMenuService.doAssign(dto);
        return ApiResultUtils.ok();
    }
}
