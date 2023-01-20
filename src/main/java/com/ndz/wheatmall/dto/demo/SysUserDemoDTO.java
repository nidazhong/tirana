package com.ndz.wheatmall.dto.demo;

import com.ndz.wheatmall.common.enums.demo.PositionEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SysUserDemoDTO {

    @ApiModelProperty(value = "用户id")
    private Long userId;
    @ApiModelProperty(value = "用户姓名", required = true)
    private String userName;

    @ApiModelProperty(value = "部门id",required = true)
    private String depId;

    @ApiModelProperty(value = "职位", example = "COO")
    private  PositionEnum position;


}
