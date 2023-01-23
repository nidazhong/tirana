package com.ndz.wheat.mini.service.sys.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ndz.wheat.mini.common.page.PageData;
import com.ndz.wheat.mini.dao.sys.SysLoginLogDao;
import com.ndz.wheat.mini.dto.sys.SysLoginLogQueryDTO;
import com.ndz.wheat.mini.entity.sys.SysLoginLogEntity;
import com.ndz.wheat.mini.service.base.impl.BaseServiceImpl;
import com.ndz.wheat.mini.service.sys.AsyncLoginLogService;
import com.ndz.wheat.mini.utils.AssertUtil;
import com.ndz.wheat.mini.vo.sys.SysLoginLogVO;

import cn.hutool.core.bean.BeanUtil;

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
    public void recordLoginLog(String username, Integer status, String ipaddr, String message) {
        SysLoginLogEntity sysLoginLog = new SysLoginLogEntity();
        sysLoginLog.setUsername(username);
        sysLoginLog.setIpaddr(ipaddr);
        sysLoginLog.setMsg(message);
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
