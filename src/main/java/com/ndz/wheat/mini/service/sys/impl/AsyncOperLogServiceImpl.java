package com.ndz.wheat.mini.service.sys.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ndz.wheat.mini.common.page.PageData;
import com.ndz.wheat.mini.dto.sys.SysOperLogQueryDTO;
import com.ndz.wheat.mini.vo.sys.SysLoginLogVO;
import com.ndz.wheat.mini.vo.sys.SysOperLogVO;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.ndz.wheat.mini.dao.sys.SysOperLogDao;
import com.ndz.wheat.mini.entity.sys.SysOperLogEntity;
import com.ndz.wheat.mini.service.base.impl.BaseServiceImpl;
import com.ndz.wheat.mini.service.sys.AsyncOperLogService;
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
