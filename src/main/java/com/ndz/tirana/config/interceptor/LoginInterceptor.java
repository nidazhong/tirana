package com.ndz.tirana.config.interceptor;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.ndz.tirana.common.helper.JwtHelper;
import com.ndz.tirana.config.security.UserSessionContext;
import com.ndz.tirana.common.bean.ApiResult;
import com.ndz.tirana.common.enums.StateEnum;
import com.ndz.tirana.utils.ApiResultUtils;
import com.ndz.tirana.vo.sys.SysUserVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class LoginInterceptor  implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //在执行controller方法（handler之前进行执行

        /**
         * 需要判断请求的接口路径是否为handlermethod（controller方法
         * 判断token是否为空 如果为空 未登录
         * 如果toke不为空  登录验证 checktoken进行认证
         * 如果认证成功 进行放行
         */
        if (!(handler instanceof HandlerMethod)) {
            //handler可能是requestresourcehandler springboot程序 访问静态资源 默认classpath下的static目录

            return true;
        }
        String token= request.getHeader("token");


//        log.info("=================request start===========================");
//        String requestURI = request.getRequestURI();
//        log.info("request uri:{}",requestURI);
//        log.info("request method:{}",request.getMethod());
//        log.info("token:{}", token);
//        log.info("=================request end===========================");

        if(StringUtils.isBlank(token)){
            ApiResult<String> result = ApiResultUtils.error(StateEnum.NO_LOGIN,"未登录");
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }
//        SysUserVO sysUser = loginService.checkToken(token);
        JSONObject userJSON = JwtHelper.getUser(token);
        if(userJSON==null){
            ApiResult<String> result = ApiResultUtils.error(StateEnum.NO_LOGIN,"未登录");
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }
        SysUserVO sysUser = userJSON.to(SysUserVO.class);
        //登录验证成功，放行
        //controller中 直接获取用户的信息
        UserSessionContext.put(sysUser);
        return true;
    }

    /**
     * 避免内存泄露
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserSessionContext.remove();
    }
}
