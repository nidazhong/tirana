package com.ndz.tirana.common.enums.demo;


import com.ndz.tirana.common.enums.BaseEnum;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 干部职级
 */


public enum CadreRankEnum implements BaseEnum<Integer, String> {
    DEPARTMENT_LEVEL(1, "正厅"),
    ASSISTANT_DEPARTMENT_LEVEL(2,"副厅级"),
    BUREAU_LEVEL(3,"局级"),
    ASSISTANT_BUREAU_LEVEL(4,"副局级"),
    SECTION_LEVEL(5,"处级"),
    ASSISTANT_SECTION_LEVEL(6,"副处级"),
    STRAIGHT_LEVEL(7,"正科级"),
    ASSISTANT_STRAIGHT_LEVEL(8,"副科级"),
    SELECT(9,"科员");

    @EnumValue
    private Integer code;
    @JsonValue
    private String desc;

    CadreRankEnum(Integer val, String desc) {
        this.code = val;
        this.desc = desc;
    }


    @Override
    public Integer getCode() {
        return code;
    }


    @Override
    public String getDesc() {
        return desc;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
