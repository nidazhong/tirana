package com.ndz.tirana.config.fillter;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.ndz.tirana.common.constant.TiranaConstant;
import com.ndz.tirana.common.helper.JwtHelper;
import com.ndz.tirana.common.bean.ApiResult;
import com.ndz.tirana.common.enums.StateEnum;
import com.ndz.tirana.utils.ApiResultUtils;
import com.ndz.tirana.utils.AssertUtil;
import com.ndz.tirana.utils.HttpContextUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 每次请求去认证解析token过滤器
 * </p>
 */
@Log4j2
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private RedisTemplate<String, String> redisTemplate;
    public TokenAuthenticationFilter(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

//    public TokenAuthenticationFilter() {}

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        logger.info("[接口鉴权]请求uri:"+request.getRequestURI());
        // 如果是登录接口，直接放行
        if("/admin/system/index/login".equals(request.getRequestURI())) {
            // 放行
            chain.doFilter(request, response);
            return;
        }
        // 其他接口较都要验证有token，解析token中的用户信息和Redis中存储的token进行比对
        UsernamePasswordAuthenticationToken authentication = this.getAuthentication(request);
        if(null != authentication) {
            // 如果认证成功，则将认认证信息(用户信息)放到SecurityContextHolder上下文
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // 通过，转给其他过滤器
            chain.doFilter(request, response);
        } else {
            // 如果没有token, 则返回无权限
            ApiResultUtils.out(response, new ApiResult<>(StateEnum.NO_PERMISSION));
        }
    }

    /**
     * 获取身份认证
     * @param request
     * @return
     */
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        // token置于header里，每次从请求头中获取token和redis中的比对
//        String token = request.getHeader("token");
        String token = HttpContextUtils.getToken(request);
        if (StrUtil.isNotEmpty(token)) {
            String useruame = JwtHelper.getUsername(token);
            AssertUtil.notNull(useruame, "token解析username失败");
            log.info("[{}]接口请求token验证通过,token: {}",useruame,token);
            if (StrUtil.isNotEmpty(useruame)) {
                // Redis 取出用户数据 (when it finished login-filter will put on the Redis)
                String authoritiesString = redisTemplate.opsForValue().get(TiranaConstant.REDIS_PREFIX+useruame);
                List<Map<String, String>> mapList = JSON.parseArray(authoritiesString, (Type) Map.class);
                List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                for (Map<String, String> map : mapList) {
                    authorities.add(new SimpleGrantedAuthority(map.get("authority")));
                }
                //  UsernamePasswordAuthenticationToken
                return new UsernamePasswordAuthenticationToken(useruame, null, authorities);
            }
        }
        // TODO 过期判断 | Redis 自动过期删除key
        return null;
    }
}
