package com.ndz.wheat.mini.service.sys;

import com.ndz.wheat.mini.dto.sys.AssginMenuDTO;
import com.ndz.wheat.mini.dto.sys.SaveSysMenuDTO;
import com.ndz.wheat.mini.entity.sys.SysMenuEntity;
import com.ndz.wheat.mini.vo.sys.RouterVO;
import com.ndz.wheat.mini.vo.sys.SysMenuVO;

import java.util.List;

public interface SysMenuService {
    List<SysMenuVO> findNodes();

    void save(SaveSysMenuDTO permission);

    void updateById(SaveSysMenuDTO permission);

    void removeById(Long id);


    List<SysMenuVO> findSysMenuByRoleId(Long roleId);

    void doAssign(AssginMenuDTO dto);

    List<RouterVO> findUserMenuList(Long userId);

    /**
     * 根据用户Id获取用户按钮的操作权限
     *
     * @param userId 用户id
     * @return {@link List}<{@link String}>
     */
    List<String> findUserPermsList(Long userId);



}
