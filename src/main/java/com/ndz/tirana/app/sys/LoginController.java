package com.ndz.tirana.app.sys;

import com.alibaba.fastjson2.JSON;
import com.ndz.tirana.config.security.UserSessionContext;
import com.ndz.tirana.vo.sys.SysUserVO;
import com.ndz.tirana.common.bean.ApiResult;
import com.ndz.tirana.dto.sys.LoginDTO;
import com.ndz.tirana.service.sys.IndexService;
import com.ndz.tirana.service.sys.SysUserService;
import com.ndz.tirana.utils.ApiResultUtils;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

//@Api(tags = "登陆管理")
@Slf4j
@RestController
@RequestMapping("/admin/system/index")
public class LoginController {

    @Autowired
    IndexService indexService;
    @Autowired
    SysUserService sysUserService;

    ////@ApiOperation("登陆")
    @PostMapping("/login")
    public ApiResult<Map<String,Object>> login(@RequestBody LoginDTO dto) {
        // 会经过token认证，TokenLoginFilter类处理
        return ApiResultUtils.ok(indexService.login(dto));
    }



    ////@ApiOperation("用户信息")
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

    ////@ApiOperation("登出")
    @PostMapping("/logout")
    public ApiResult<Map<String,Object>> logout(){
        return ApiResultUtils.ok();
    }






}
