package com.ndz.wheat.mini.service.sys;

import com.ndz.wheat.mini.common.page.PageData;
import com.ndz.wheat.mini.vo.sys.SysRoleVO;

import java.util.Map;

public interface SysUserRoleService {

    PageData<SysRoleVO> page(Map<String, Object> params);
}
