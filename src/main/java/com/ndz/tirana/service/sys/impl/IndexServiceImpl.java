package com.ndz.tirana.service.sys.impl;

import java.util.HashMap;
import java.util.Map;

import com.ndz.tirana.common.helper.JwtHelper;
import com.ndz.tirana.entity.sys.SysUserEntity;
import com.ndz.tirana.service.sys.IndexService;
import com.ndz.tirana.service.sys.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndz.tirana.common.enums.BizCodeEnum;
import com.ndz.tirana.dto.sys.LoginDTO;
import com.ndz.tirana.exception.WheatException;
import com.ndz.tirana.utils.MD5Utils;

@Service
@Slf4j
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
        String token = JwtHelper.createToken(sysUser.getId(), sysUser.getUsername());
        map.put("token", token);
        log.info("IndexServiceImpl 生成token："+ token);
        return map;
    }

}
