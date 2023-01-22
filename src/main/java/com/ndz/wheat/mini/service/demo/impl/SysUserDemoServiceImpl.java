package com.ndz.wheat.mini.service.demo.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ndz.wheat.mini.dao.base.DeleteDTO;
import com.ndz.wheat.mini.dto.demo.SysUserDemoDTO;
import com.ndz.wheat.mini.service.base.impl.BaseServiceImpl;
import com.ndz.wheat.mini.vo.demo.SysUserDemoVO;
import org.springframework.stereotype.Service;

import com.ndz.wheat.mini.dao.demo.SysUserDemoDao;
import com.ndz.wheat.mini.entity.demo.SysUserDemoEntity;
import com.ndz.wheat.mini.service.demo.SysUserDemoService;

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
    public void del(DeleteDTO dto) {
        boolean bool = deleteBatchIds(dto.getIds());

    }
}
