package com.ndz.wheat.mini.service.sys;

import com.ndz.wheat.mini.common.page.PageData;
import com.ndz.wheat.mini.entity.base.LogErrorEntity;
import com.ndz.wheat.mini.vo.sys.LogErrorVO;

import java.util.Map;

public interface LogErrorService {
    void save(LogErrorEntity entity);

    PageData<LogErrorVO> page(Map<String, Object> params);
}
