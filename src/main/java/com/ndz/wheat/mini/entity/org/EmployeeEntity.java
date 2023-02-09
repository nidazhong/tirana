package com.ndz.wheat.mini.entity.org;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.ndz.wheat.mini.common.enums.demo.CadreRankEnum;
import com.ndz.wheat.mini.common.enums.demo.PositionEnum;
import com.ndz.wheat.mini.config.mybatis.ListEnumTypeHandler;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * autoResultMap = true --> jsonArr 自动映射
 */
@Data
@TableName(value = "org_employee",autoResultMap = true)
public class EmployeeEntity {

    @TableId(type = IdType.AUTO) //  IdType.INPUT 自定义ID
    private String empId;
    private String name;
    private String job;
    private String department;

    /**
     * FieldStrategy.IGNORED 忽略更新策略（非空不能更新等）， 也可配置文件进行全局配置
     * typeHandler = EnumTypeHandler.class 枚举映射 ， @TableName(autoResultMap = true) 也可以自动映射
     */
    @TableField(updateStrategy= FieldStrategy.IGNORED) //typeHandler = EnumTypeHandler.class
    private CadreRankEnum cadreRank;

    /**
     * 公司职位-枚举List
     *
     * exist = false 数据库不存在该字段，不映射
     */
//    @TableField(typeHandler = EnumTypeHandler.class, updateStrategy= FieldStrategy.IGNORED, exist = false)
    @TableField(typeHandler = ListEnumTypeHandler.class, updateStrategy= FieldStrategy.IGNORED)
    private List<PositionEnum> position;

    private LocalDateTime entryDateTime;

    @TableField(typeHandler = JacksonTypeHandler.class, updateStrategy= FieldStrategy.IGNORED)
    private List<String> empTag;



}
