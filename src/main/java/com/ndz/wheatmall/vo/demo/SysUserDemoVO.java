package com.ndz.wheatmall.vo.demo;

import java.time.LocalDateTime;
import java.util.List;

import com.alibaba.fastjson2.JSONObject;
import com.ndz.wheatmall.common.enums.demo.PositionEnum;
import com.ndz.wheatmall.entity.demo.SysUserDemoEntity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

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


    @ApiModelProperty(value = "extraObject[Jackson]")
    private SysUserDemoEntity.ExtraNode extraObject;
    @ApiModelProperty(value = "extraJson[Fastjson]")
    private JSONObject extraJson;
    @ApiModelProperty(value = "extraList[ObjectAndJsonHandler自定义]")
    private List<SysUserDemoEntity.ExtraNode> extraList;
    @ApiModelProperty(value = "extraArray[Jackson]")
    private List<SysUserDemoEntity.ExtraNode> extraArray;

    private List<SysUserDemoEntity.ExtraNode> extraJsonArrStr;

}
