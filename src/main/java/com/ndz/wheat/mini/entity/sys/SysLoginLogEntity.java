package com.ndz.wheat.mini.entity.sys;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
    * 系统访问记录
    */
@Data
@TableName("sys_login_log")
public class SysLoginLogEntity {
    /**
    * 访问ID
    */
    @TableId(type=IdType.AUTO)
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