package com.ndz.tirana.service.sys;

import com.ndz.tirana.entity.base.SysErrorLogEntity;
import com.ndz.tirana.vo.sys.LogErrorVO;
import com.ndz.tirana.common.page.PageData;

import java.util.Map;

public interface SysErrorLogService {
    void save(SysErrorLogEntity entity);

    PageData<LogErrorVO> page(Map<String, Object> params);

    LogErrorVO getById(Long id);

}
