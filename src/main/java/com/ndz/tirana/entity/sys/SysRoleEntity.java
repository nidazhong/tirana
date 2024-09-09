package com.ndz.tirana.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("sys_role")
public class SysRoleEntity {
    @TableId(type=IdType.AUTO)
    private Long id;
    private String roleName;
    private String roleCode;
    private Date createTime;
}
