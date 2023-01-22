package com.ndz.wheat.mini.service.sys;

import com.ndz.wheat.mini.common.page.PageData;
import com.ndz.wheat.mini.dto.sys.AssginRoleDTO;
import com.ndz.wheat.mini.dto.sys.SaveSysRoleDTO;
import com.ndz.wheat.mini.vo.sys.SysRoleVO;

import java.util.List;
import java.util.Map;

public interface SysUserRoleService {

    PageData<SysRoleVO> page(Map<String, Object> params);

    void save(SaveSysRoleDTO dto);

    void remove(String id);

    SysRoleVO info(String id);

    void update(SaveSysRoleDTO dto);

    void batchRemove(Long[] idList);

    Map<String, Object> getRolesByUserId(Long userId);

    void doAssign(AssginRoleDTO dto);
}
