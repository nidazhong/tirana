package com.ndz.wheat.mini.service.demo;

import com.ndz.wheat.mini.dao.base.DeleteDTO;
import com.ndz.wheat.mini.dto.demo.SysUserDemoDTO;
import com.ndz.wheat.mini.vo.demo.SysUserDemoVO;
import org.apache.ibatis.annotations.Param;

public interface SysUserDemoService {
    void save(SysUserDemoDTO dto);

    SysUserDemoVO info(Long id);

    void del(@Param("dto") DeleteDTO dto);
}
