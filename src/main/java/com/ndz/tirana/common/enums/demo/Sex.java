package com.ndz.tirana.common.enums.demo;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ndz.tirana.common.enums.BaseEnum;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Sex implements BaseEnum<Integer, String> {
    /**
     * 性别枚举
     */
    MAN(1,"男"),
    WOMAN(2,"女");
    @EnumValue
    private Integer code;

    private String desc;


    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    @JsonValue
    public String getDesc() {
        return desc;
    }
}
