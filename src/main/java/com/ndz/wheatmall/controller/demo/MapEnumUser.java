package com.ndz.wheatmall.controller.demo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MapEnumUser {

    @ApiModelProperty(value = "姓名",allowableValues = "张飞,关羽,赵云")
    private String name;

    @ApiModelProperty(value = "枚举类型")
    private CourseType courseType;


}