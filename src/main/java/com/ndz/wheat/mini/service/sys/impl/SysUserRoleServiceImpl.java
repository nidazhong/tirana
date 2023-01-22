package com.ndz.wheat.mini.service.sys.impl;

import java.util.List;
import java.util.Map;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.extra.cglib.CglibUtil;
import com.ndz.wheat.mini.dto.sys.SaveSysRoleDTO;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ndz.wheat.mini.common.constant.MybatisConstant;
import com.ndz.wheat.mini.common.page.PageData;
import com.ndz.wheat.mini.dao.sys.SysUserRoleDao;
import com.ndz.wheat.mini.entity.sys.SysRoleEntity;
import com.ndz.wheat.mini.service.base.impl.BaseServiceImpl;
import com.ndz.wheat.mini.service.sys.SysUserRoleService;
import com.ndz.wheat.mini.vo.sys.SysRoleVO;

import cn.hutool.core.util.StrUtil;

@Service
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRoleDao, SysRoleEntity> implements SysUserRoleService {

    @Override
    public PageData<SysRoleVO> page(Map<String, Object> params) {
        LambdaQueryWrapper<SysRoleEntity> searchQuery = new LambdaQueryWrapper<>();
        if (StrUtil.isNotEmpty((String)params.get("roleName"))) {
            searchQuery.like(SysRoleEntity::getRoleName, params.get("roleName"));
        }
        IPage<SysRoleEntity> page = baseDao.selectPage(
                getPage(params, MybatisConstant.CREATE_TIME,false), searchQuery
        );
        return getPageData(page, SysRoleVO.class);
    }

    @Override
    public void save( SaveSysRoleDTO dto) {
        SysRoleEntity entity =  BeanUtil.copyProperties(dto, SysRoleEntity.class);
        insert(entity);
    }

    @Override
    public void remove(String id) {
        deleteById(id);
    }

    @Override
    public SysRoleVO info(String id) {
        SysRoleEntity entity = selectById(id);
        return BeanUtil.copyProperties(entity, SysRoleVO.class);
    }


    @Override
    public void update(SaveSysRoleDTO dto) {
        SysRoleEntity entity = selectById(dto.getId());
        BeanUtil.copyProperties(dto, entity, "id");
        updateById(entity);
    }

    @Override
    public void batchRemove(Long[] idList) {
        deleteBatchIds(ListUtil.toList(idList));
    }
}
