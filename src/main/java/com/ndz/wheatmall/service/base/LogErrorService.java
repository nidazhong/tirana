package com.ndz.wheatmall.service.base;

import com.ndz.wheatmall.common.page.PageData;
import com.ndz.wheatmall.entity.base.LogErrorEntity;
import com.ndz.wheatmall.vo.base.LogErrorVO;

import java.util.List;
import java.util.Map;

public interface LogErrorService {
    void save(LogErrorEntity entity);

    PageData<LogErrorVO> page(Map<String, Object> params);
}
