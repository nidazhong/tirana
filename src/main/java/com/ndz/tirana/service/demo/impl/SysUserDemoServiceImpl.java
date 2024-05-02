package com.ndz.tirana.service.demo.impl;

import com.ndz.tirana.dao.demo.SysUserDemoDao;
import com.ndz.tirana.entity.demo.SysUserDemoEntity;
import com.ndz.tirana.vo.demo.SysUserDemoVO;
import org.springframework.stereotype.Service;

import com.ndz.tirana.dto.demo.SysUserDemoDTO;
import com.ndz.tirana.service.base.impl.BaseServiceImpl;
import com.ndz.tirana.service.demo.SysUserDemoService;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.ListUtil;

@Service
public class SysUserDemoServiceImpl extends BaseServiceImpl<SysUserDemoDao, SysUserDemoEntity> implements SysUserDemoService {


    @Override
    public void save(SysUserDemoDTO dto) {
        SysUserDemoEntity sysUserDemoEntity = BeanUtil.copyProperties(dto, SysUserDemoEntity.class);
        insert(sysUserDemoEntity);
    }

    @Override
    public SysUserDemoVO info(Long id) {
        SysUserDemoEntity entity = selectById(id);
        return BeanUtil.copyProperties(entity, SysUserDemoVO.class);
    }

    @Override
    public void del(Long[] ids) {
        boolean bool = deleteBatchIds(ListUtil.toList(ids));

    }
}
