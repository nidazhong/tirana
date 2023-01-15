package com.ndz.wheatmall.controller.demo;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
//@JsonFormat(shape = JsonFormat.Shape.OBJECT) // 自定义序列化枚举
public enum CourseType {
    /**
     * 图文
     */
    PICTURE(102, "图文"),
    /**
     * 音频
     */
    AUDIO(103, "音频"),
    /**
     * 视频
     */
    VIDEO(104, "视频"),
    /**
     * 外链
     */
    URL(105, "外链");

    private final int type;
    private final String desc;

    /**
     * 重写toString 返回前端
     */
    @Override
    public String toString() {
        return this.type+":"+this.desc;
    }


}