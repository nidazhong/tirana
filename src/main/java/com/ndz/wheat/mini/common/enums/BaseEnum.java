package com.ndz.wheat.mini.common.enums;

/**
 *  业务相关基础枚举类，所有表关联的枚举继承与此
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
