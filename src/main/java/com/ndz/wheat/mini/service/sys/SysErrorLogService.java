package com.ndz.wheat.mini.service.sys;

import com.ndz.wheat.mini.common.page.PageData;
import com.ndz.wheat.mini.entity.base.SysErrorLogEntity;
import com.ndz.wheat.mini.vo.sys.LogErrorVO;

import java.util.Map;

public interface SysErrorLogService {
    void save(SysErrorLogEntity entity);

    PageData<LogErrorVO> page(Map<String, Object> params);

    LogErrorVO getById(Long id);

}
