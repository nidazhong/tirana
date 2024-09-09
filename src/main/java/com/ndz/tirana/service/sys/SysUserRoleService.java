package com.ndz.tirana.service.sys;

import com.ndz.tirana.vo.sys.SysRoleVO;
import com.ndz.tirana.common.page.PageData;
import com.ndz.tirana.dto.sys.AssginRoleDTO;
import com.ndz.tirana.dto.sys.SaveSysRoleDTO;

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
