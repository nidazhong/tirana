package com.ndz.tirana.service.demo;

import com.ndz.tirana.vo.demo.SysUserDemoVO;
import com.ndz.tirana.dto.demo.SysUserDemoDTO;

public interface SysUserDemoService {
    void save(SysUserDemoDTO dto);

    SysUserDemoVO info(Long id);

    void del(Long[] ids);
}
