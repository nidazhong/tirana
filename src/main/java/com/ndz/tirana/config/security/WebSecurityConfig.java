package com.ndz.tirana.config.security;

import com.ndz.tirana.common.constant.TiranaConstant;
import com.ndz.tirana.config.fillter.TokenAuthenticationFilter;
import com.ndz.tirana.config.fillter.TokenLoginFilter;
import com.ndz.tirana.service.sys.AsyncLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ndz.tirana.service.sys.CustomerUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//开启安全认证注解功能，默认禁用注解
public class WebSecurityConfig {

    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;

    @Autowired
    private CustomMd5PasswordEncoder customMd5PasswordEncoder;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private AsyncLoginLogService asyncLoginLogService;


    @Bean
    public AuthenticationProvider userDetailsAuthProvider(){
        DaoAuthenticationProvider a = new DaoAuthenticationProvider();
        a.setUserDetailsService(customerUserDetailsService);
        a.setPasswordEncoder(customMd5PasswordEncoder);
        return a;
    }

//    /**
//     * 注册自定义拦截器
//     *
//     * @param registry
//     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/user/wx/login");//开放登录路径
//    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(userDetailsAuthProvider());
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // 这是配置的关键，决定哪些接口开启防护，哪些接口绕过防护
        httpSecurity
                //关闭csrf
                .csrf().disable()
                // 开启跨域以便前端调用接口
                .cors().and()
                .authorizeRequests()
                // 指定某些接口不需要通过验证即可访问。登陆接口肯定是不需要认证的
                .antMatchers("/admin/system/index/login").permitAll()
                // 这里意思是其它所有接口需要认证才能访问
                .anyRequest().authenticated()
                .and()
                //TokenAuthenticationFilter放到UsernamePasswordAuthenticationFilter的前面，这样做就是为了除了登录的时候去查询数据库外，其他时候都用token进行认证。
                .addFilterBefore(new TokenAuthenticationFilter(redisTemplate), UsernamePasswordAuthenticationFilter.class)
                .addFilter(new TokenLoginFilter(authenticationManager(), redisTemplate, asyncLoginLogService));

        //禁用session
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return httpSecurity.build();
    }

    /**
     * 配置哪些请求不拦截
     * 排除swagger相关请求, 静态资源
     */
    @Bean
    @Order(0)
    SecurityFilterChain resources(HttpSecurity http) throws Exception {
        http
                .requestMatchers((matchers) -> matchers.antMatchers(
                        "/static/**","/templates/**",
                        "/favicon.ico")
                        .antMatchers(TiranaConstant.SWAGGER_PATH))
                .authorizeHttpRequests((authorize) -> authorize.anyRequest().permitAll())
                .requestCache().disable()
                .securityContext().disable()
                .sessionManagement().disable();
        return http.build();
    }
}
