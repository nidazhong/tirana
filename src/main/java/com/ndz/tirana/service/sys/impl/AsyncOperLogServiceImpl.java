package com.ndz.tirana.service.sys.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ndz.tirana.entity.sys.SysOperLogEntity;
import com.ndz.tirana.service.sys.AsyncOperLogService;
import com.ndz.tirana.vo.sys.SysOperLogVO;
import com.ndz.tirana.common.page.PageData;
import com.ndz.tirana.dto.sys.SysOperLogQueryDTO;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.ndz.tirana.dao.sys.SysOperLogDao;
import com.ndz.tirana.service.base.impl.BaseServiceImpl;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AsyncOperLogServiceImpl extends BaseServiceImpl<SysOperLogDao, SysOperLogEntity>
    implements AsyncOperLogService {

    @Async
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveSysLog(SysOperLogEntity operLog) {
        if (operLog == null)
            throw new RuntimeException("保存操作日志对象为null");
        this.insert(operLog);
    }

    @Override
    public PageData<SysOperLogVO> page(Long page, Long limit, SysOperLogQueryDTO query) {
        Page<SysOperLogVO> pageParam = new Page<>(page, limit);
        IPage<SysOperLogVO> iPage = baseDao.page(pageParam, query);
        return getPageData(iPage, SysOperLogVO.class);
    }

    @Override
    public SysOperLogVO getById(Long id) {
        SysOperLogEntity sysOperLogEntity = this.selectById(id);
        assert sysOperLogEntity != null;
        return BeanUtil.copyProperties(sysOperLogEntity, SysOperLogVO.class);
    }
}
