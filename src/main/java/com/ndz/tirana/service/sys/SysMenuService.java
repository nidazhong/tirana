package com.ndz.tirana.service.sys;

import com.ndz.tirana.vo.sys.RouterVO;
import com.ndz.tirana.vo.sys.SysMenuVO;
import com.ndz.tirana.dto.sys.AssginMenuDTO;
import com.ndz.tirana.dto.sys.SaveSysMenuDTO;

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
