package com.ndz.wheatmall.controller.demo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class MapUser {

    @ApiModelProperty(value = "pid-测试id")
    private Integer pid;

    @ApiModelProperty(value = "用户列表")
    private HashMap<String, List<SysUser>> userLists;
}
