package com.ndz.wheat.mini.app.sys;

import com.ndz.wheat.mini.common.bean.ApiResult;
import com.ndz.wheat.mini.dto.sys.AssginMenuDTO;
import com.ndz.wheat.mini.dto.sys.SaveSysMenuDTO;
import com.ndz.wheat.mini.entity.sys.SysMenuEntity;
import com.ndz.wheat.mini.service.sys.SysMenuService;
import com.ndz.wheat.mini.utils.ApiResultUtils;
import com.ndz.wheat.mini.vo.sys.SysMenuVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "菜单管理")
@RestController
@RequestMapping("/admin/system/sysMenu")
public class SysMenuController {
    @Autowired
    private SysMenuService sysMenuService;

    @ApiOperation(value = "获取菜单")
    @GetMapping("findNodes")
    public ApiResult findNodes() {
        List<SysMenuVO> list = sysMenuService.findNodes();
        return ApiResultUtils.ok(list);
    }

    @ApiOperation(value = "新增菜单")
    @PostMapping("save")
    public ApiResult save(@RequestBody SaveSysMenuDTO permission) {
        sysMenuService.save(permission);
        return ApiResultUtils.ok();
    }

    @ApiOperation(value = "修改菜单")
    @PutMapping("update")
    public ApiResult updateById(@RequestBody SaveSysMenuDTO permission) {
        sysMenuService.updateById(permission);
        return ApiResultUtils.ok();
    }

    @ApiOperation(value = "删除菜单")
    @DeleteMapping("remove/{id}")
    public ApiResult remove(@PathVariable Long id) {
        sysMenuService.removeById(id);
        return ApiResultUtils.ok();
    }


    @ApiOperation(value = "根据角色获取菜单")
    @GetMapping("/toAssign/{roleId}")
    public ApiResult toAssign(@PathVariable Long roleId) {
        List<SysMenuVO> list = sysMenuService.findSysMenuByRoleId(roleId);
        return ApiResultUtils.ok(list);
    }

    @ApiOperation(value = "给角色分配权限")
    @PostMapping("/doAssign")
    public ApiResult doAssign(@RequestBody AssginMenuDTO dto) {
        sysMenuService.doAssign(dto);
        return ApiResultUtils.ok();
    }
}
