package com.ndz.tirana.service.sys;

import com.ndz.tirana.entity.sys.SysOperLogEntity;
import com.ndz.tirana.vo.sys.SysOperLogVO;
import com.ndz.tirana.common.page.PageData;
import com.ndz.tirana.dto.sys.SysOperLogQueryDTO;

public interface AsyncOperLogService {
    void saveSysLog(SysOperLogEntity operLog);

    PageData<SysOperLogVO> page(Long page, Long limit, SysOperLogQueryDTO query);

    SysOperLogVO getById(Long id);
}
