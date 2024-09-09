package com.ndz.tirana.config.fillter;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.ndz.tirana.common.constant.WheatConstant;
import com.ndz.tirana.common.helper.JwtHelper;
import com.ndz.tirana.common.bean.ApiResult;
import com.ndz.tirana.common.enums.StateEnum;
import com.ndz.tirana.utils.ApiResultUtils;
import com.ndz.tirana.utils.AssertUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
 * 认证解析token过滤器
 * </p>
 */
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private RedisTemplate<String, String> redisTemplate;
    public TokenAuthenticationFilter(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

//    public TokenAuthenticationFilter() {}

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        logger.info("uri:"+request.getRequestURI());
        //如果是登录接口，直接放行
        if("/admin/system/index/login".equals(request.getRequestURI())) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        if(null != authentication) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);
        } else {
            ApiResultUtils.out(response, new ApiResult<>(StateEnum.NO_PERMISSION));
        }
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        // token置于header里
        String token = request.getHeader("token");
        logger.info("token:"+token);
        if (StrUtil.isNotEmpty(token)) {
            String useruame = JwtHelper.getUsername(token);
            AssertUtil.notNull(useruame, "token解析username失败");
            logger.info("useruame:"+useruame);
            if (StrUtil.isNotEmpty(useruame)) {
                // Redis 取出用户数据 (when it finished login-filter will put on the Redis)
                String authoritiesString = redisTemplate.opsForValue().get(WheatConstant.REDIS_PREFIX+useruame);
                List<Map<String, String>> mapList = JSON.parseArray(authoritiesString, (Type) Map.class);
                List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                for (Map<String, String> map : mapList) {
                    authorities.add(new SimpleGrantedAuthority(map.get("authority")));
                }

                return new UsernamePasswordAuthenticationToken(useruame, null, authorities);
            }
        }
        return null;
    }
}
