package com.ndz.wheatmall.vo.demo;

import com.ndz.wheatmall.common.enums.demo.PositionEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class SysUserDemoVO {

    @ApiModelProperty(value = "用户id")
    private Long userId;
    @ApiModelProperty(value = "用户姓名", required = true)
    private String userName;

    @ApiModelProperty(value = "部门id",required = true)
    private Long depId;

    @ApiModelProperty(value = "职位", example = "COO")
    private PositionEnum position;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;


}
