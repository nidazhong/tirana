package com.ndz.tirana.service.sys.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ndz.tirana.config.security.CustomUser;
import com.ndz.tirana.entity.sys.SysUserEntity;
import com.ndz.tirana.service.sys.CustomerUserDetailsService;
import com.ndz.tirana.service.sys.SysMenuService;
import com.ndz.tirana.service.sys.SysUserService;
import com.ndz.tirana.vo.sys.SysUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 返回封装Spring Security 用户对象
 */
@Component
public class CustomerUserDetailsServiceImpl implements CustomerUserDetailsService {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    SysMenuService sysMenuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUserEntity sysUser = sysUserService.getByUsername(username);
        if(null == sysUser) {
            throw new UsernameNotFoundException("用户名不存在！");
        }
        if(sysUser.getStatus() == 0) {
            throw new RuntimeException("账号已停用");
        }
        SysUserVO sysUserVO = BeanUtil.copyProperties(sysUser, SysUserVO.class);

        // 根据用户ID查询操作的数据权限
        List<String> userPermsList = sysMenuService.findUserPermsList(sysUser.getId());
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (String perm : userPermsList) {
            authorities.add(new SimpleGrantedAuthority(perm.trim()));
        }
        return new CustomUser(sysUserVO, authorities);
    }
}
