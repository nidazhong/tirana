package com.ndz.tirana.service.sys.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ndz.tirana.dao.sys.SysUserDao;
import com.ndz.tirana.entity.sys.SysRoleEntity;
import com.ndz.tirana.entity.sys.SysUserEntity;
import com.ndz.tirana.service.sys.SysMenuService;
import com.ndz.tirana.service.sys.SysUserRoleService;
import com.ndz.tirana.service.sys.SysUserService;
import com.ndz.tirana.vo.sys.RouterVO;
import com.ndz.tirana.vo.sys.SysUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ndz.tirana.common.page.PageData;
import com.ndz.tirana.dto.sys.QuerySysUserDTO;
import com.ndz.tirana.dto.sys.SysUserDTO;
import com.ndz.tirana.service.base.impl.BaseServiceImpl;
import com.ndz.tirana.utils.AssertUtil;
import com.ndz.tirana.utils.MD5Utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.map.MapUtil;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {

    @Autowired
    SysMenuService sysMenuService;
    @Autowired
    SysUserRoleService sysUserRoleService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(SysUserDTO user) {
        SysUserEntity sysUserEntity = BeanUtil.copyProperties(user, SysUserEntity.class);
        String encryptPwd = MD5Utils.encrypt(user.getPassword());
        sysUserEntity.setPassword(encryptPwd);
        insert(sysUserEntity);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateById(SysUserDTO user) {
        SysUserEntity sysUserEntity = selectById(user.getId());
        BeanUtil.copyProperties(user, sysUserEntity, "id");
        updateById(sysUserEntity);
    }

    @Transactional(rollbackFor = Exception.class)
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
    public PageData<SysUserVO>  page(Long page, Long limit, QuerySysUserDTO query) {
        Page<SysUserVO> pageParam = new Page<>(page, limit);
        IPage<SysUserVO> iPage = baseDao.page(pageParam, query);
        return  getPageData(iPage, SysUserVO.class);
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        SysUserEntity sysUser = selectById(id);
        sysUser.setStatus(status);
        updateById(sysUser);
    }

    @Override
    public SysUserEntity getByUsername(String userName) {
        AssertUtil.notNull(userName, "账号名称不能为空");
        return this.baseDao.selectOne(new LambdaQueryWrapper<SysUserEntity>().eq(SysUserEntity::getUsername,userName));
    }

    /**
     * 根据用户名称获取用户信息（基本信息、菜单权限、按钮权限数据）
     * @param username
     * @return
     */
    @Override
    public Map<String, Object> userInfo(String username) {
        Map<String, Object> result = new HashMap<>();
        SysUserEntity sysUser = this.getByUsername(username);

        // 获取用户角色数据
        Map<String, Object> roles = sysUserRoleService.getRolesByUserId(sysUser.getId());
        List<SysRoleEntity> uoleList = MapUtil.get(roles, "allRoles", new TypeReference<>() {});
        List<String> roleCodes = null;
        if (CollUtil.isNotEmpty(uoleList)) {
             roleCodes = uoleList.stream().map(SysRoleEntity::getRoleCode).collect(Collectors.toList());
        }

        //根据用户id获取菜单权限值
        List<RouterVO> routerVOList = sysMenuService.findUserMenuList(sysUser.getId());
        //根据用户id获取用户按钮权限
        List<String> permsList = sysMenuService.findUserPermsList(sysUser.getId());

        result.put("name", sysUser.getName());
        result.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        //当前权限控制使用不到，我们暂时忽略
//        result.put("roles",  "['admin']");
        result.put("roles",  roleCodes);
        result.put("buttons", permsList);
        result.put("routers", routerVOList);
        return result;
    }
}
