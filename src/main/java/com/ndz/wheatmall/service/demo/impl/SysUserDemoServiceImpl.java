package com.ndz.wheatmall.service.demo.impl;

import cn.hutool.core.bean.BeanUtil;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

import com.ndz.wheatmall.dao.demo.SysUserDemoDao;
import com.ndz.wheatmall.dto.demo.SysUserDemoDTO;
import com.ndz.wheatmall.entity.demo.SysUserDemoEntity;
import com.ndz.wheatmall.service.base.impl.BaseServiceImpl;
import com.ndz.wheatmall.service.demo.SysUserDemoService;

@Service
public class SysUserDemoServiceImpl extends BaseServiceImpl<SysUserDemoDao, SysUserDemoEntity>  implements SysUserDemoService {


    @Override
    public void save(SysUserDemoDTO dto) {
        SysUserDemoEntity sysUserDemoEntity = BeanUtil.copyProperties(dto, SysUserDemoEntity.class);
        insert(sysUserDemoEntity);
    }
}
