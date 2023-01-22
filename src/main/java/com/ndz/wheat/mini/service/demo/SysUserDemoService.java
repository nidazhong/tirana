package com.ndz.wheat.mini.service.demo;

import com.ndz.wheat.mini.dto.demo.SysUserDemoDTO;
import com.ndz.wheat.mini.vo.demo.SysUserDemoVO;

public interface SysUserDemoService {
    void save(SysUserDemoDTO dto);

    SysUserDemoVO info(Long id);

    void del(Long[] ids);
}
