package com.ndz.tirana.entity.sys;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
    * 操作日志记录
    */
@Data
@TableName("sys_oper_log")
public class SysOperLogEntity {
    /**
    * 日志主键
    */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
    * 模块标题
    */
    private String title;

    /**
    * 业务类型（0其它 1新增 2修改 3删除）
    */
    private String businessType;

    /**
    * 方法名称
    */
    private String method;

    /**
    * 请求方式
    */
    private String requestMethod;

    /**
    * 操作类别（0其它 1后台用户 2手机端用户）
    */
    private String operatorType;

    /**
    * 操作人员
    */
    private String operName;

    /**
    * 部门名称
    */
    private String deptName;

    /**
    * 请求URL
    */
    private String operUrl;

    /**
    * 主机地址
    */
    private String operIp;

    /**
    * 请求参数
    */
    private String operParam;

    /**
    * 返回参数
    */
    private String jsonResult;

    /**
    * 操作状态（0正常 1异常）
    */
    private Integer status;

    /**
    * 错误消息
    */
    private String errorMsg;

    /**
    * 操作时间
    */
    private LocalDateTime operTime;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    /**
    * 删除标记（0:可用 1:已删除）
    */
    private Integer isDeleted;
}