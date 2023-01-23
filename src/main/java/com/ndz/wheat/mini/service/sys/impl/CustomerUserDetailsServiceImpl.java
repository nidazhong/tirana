package com.ndz.wheat.mini.service.sys.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ndz.wheat.mini.config.security.CustomUser;
import com.ndz.wheat.mini.entity.sys.SysUserEntity;
import com.ndz.wheat.mini.service.sys.SysUserService;
import com.ndz.wheat.mini.service.sys.CustomerUserDetailsService;
import com.ndz.wheat.mini.vo.sys.SysUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * 返回封装Spring Security 用户对象
 */
@Component
public class CustomerUserDetailsServiceImpl implements CustomerUserDetailsService {

    @Autowired
    private SysUserService sysUserService;

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
        return new CustomUser(sysUserVO, Collections.emptyList());
    }
}
