package com.ndz.wheatmall.app.sys;

import cn.hutool.core.map.MapUtil;
import com.ndz.wheatmall.common.bean.ApiResult;
import com.ndz.wheatmall.utils.ApiResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Api(tags = "权限系统登陆管理")
@RestController
@RequestMapping("/admin/system/index")
public class IndexController {

    @ApiOperation("登陆")
    @PostMapping("/login")
    public ApiResult<Map<String,Object>> login() {
        Map<String, Object> map = MapUtil.of("token", "admin");
        return ApiResultUtils.ok(map);
    }



    @ApiOperation("用户信息")
    @GetMapping("/info")
    public ApiResult<Map<String,Object>> info() {
        Map<String, Object> map = new HashMap<>();
        map.put("roles","[admin]");
        map.put("name","admin");
        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return ApiResultUtils.ok(map);
    }

    @ApiOperation("登出")
    @PostMapping("/logout")
    public ApiResult<Map<String,Object>> logout(){
        return ApiResultUtils.ok();
    }






}
