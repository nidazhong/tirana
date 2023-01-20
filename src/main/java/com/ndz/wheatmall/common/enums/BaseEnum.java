package com.ndz.wheatmall.common.enums;

/**
 *  基础枚举类
 * @param <K> 枚举的Key类型
 * @param <V> 枚举的描述类型
 */
public interface BaseEnum<K, V> {
    /**
     * 获取编码
     * @return 编码
     */
    K getCode();

    /**
     * 获取描述
     * @return 描述
     */
    V getDesc();
}
