package com.ndz.tirana.service.sys;

import com.ndz.tirana.vo.sys.SysLoginLogVO;
import com.ndz.tirana.common.page.PageData;
import com.ndz.tirana.dto.sys.SysLoginLogQueryDTO;

/**
 * 异步调用日志服务
 */
public interface AsyncLoginLogService {
    /**
     * 记录登录信息
     *
     * @param username 用户名
     * @param status 状态
     * @param ipaddr ip
     * @param message 消息内容
     * @return
     */
    void recordLoginLog(String username, Integer status, String ipaddr, String message);

    SysLoginLogVO getById(Long id);

    PageData<SysLoginLogVO> page(Long page, Long limit, SysLoginLogQueryDTO query);
}
