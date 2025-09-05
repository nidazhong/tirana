package com.ndz.tirana.config.security;

import com.ndz.tirana.vo.sys.SysUserVO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * 自定义用户信息类
 */
public class MyUserDetails extends User {
    /**
     * 我们自己的用户实体对象，要调取用户信息时直接获取这个实体对象
     */
    private SysUserVO sysUser;

    public MyUserDetails(SysUserVO sysUser, Collection<? extends GrantedAuthority> authorities) {
        super(sysUser.getUsername(), sysUser.getPassword(), authorities);
        this.sysUser = sysUser;
    }

    public SysUserVO getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUserVO sysUser) {
        this.sysUser = sysUser;
    }

}
