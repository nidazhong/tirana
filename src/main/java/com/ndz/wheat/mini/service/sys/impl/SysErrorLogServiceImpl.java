package com.ndz.wheat.mini.service.sys.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ndz.wheat.mini.common.constant.MybatisConstant;
import com.ndz.wheat.mini.common.page.PageData;
import com.ndz.wheat.mini.dao.sys.LogErrorDao;
import com.ndz.wheat.mini.entity.base.SysErrorLogEntity;
import com.ndz.wheat.mini.service.base.impl.BaseServiceImpl;
import com.ndz.wheat.mini.service.sys.SysErrorLogService;
import com.ndz.wheat.mini.vo.sys.LogErrorVO;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class SysErrorLogServiceImpl extends BaseServiceImpl<LogErrorDao, SysErrorLogEntity> implements SysErrorLogService {

    @Transactional(rollbackFor = Exception.class)
    @Async
    @Override
    public void save(SysErrorLogEntity entity) {
        insert(entity);
    }

    @Override
    public PageData<LogErrorVO> page(Map<String, Object> params) {
        // 查询条件
        String keyword = (String) params.get("keyword");
        LambdaQueryWrapper<SysErrorLogEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StrUtil.isNotEmpty((String)params.get("requestUri")), SysErrorLogEntity::getRequestUri, params.get("requestUri"));
        queryWrapper.and(StrUtil.isNotEmpty(keyword), qr->qr.like(SysErrorLogEntity::getRequestMethod, keyword)
                                                          .or(it->it.like(SysErrorLogEntity::getIp, keyword)));

        IPage<SysErrorLogEntity> page = baseDao.selectPage(
                getPage(params, MybatisConstant.CREATE_DATE, false), queryWrapper
        );
        return getPageData(page, LogErrorVO.class);
    }


    @Override
    public LogErrorVO getById(Long id) {
        SysErrorLogEntity logErrorEntity = selectById(id);
        return BeanUtil.copyProperties(logErrorEntity, LogErrorVO.class);
    }

}
