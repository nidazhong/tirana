package com.ndz.tirana.config.fillter;

import com.alibaba.fastjson2.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ndz.tirana.common.constant.TiranaConstant;
import com.ndz.tirana.common.helper.JwtHelper;
import com.ndz.tirana.common.bean.ApiResult;
import com.ndz.tirana.common.enums.BizCodeEnum;
import com.ndz.tirana.common.enums.StateEnum;
import com.ndz.tirana.config.security.MyUserDetails;
import com.ndz.tirana.service.sys.AsyncLoginLogService;
import com.ndz.tirana.utils.ApiResultUtils;
import com.ndz.tirana.utils.IpUtils;
import com.ndz.tirana.vo.sys.LoginVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * <p>
 * 登录过滤器，继承UsernamePasswordAuthenticationFilter
 * 1、负责登录认证
 * 2、生成一个包含账号密码的认证信息
 * </p>
 *
 */
@Log4j2
public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    private RedisTemplate<String, String> redisTemplate;
    private AsyncLoginLogService asyncLoginLogService;

    // 构造器
    public MyUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager, RedisTemplate<String, String> redisTemplate,
                                                  AsyncLoginLogService asyncLoginLogService) {
        this.setAuthenticationManager(authenticationManager);
        this.setPostOnly(false);
        // 指定登录接口及提交方式，可以指定任意路径
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/admin/system/index/login","POST"));
        this.redisTemplate = redisTemplate;
        this.asyncLoginLogService = asyncLoginLogService;
    }


    /**
     * 尝试认证
     * @param req req
     * @param res res
     * @return {@link Authentication}
     * @throws AuthenticationException 身份验证异常
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException {
        try {
            LoginVO loginVo = new ObjectMapper().readValue(req.getInputStream(), LoginVO.class);
            Authentication authenticationToken = new UsernamePasswordAuthenticationToken(loginVo.getUsername(), loginVo.getPassword());
            // 调用AuthenticationManager进行身份验证
            // 校验这个认证信息，返回一个已认证的Authentication
            // AuthenticationManager就会根据他管理的provider的supports方法来判断是否支持该UsernamePasswordAuthenticationToken
            // MyUserDetailsService.loadUserByUsername(name) -- 委托认证

            log.info("[{}]登录委托UserDetailsService认证, AuthenticationManager.authenticate()",loginVo.getUsername());
            return this.getAuthenticationManager().authenticate(authenticationToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    /**
     * 认证成功
     *
     * @param request  请求
     * @param response 响应
     * @param chain    链
     * @param auth     身份验证
     * @throws IOException      ioexception
     * @throws ServletException servlet异常
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        MyUserDetails customUser = (MyUserDetails) auth.getPrincipal();
        String token = JwtHelper.createToken(customUser.getSysUser().getId(), customUser.getSysUser().getUsername());

        // Redis保存权限数据 -->
        redisTemplate.opsForValue().set(TiranaConstant.REDIS_PREFIX + customUser.getUsername(), JSON.toJSONString(customUser.getAuthorities()));
        // 记录登陆成功日志
        asyncLoginLogService.recordLoginLog(customUser.getUsername(), 0, IpUtils.getIpAddr(request), "登录成功");

        //
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        log.info("[{}]登录认证成功，返回token:{}", customUser.getUsername(),token);
        ApiResultUtils.out(response, new ApiResult<>(StateEnum.SUCCESS, map));
    }

    /**
     * 登录失败 | 认证失败
     *
     * @param request  请求
     * @param response 响应
     * @param e        e
     * @throws IOException      ioexception
     * @throws ServletException servlet异常
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException e) throws IOException, ServletException {
        if(e.getCause() instanceof RuntimeException) {
            ApiResultUtils.out(response, new ApiResult<>(204, e.getMessage()));
        } else {
            ApiResultUtils.out(response, new ApiResult<>(BizCodeEnum.LOGIN_MOBLE_ERROR));
        }
    }
}
