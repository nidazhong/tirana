package com.ndz.wheat.mini.vo.sys;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SysLoginLogVO {
    private Long id;

    /**
     * 用户账号
     */
    private String username;

    /**
     * 登录IP地址
     */
    private String ipaddr;

    /**
     * 登录状态（0成功 1失败）
     */
    private Integer status;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 访问时间
     */
    private LocalDateTime accessTime;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    /**
     * 删除标记（0:可用 1:已删除）
     */
    private Integer isDeleted;
}
