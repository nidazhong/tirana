package com.ndz.tirana.config.security;

import com.ndz.tirana.vo.sys.SysUserVO;

/**
 * 拦截器方式进行用户验证
 * 使用ThreadLocal去存储当前线程的用户信息，给Controller等去使用
 */
public class UserSessionContext {

    //线程变量隔离
    private static final ThreadLocal<SysUserVO> LOCAL = new ThreadLocal<>();

    public static void put(SysUserVO sysUser){
        LOCAL.set(sysUser);
    }

    public static SysUserVO get(){
        return LOCAL.get();
    }

    public static void remove(){
        LOCAL.remove();
    }
}
