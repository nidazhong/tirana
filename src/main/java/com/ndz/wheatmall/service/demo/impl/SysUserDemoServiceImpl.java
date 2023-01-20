package com.ndz.wheatmall.service.demo.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ndz.wheatmall.dao.base.DeleteDTO;
import com.ndz.wheatmall.vo.demo.SysUserDemoVO;
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

    @Override
    public SysUserDemoVO info(Long id) {
        SysUserDemoEntity entity = selectById(id);
        SysUserDemoVO sysUserDemoVO = BeanUtil.copyProperties(entity, SysUserDemoVO.class);
        return sysUserDemoVO;
    }

    @Override
    public void del(DeleteDTO dto) {
        boolean bool = deleteBatchIds(dto.getIds());

    }
}
