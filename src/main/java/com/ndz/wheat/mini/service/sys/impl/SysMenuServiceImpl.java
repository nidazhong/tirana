package com.ndz.wheat.mini.service.sys.impl;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ndz.wheat.mini.common.enums.BizCodeEnum;
import com.ndz.wheat.mini.dao.sys.SysRoleMenuDao;
import com.ndz.wheat.mini.dto.sys.AssginMenuDTO;
import com.ndz.wheat.mini.entity.sys.SysRoleMenuEntity;
import com.ndz.wheat.mini.exception.WheatException;
import com.ndz.wheat.mini.vo.sys.SysMenuVO;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ndz.wheat.mini.common.helper.MenuHelper;
import com.ndz.wheat.mini.dao.sys.SysMenuDao;
import com.ndz.wheat.mini.dto.sys.SaveSysMenuDTO;
import com.ndz.wheat.mini.entity.sys.SysMenuEntity;
import com.ndz.wheat.mini.service.base.impl.BaseServiceImpl;
import com.ndz.wheat.mini.service.sys.SysMenuService;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;

import javax.annotation.Resource;

@Service
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenuDao, SysMenuEntity> implements SysMenuService {


    @Resource
    SysRoleMenuDao sysRoleMenuDao;

    @Override
    public List<SysMenuVO> findNodes() {
        //全部权限列表
        List<SysMenuEntity> sysMenuList = this.baseDao.selectList(null);
        if (CollUtil.isEmpty(sysMenuList)) return null;
        List<SysMenuVO> sysMenuVOS = BeanUtil.copyToList(sysMenuList, SysMenuVO.class);

        //构建树形数据
        List<SysMenuVO> result = MenuHelper.buildTree(sysMenuVOS);
        return result;
    }

    @Override
    public void save(SaveSysMenuDTO permission) {
        SysMenuEntity sysMenuEntity = BeanUtil.copyProperties(permission, SysMenuEntity.class);
        insert(sysMenuEntity);
    }

    @Override
    public void updateById(SaveSysMenuDTO permission) {
        SysMenuEntity sysMenuEntity = selectById(permission.getId());
        BeanUtil.copyProperties(permission, sysMenuEntity, "id");
        updateById(sysMenuEntity);
    }

    @Override
    public void removeById(Long id) {
        Long count = this.baseDao.selectCount(new LambdaQueryWrapper<SysMenuEntity>().eq(SysMenuEntity::getParentId, id));
        if (count < 0) {
            throw new WheatException(BizCodeEnum.NODE_ERROR);
        }
        deleteById(id);
    }

    @Override
    public List<SysMenuVO> findSysMenuByRoleId(Long roleId) {
        //获取所有status为1的菜单列表
        List<SysMenuEntity> menuList = this.baseDao.selectList(new QueryWrapper<SysMenuEntity>().eq("status", 1));
        List<SysMenuVO> sysMenuVOS = BeanUtil.copyToList(menuList, SysMenuVO.class);
        //根据角色id获取角色权限
        List<SysRoleMenuEntity> roleMenus = sysRoleMenuDao.selectList(new QueryWrapper<SysRoleMenuEntity>().eq("role_id",roleId));
        //获取该角色已分配的所有菜单id
        List<Long> roleMenuIds = new ArrayList<>();
        for (SysRoleMenuEntity roleMenu : roleMenus) {
            roleMenuIds.add(roleMenu.getMenuId());
        }
        //遍历所有菜单列表
        for (SysMenuVO sysMenu : sysMenuVOS) {
            if(roleMenuIds.contains(sysMenu.getId())){
                //设置该菜单已被分配
                sysMenu.setSelect(true);
            }else {
                sysMenu.setSelect(false);
            }
        }
        //将权限列表转换为权限树
        List<SysMenuVO> sysMenus = MenuHelper.buildTree(sysMenuVOS);
        return sysMenus;
    }

    @Override
    public void doAssign(AssginMenuDTO dto) {
        //删除已分配的权限
        sysRoleMenuDao.delete(new QueryWrapper<SysRoleMenuEntity>().eq("role_id",dto.getRoleId()));
        //遍历所有已选择的权限id
        for (Long menuId : dto.getMenuIdList()) {
            if(menuId != null){
                //创建SysRoleMenu对象
                SysRoleMenuEntity sysRoleMenu = new SysRoleMenuEntity();
                sysRoleMenu.setMenuId(menuId);
                sysRoleMenu.setRoleId(dto.getRoleId());
                //添加新权限
                sysRoleMenuDao.insert(sysRoleMenu);
            }
        }
    }
}
