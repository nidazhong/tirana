package com.ndz.tirana.service.sys.impl;

import javax.annotation.Resource;

import com.ndz.tirana.dao.sys.SysLoginLogDao;
import com.ndz.tirana.entity.sys.SysLoginLogEntity;
import com.ndz.tirana.service.sys.AsyncLoginLogService;
import com.ndz.tirana.vo.sys.SysLoginLogVO;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ndz.tirana.common.page.PageData;
import com.ndz.tirana.dto.sys.SysLoginLogQueryDTO;
import com.ndz.tirana.service.base.impl.BaseServiceImpl;
import com.ndz.tirana.utils.AssertUtil;

import cn.hutool.core.bean.BeanUtil;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class AsyncLoginLogServiceImpl extends BaseServiceImpl<SysLoginLogDao, SysLoginLogEntity> implements AsyncLoginLogService {
    @Resource
    private SysLoginLogDao sysLoginLogDao;

    /**
     * 记录登录信息
     *
     * @param username 用户名
     * @param status 状态
     * @param ipaddr ip
     * @param message 消息内容
     * @return
     */
    @Async
    @Transactional(rollbackFor = Exception.class)
    public void recordLoginLog(String username, Integer status, String ipaddr, String message) {
        SysLoginLogEntity sysLoginLog = new SysLoginLogEntity();
        sysLoginLog.setUsername(username);
        sysLoginLog.setIpaddr(ipaddr);
        sysLoginLog.setMsg(message);
        sysLoginLog.setAccessTime(LocalDateTime.now());
        // 日志状态
        sysLoginLog.setStatus(status);
        sysLoginLogDao.insert(sysLoginLog);
    }

    @Override
    public SysLoginLogVO getById(Long id) {
        AssertUtil.notNull(id, "登陆日志Id不能为空！");
        SysLoginLogEntity sysLoginLogEntity = sysLoginLogDao.selectById(id);
        return BeanUtil.copyProperties(sysLoginLogEntity, SysLoginLogVO.class);
    }

    @Override
    public PageData<SysLoginLogVO> page(Long page, Long limit, SysLoginLogQueryDTO query) {
        Page<SysLoginLogVO> pageParam = new Page<>(page, limit);
        IPage<SysLoginLogVO> iPage = baseDao.page(pageParam, query);
        return  getPageData(iPage, SysLoginLogVO.class);
    }
}
