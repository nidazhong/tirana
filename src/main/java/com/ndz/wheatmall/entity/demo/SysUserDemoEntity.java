package com.ndz.wheatmall.entity.demo;

import com.baomidou.mybatisplus.annotation.*;
import com.ndz.wheatmall.common.enums.demo.PositionEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@TableName("sys_user_demo")
public class SysUserDemoEntity {
    @TableId(type = IdType.AUTO)
    private Long userId;
    private String userName;

    private String depId;

    private PositionEnum position;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
