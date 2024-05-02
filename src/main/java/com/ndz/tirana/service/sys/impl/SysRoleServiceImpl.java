package com.ndz.tirana.service.sys.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.ListUtil;
import com.ndz.tirana.common.constant.MybatisConstant;
import com.ndz.tirana.dao.sys.SysRoleDao;
import com.ndz.tirana.dao.sys.SysUserRoleDao;
import com.ndz.tirana.entity.sys.SysRoleEntity;
import com.ndz.tirana.entity.sys.SysUserRoleEntity;
import com.ndz.tirana.vo.sys.SysRoleVO;
import com.ndz.tirana.dto.sys.AssginRoleDTO;
import com.ndz.tirana.dto.sys.SaveSysRoleDTO;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ndz.tirana.common.page.PageData;
import com.ndz.tirana.service.base.impl.BaseServiceImpl;
import com.ndz.tirana.service.sys.SysUserRoleService;

import cn.hutool.core.util.StrUtil;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleDao, SysRoleEntity> implements SysUserRoleService {

    @Resource
    SysUserRoleDao userRoleDao;

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

    @Transactional(rollbackFor = Exception.class)
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


    @Transactional(rollbackFor = Exception.class)
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

    @Override
    public Map<String, Object> getRolesByUserId(Long userId) {
        //获取所有角色
        List<SysRoleEntity> roles = this.baseDao.selectList(null);
        //根据用户id查询
        LambdaQueryWrapper<SysUserRoleEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUserRoleEntity::getUserId,userId);
        //获取用户已分配的角色
        List<SysUserRoleEntity> userRoles = userRoleDao.selectList(queryWrapper);
        //获取用户已分配的角色id
        List<Long> userRoleIds = new ArrayList<>();
        for (SysUserRoleEntity userRole : userRoles) {
            userRoleIds.add(userRole.getRoleId());
        }
        //创建返回的Map
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("allRoles",roles);
        returnMap.put("userRoleIds",userRoleIds);
        return returnMap;
    }

    @Override
    public void doAssign(AssginRoleDTO dto) {
        //根据用户id删除原来分配的角色
        LambdaQueryWrapper<SysUserRoleEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUserRoleEntity::getUserId, dto.getUserId());
        userRoleDao.delete(queryWrapper);
        //获取所有的角色id
        List<Long> roleIdList = dto.getRoleIdList();
        for (Long roleId : roleIdList) {
            if(roleId != null){
                SysUserRoleEntity sysUserRole = new SysUserRoleEntity();
                sysUserRole.setUserId(dto.getUserId());
                sysUserRole.setRoleId(roleId);
                //保存
                userRoleDao.insert(sysUserRole);
            }
        }
    }
}
