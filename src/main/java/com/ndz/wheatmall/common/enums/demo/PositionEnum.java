package com.ndz.wheatmall.common.enums.demo;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ndz.wheatmall.common.enums.BaseEnum;
import lombok.AllArgsConstructor;

// https://www.jianshu.com/p/b719548e204a
// see:https://blog.csdn.net/XY1790026787/article/details/107768555#:~:text=%E4%BD%BF%E7%94%A8%20%40JsonFormat%20%28shape%20%3D%20JsonFormat.Shape.OBJECT%29,%E6%B3%A8%E8%A7%A3%E6%9D%A5%E8%AE%A9%E6%9E%9A%E4%B8%BE%E6%8C%89%E7%85%A7%E7%B1%BB%EF%BC%88%E5%AF%B9%E8%B1%A1%EF%BC%89%E7%9A%84%E6%A0%BC%E5%BC%8F%E8%BF%9B%E8%A1%8C%E5%BA%8F%E5%88%97%E5%8C%96%E3%80%82%20%E4%BD%BF%E7%94%A8%20%40JsonCreator%20%E6%B3%A8%E8%A7%A3%E6%A0%87%E8%AE%B0%E4%B8%80%E4%B8%AA%E9%80%9A%E8%BF%87%E6%9E%9A%E4%B8%BE%E7%A0%81%E6%9D%A5%E6%9F%A5%E8%AF%A2%E6%9E%9A%E4%B8%BE%E7%9A%84%E6%96%B9%E6%B3%95%EF%BC%8CJackson%20%E4%BC%9A%E4%BD%BF%E7%94%A8%E8%BF%99%E4%B8%AA%E6%9C%89%E5%8F%82%E6%9E%84%E9%80%A0%E5%99%A8%E8%BF%9B%E8%A1%8C%E5%8F%8D%E5%BA%8F%E5%88%97%E5%8C%96%E3%80%82
@AllArgsConstructor
//@JsonFormat(shape = JsonFormat.Shape.OBJECT) // 前端使用type数字传入标志枚举， 无JsonFormat则用枚举的类型GM，COO....
//@Getter
public  enum PositionEnum implements BaseEnum {

    GM(1, "总经理"),

    COO(2, "首席运营官"),
    STAFF(3, "职员"),
    ;

    @EnumValue // 后端存储的值
    private final Integer code;

    private final String desc;

//    @JsonValue //标记响应json值，返回给前端显示的值
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
