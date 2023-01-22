package com.ndz.wheat.mini.service.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ndz.wheat.mini.dto.sys.QuerySysUserDTO;
import com.ndz.wheat.mini.dto.sys.SysUserDTO;
import com.ndz.wheat.mini.vo.sys.SysUserVO;

public interface SysUserService {
    void save(SysUserDTO user);

    void updateById(SysUserDTO user);

    void removeById(Long id);


    SysUserVO getById(Long id);

    IPage<SysUserVO> page(Page<SysUserVO> pageParam, QuerySysUserDTO query);
}
