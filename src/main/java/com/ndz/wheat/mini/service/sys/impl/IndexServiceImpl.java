package com.ndz.wheat.mini.service.sys.impl;

import com.ndz.wheat.mini.common.enums.BizCodeEnum;
import com.ndz.wheat.mini.common.helper.JwtHelper;
import com.ndz.wheat.mini.dto.sys.LoginDTO;
import com.ndz.wheat.mini.entity.sys.SysUserEntity;
import com.ndz.wheat.mini.exception.WheatException;
import com.ndz.wheat.mini.service.sys.IndexService;
import com.ndz.wheat.mini.service.sys.SysUserService;
import com.ndz.wheat.mini.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    SysUserService sysUserService;
    @Override
    public Map<String, Object> login(LoginDTO loginDTO) {
        // 根据用户名称查询数据库
        SysUserEntity sysUser = sysUserService.getByUsername(loginDTO.getUsername());
        // 如果查询为空
        if(null == sysUser) {
            throw new WheatException(BizCodeEnum.ACCOUNT_ERROR);
        }
        // 判断密码是否一样
        if(!MD5Utils.encrypt(loginDTO.getPassword()).equals(sysUser.getPassword())) {
            throw new WheatException(BizCodeEnum.PASSWORD_ERROR);
        }
        // 判断用户是否可用
        if(sysUser.getStatus() == 0) {
            throw new WheatException(BizCodeEnum.ACCOUNT_STOP);
        }
        // 根据userId和userName生成token字符串， 通过map返回
        Map<String, Object> map = new HashMap<>();
        map.put("token", JwtHelper.createToken(sysUser.getId(), sysUser.getUsername()));
        return map;
    }
}
