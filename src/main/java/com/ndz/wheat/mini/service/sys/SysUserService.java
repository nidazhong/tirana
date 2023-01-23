package com.ndz.wheat.mini.service.sys;

import com.ndz.wheat.mini.common.page.PageData;
import com.ndz.wheat.mini.dto.sys.QuerySysUserDTO;
import com.ndz.wheat.mini.dto.sys.SysUserDTO;
import com.ndz.wheat.mini.entity.sys.SysUserEntity;
import com.ndz.wheat.mini.vo.sys.SysUserVO;

import java.util.Map;

public interface SysUserService {
    void save(SysUserDTO user);

    void updateById(SysUserDTO user);

    void removeById(Long id);


    SysUserVO getById(Long id);

    PageData<SysUserVO> page(Long page, Long limit, QuerySysUserDTO query);

    void updateStatus(Long id, Integer status);

    SysUserEntity getByUsername(String userName);

    Map<String, Object> userInfo(String username);

}
