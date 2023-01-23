package com.ndz.wheat.mini.service.sys;

import com.ndz.wheat.mini.common.page.PageData;
import com.ndz.wheat.mini.dto.sys.SysOperLogQueryDTO;
import com.ndz.wheat.mini.entity.sys.SysOperLogEntity;
import com.ndz.wheat.mini.vo.sys.SysOperLogVO;
import org.apache.ibatis.annotations.Param;

public interface AsyncOperLogService {
    void saveSysLog(SysOperLogEntity operLog);

    PageData<SysOperLogVO> page(Long page, Long limit, SysOperLogQueryDTO query);

    SysOperLogVO getById(Long id);
}
