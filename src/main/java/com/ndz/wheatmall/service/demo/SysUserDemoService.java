package com.ndz.wheatmall.service.demo;

import com.ndz.wheatmall.dto.demo.SysUserDemoDTO;
import com.ndz.wheatmall.vo.demo.SysUserDemoVO;

public interface SysUserDemoService {
    void save(SysUserDemoDTO dto);

    SysUserDemoVO info(Long id);
}
