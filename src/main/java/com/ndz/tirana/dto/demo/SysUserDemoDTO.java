package com.ndz.tirana.dto.demo;

import java.util.List;

import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ndz.tirana.common.enums.demo.PositionEnum;
import com.ndz.tirana.config.BaseEnumDeserializer;
import com.ndz.tirana.entity.demo.SysUserDemoEntity;
import com.ndz.tirana.common.enums.demo.Sex;

//import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SysUserDemoDTO {

    //@ApiModelProperty(value = "用户id")
    private Long userId;
    //@ApiModelProperty(value = "用户姓名", required = true)
    private String userName;

    //@ApiModelProperty(value = "部门id",required = true)
    private String depId;

    //@ApiModelProperty(value = "职位； 1-总经理 2-首席运营官 3-职员",example = "1", required = true)
    @JsonDeserialize(using = BaseEnumDeserializer.class)
    private PositionEnum position;

    //@ApiModelProperty(value = "性别；1-男 2-女" , example = "1", required = true)
    @JsonDeserialize(using = BaseEnumDeserializer.class)
    private Sex sex;


    //@ApiModelProperty(value = "扩展对象", required = true)
    private SysUserDemoEntity.ExtraNode extraObject;

    private JSONObject extraJson;

    private List<SysUserDemoEntity.ExtraNode> extraList;

    private SysUserDemoEntity.ExtraNode[] extraArray;

    //@ApiModelProperty(value = "扩展对象JSON集合", required = true)
    private List<SysUserDemoEntity.ExtraNode> extraJsonArrStr;



}
