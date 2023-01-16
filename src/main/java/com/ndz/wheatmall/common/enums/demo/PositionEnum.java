package com.ndz.wheatmall.common.enums.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PositionEnum {

    GM(1, "总经理"),

    COO(2, "首席运营官"),
    STAFF(3, "职员"),
    ;

    private final int type;
    private final String desc;



}
