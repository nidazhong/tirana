package com.ndz.tirana.config.security;

import cn.hutool.core.bean.BeanUtil;
import com.ndz.tirana.dao.sys.SysUserDao;
import com.ndz.tirana.entity.sys.SysUserEntity;
import com.ndz.tirana.service.sys.SysMenuService;
import com.ndz.tirana.service.sys.SysUserService;
import com.ndz.tirana.vo.sys.SysUserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 将系统用户和菜单权限组装
 * 封装成Spring Security用户对象
 *
 */
@Slf4j
@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private SysUserService userService;
    @Autowired
    SysMenuService sysMenuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 从数据库中获取系统用户
        SysUserEntity sysUser = userService.getByUsername(username);
        if(null == sysUser) {
            throw new UsernameNotFoundException("用户名不存在！");
        }
        if(sysUser.getStatus() == 0) {
            throw new RuntimeException("账号已停用");
        }
        log.info("[{}]登录认证查询 UserDetailsService.loadUserByUsername()",username);
    SysUserVO sysUserVO = BeanUtil.copyProperties(sysUser, SysUserVO.class);

    // 根据用户ID查询操作的数据权限
    List<String> userPermsList = sysMenuService.findUserPermsList(sysUser.getId());
    // 用户权限数据（菜单数据）
    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (String perm : userPermsList) {
        authorities.add(new SimpleGrantedAuthority(perm.trim()));
    }
        // 封装成Spring Security用户对象
        return new MyUserDetails(sysUserVO, authorities);
    }
}
