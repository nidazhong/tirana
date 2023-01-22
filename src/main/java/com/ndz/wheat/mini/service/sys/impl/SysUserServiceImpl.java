package com.ndz.wheat.mini.service.sys.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ndz.wheat.mini.dao.sys.SysUserDao;
import com.ndz.wheat.mini.dto.sys.QuerySysUserDTO;
import com.ndz.wheat.mini.dto.sys.SysUserDTO;
import com.ndz.wheat.mini.entity.sys.SysUserEntity;
import com.ndz.wheat.mini.service.base.impl.BaseServiceImpl;
import com.ndz.wheat.mini.service.sys.SysUserService;
import com.ndz.wheat.mini.vo.sys.SysUserVO;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {



    @Override
    public void save(SysUserDTO user) {
        SysUserEntity sysUserEntity = BeanUtil.copyProperties(user, SysUserEntity.class);
        insert(sysUserEntity);
    }

    @Override
    public void updateById(SysUserDTO user) {
        SysUserEntity sysUserEntity = selectById(user.getId());
        BeanUtil.copyProperties(user, sysUserEntity, "id");
        updateById(sysUserEntity);
    }

    @Override
    public void removeById(Long id) {
        deleteById(id);
    }

    @Override
    public SysUserVO getById(Long id) {
        SysUserEntity sysUserEntity = selectById(id);
        return BeanUtil.copyProperties(sysUserEntity, SysUserVO.class);
    }

    @Override
    public IPage<SysUserVO> page(Page<SysUserVO> pageParam, QuerySysUserDTO query) {
        return baseDao.page(pageParam, query);
    }
}
