package com.ndz.wheatmall.service.sys;

import com.ndz.wheatmall.common.page.PageData;
import com.ndz.wheatmall.vo.sys.SysRoleVO;

import java.util.Map;

public interface SysUserRoleService {

    PageData<SysRoleVO> page(Map<String, Object> params);
}
