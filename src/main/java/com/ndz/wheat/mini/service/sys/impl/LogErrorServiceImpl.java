package com.ndz.wheat.mini.service.sys.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ndz.wheat.mini.common.constant.MybatisConstant;
import com.ndz.wheat.mini.common.page.PageData;
import com.ndz.wheat.mini.dao.sys.LogErrorDao;
import com.ndz.wheat.mini.entity.base.LogErrorEntity;
import com.ndz.wheat.mini.service.base.impl.BaseServiceImpl;
import com.ndz.wheat.mini.service.sys.LogErrorService;
import com.ndz.wheat.mini.vo.sys.LogErrorVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
//        paramsToLike(params, "username");
        IPage<LogErrorEntity> page = baseDao.selectPage(
                getPage(params, MybatisConstant.CREATE_DATE, false), new QueryWrapper<>()
        );
        return getPageData(page, LogErrorVO.class);
    }

}
