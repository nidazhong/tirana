package com.ndz.wheat.mini.app.sys;

import com.alibaba.fastjson2.JSON;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.ndz.wheat.mini.common.bean.ApiResult;
import com.ndz.wheat.mini.common.page.PageData;
import com.ndz.wheat.mini.dao.base.DeleteDTO;
import com.ndz.wheat.mini.dto.sys.SaveSysRoleDTO;
import com.ndz.wheat.mini.service.sys.SysUserRoleService;
import com.ndz.wheat.mini.vo.sys.SysRoleVO;
import com.ndz.wheat.mini.utils.ApiResultUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("admin/system/sysRole")
public class SysUserRoleController {

    @Autowired
    SysUserRoleService sysUserRoleService;

    @GetMapping("/page")
    public ApiResult<PageData<SysRoleVO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        log.info("角色分页请求Req: {}", JSON.toJSONString(params));
        return ApiResultUtils.ok(sysUserRoleService.page(params));
    }

    @PostMapping("/save")
    public ApiResult<Object> save(@RequestBody SaveSysRoleDTO dto) {
        sysUserRoleService.save(dto);
        return ApiResultUtils.ok();
    }


    @DeleteMapping(value = "/remove/{id}")
    public ApiResult<Object> remove(@PathVariable String id) {
        sysUserRoleService.remove(id);
        return ApiResultUtils.ok();
    }

    @DeleteMapping(value = "/batchRemove")
    public ApiResult<Object> batchRemove(@RequestBody Long[] idList) {
        sysUserRoleService.batchRemove(idList);
        return ApiResultUtils.ok();
    }

    @GetMapping("/info/{id}")
    public ApiResult<SysRoleVO> info(@PathVariable String id) {
        return ApiResultUtils.ok(sysUserRoleService.info(id));
    }


    @PutMapping("/update")
    public ApiResult<Object> update(@RequestBody SaveSysRoleDTO dto) {
        sysUserRoleService.update(dto);
        return ApiResultUtils.ok();
    }






}
