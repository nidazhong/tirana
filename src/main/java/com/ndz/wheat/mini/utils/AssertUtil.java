package com.ndz.wheat.mini.utils;

import com.ndz.wheat.mini.exception.WheatException;

import cn.hutool.core.collection.CollUtil;


/**
 * 自定义断言，如有表达式不为真，则抛出麦子异常
 */
public class AssertUtil {
    public AssertUtil(){}

    /**
     * 断言表达式为真
     */
    public static  void isTrue(boolean expression, String msg){
        if(!expression) {
            throw new WheatException(msg);
        }
    }

    /**
     * 断言集合不为空
     */
    public static void notEmpty(Iterable<?> iterable, String msg) {
        if (CollUtil.isEmpty(iterable)) {
            throw new WheatException(msg);
        }
    }

    /**
     * 断言集合为空
     */
    public static void isEmpty(Iterable<?> iterable, String msg) {
        if (CollUtil.isNotEmpty(iterable)) {
            throw new WheatException(msg);
        }
    }

    /**
     * 断言obj不是null
     */
    public static  void notNull(Object obj, String msg){
        if(obj==null) {
            throw new WheatException(msg);
        }
    }


    /**
     * 断言obj是null
     */
    public static  void isNull(Object obj, String msg){
        if(obj!=null) {
            throw new WheatException(msg);
        }
    }



}
