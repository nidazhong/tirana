package com.ndz.tirana.entity.demo;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.ndz.tirana.common.enums.demo.PositionEnum;
import com.ndz.tirana.config.mybatis.ObjAndJsonHandler;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@TableName(value = "sys_user_demo", autoResultMap = true)
public class SysUserDemoEntity {
    @TableId(type = IdType.AUTO)
    private Long userId;
    private String userName;

    private String depId;

    /**
     * 职位枚举
     * 数据库字段：position
     */
    private PositionEnum position;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    private Integer deleteFlag;

    /**
     * 使用Jackson，推荐
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private ExtraNode extraObject;

    /**
     * 使用Fastjson
     */
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private JSONObject extraJson;

      // ObjectAndJsonHandler 为自定义
    @TableField(typeHandler = ObjAndJsonHandler.class)
    private List<ExtraNode> extraList;

    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<ExtraNode> extraArray;
    // private ExtraNode[] extraArray;


    @TableField(typeHandler = ObjAndJsonHandler.class)
    private List<ExtraNode> extraJsonArrStr;


    @Data
    public static  class ExtraNode {
        private Integer id;
        private String name;
    }


}
