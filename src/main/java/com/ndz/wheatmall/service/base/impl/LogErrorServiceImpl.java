package com.ndz.wheatmall.service.base.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ndz.wheatmall.common.constant.MybatisPlusConstant;
import com.ndz.wheatmall.common.page.PageData;
import com.ndz.wheatmall.dao.base.LogErrorDao;
import com.ndz.wheatmall.entity.base.LogErrorEntity;
import com.ndz.wheatmall.service.base.LogErrorService;
import com.ndz.wheatmall.vo.base.LogErrorVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class LogErrorServiceImpl extends BaseServiceImpl<LogErrorDao, LogErrorEntity> implements LogErrorService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(LogErrorEntity entity) {
        insert(entity);
    }

    @Override
    public PageData<LogErrorVO> page(Map<String, Object> params) {
        //转换成like
        paramsToLike(params, "username");
        IPage<LogErrorEntity> page = baseDao.selectPage(
                getPage(params, MybatisPlusConstant.CREATE_DATE, false),
                new QueryWrapper<>()
        );
        return getPageData(page, LogErrorVO.class);
    }



}
