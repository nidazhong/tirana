package com.ndz.wheatmall.service.base.impl;


import com.ndz.wheatmall.dao.base.LogErrorDao;
import com.ndz.wheatmall.entity.base.LogErrorEntity;
import com.ndz.wheatmall.service.base.LogErrorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LogErrorServiceImpl extends BaseServiceImpl<LogErrorDao, LogErrorEntity> implements LogErrorService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(LogErrorEntity entity) {
        insert(entity);
    }
}
