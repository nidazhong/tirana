package com.ndz.wheat.mini.service.sys;

import com.ndz.wheat.mini.dto.sys.AssginMenuDTO;
import com.ndz.wheat.mini.dto.sys.SaveSysMenuDTO;
import com.ndz.wheat.mini.entity.sys.SysMenuEntity;
import com.ndz.wheat.mini.vo.sys.SysMenuVO;

import java.util.List;

public interface SysMenuService {
    List<SysMenuVO> findNodes();

    void save(SaveSysMenuDTO permission);

    void updateById(SaveSysMenuDTO permission);

    void removeById(Long id);


    List<SysMenuVO> findSysMenuByRoleId(Long roleId);

    void doAssign(AssginMenuDTO dto);
}
