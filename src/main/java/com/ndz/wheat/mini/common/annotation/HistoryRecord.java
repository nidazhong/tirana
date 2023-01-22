package com.ndz.wheat.mini.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface HistoryRecord {

    /**
     * 定义字段对应的表字段名
     * @return
     */
    String field();
}
