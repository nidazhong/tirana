package com.ndz.wheat.mini.app.sys;

import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson2.JSON;
import com.ndz.wheat.mini.common.bean.ApiResult;
import com.ndz.wheat.mini.common.enums.BizCodeEnum;
import com.ndz.wheat.mini.common.helper.JwtHelper;
import com.ndz.wheat.mini.config.security.UserSessionContext;
import com.ndz.wheat.mini.dto.sys.LoginDTO;
import com.ndz.wheat.mini.exception.WheatException;
import com.ndz.wheat.mini.service.sys.IndexService;
import com.ndz.wheat.mini.service.sys.SysUserService;
import com.ndz.wheat.mini.utils.ApiResultUtils;
import com.ndz.wheat.mini.utils.AssertUtil;
import com.ndz.wheat.mini.vo.sys.SysUserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "登陆管理")
@Slf4j
@RestController
@RequestMapping("/admin/system/index")
public class IndexController {

    @Autowired
    IndexService indexService;
    @Autowired
    SysUserService sysUserService;

    @ApiOperation("登陆")
    @PostMapping("/login")
    public ApiResult<Map<String,Object>> login(@RequestBody LoginDTO dto) {
        return ApiResultUtils.ok(indexService.login(dto));
    }



    @ApiOperation("用户信息")
    @GetMapping("/info")
    public ApiResult<Map<String,Object>> info(HttpServletRequest request) {
        SysUserVO sysUserVO = UserSessionContext.get();
        log.info("ThreadLocal中取得用户信息, {}", JSON.toJSONString(sysUserVO));
        // 获取请求头的token字段
//        String token = request.getHeader("token");
//        if (token==null) throw new WheatException(BizCodeEnum.TOKEN_NULL);
//        // 从token字符串获取用户名称
//        String username = JwtHelper.getUsername(token);
        // 根据用户名称获取用户信息（基本信息、菜单权限、按钮权限数据）
        Map<String,Object> map = sysUserService.userInfo(sysUserVO.getUsername());
        return ApiResultUtils.ok(map);
    }

    @ApiOperation("登出")
    @PostMapping("/logout")
    public ApiResult<Map<String,Object>> logout(){
        return ApiResultUtils.ok();
    }






}
