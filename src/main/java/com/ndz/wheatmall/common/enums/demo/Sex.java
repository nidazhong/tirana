package com.ndz.wheatmall.common.enums.demo;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ndz.wheatmall.common.enums.BaseEnum;
import io.swagger.models.auth.In;
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
