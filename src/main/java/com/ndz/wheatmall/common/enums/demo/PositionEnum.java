package com.ndz.wheatmall.common.enums.demo;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PositionEnum {

    GM(1, "总经理"),

    COO(2, "首席运营官"),
    STAFF(3, "职员"),
    ;

    @EnumValue
    @JsonValue //标记响应json值
    private final int type;
    private final String desc;



}
