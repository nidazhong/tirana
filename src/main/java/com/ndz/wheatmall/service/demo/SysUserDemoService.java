package com.ndz.wheatmall.service.demo;

import com.ndz.wheatmall.dao.base.DeleteDTO;
import com.ndz.wheatmall.dto.demo.SysUserDemoDTO;
import com.ndz.wheatmall.vo.demo.SysUserDemoVO;
import org.apache.ibatis.annotations.Param;

public interface SysUserDemoService {
    void save(SysUserDemoDTO dto);

    SysUserDemoVO info(Long id);

    void del(@Param("dto") DeleteDTO dto);
}
