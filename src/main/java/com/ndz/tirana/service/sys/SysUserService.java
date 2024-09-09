package com.ndz.tirana.service.sys;

import com.ndz.tirana.entity.sys.SysUserEntity;
import com.ndz.tirana.vo.sys.SysUserVO;
import com.ndz.tirana.common.page.PageData;
import com.ndz.tirana.dto.sys.QuerySysUserDTO;
import com.ndz.tirana.dto.sys.SysUserDTO;

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
