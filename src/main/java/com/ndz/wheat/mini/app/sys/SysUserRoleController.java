package com.ndz.wheat.mini.app.sys;

import com.alibaba.fastjson2.JSON;
import com.ndz.wheat.mini.common.bean.ApiResult;
import com.ndz.wheat.mini.common.page.PageData;
import com.ndz.wheat.mini.service.sys.SysUserRoleService;
import com.ndz.wheat.mini.vo.sys.SysRoleVO;
import com.ndz.wheat.mini.utils.ApiResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

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
}
