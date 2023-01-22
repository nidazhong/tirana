package com.ndz.wheat.mini.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
    * 角色菜单
    */
@Data
@TableName("sys_role_menu")
public class SysRoleMenuEntity {
    @TableId(type= IdType.AUTO)
    private Long id;

    private Long roleId;

    private Long menuId;
}