package com.ndz.wheat.mini.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
    * 用户角色
    */
@Data
@TableName("sys_user_role")
public class SysUserRoleEntity {
    /**
    * 主键id
    */
    @TableId(type= IdType.AUTO)
    private Long id;

    /**
    * 角色id
    */
    private Long roleId;

    /**
    * 用户id
    */
    private Long userId;
}