package com.ndz.tirana;

import java.util.Arrays;

import com.alibaba.fastjson2.JSON;
import com.ndz.tirana.common.enums.demo.PositionEnum;

import org.junit.jupiter.api.Test;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;

public class NormalTests {

    @Test
    public void test01() {

        //
        String s = JSONUtil.toJsonStr(Arrays.asList(PositionEnum.COO, PositionEnum.STAFF));
        System.out.println(s);
    }

    @Test
    public void test02() {
        System.out.println(IdUtil.fastSimpleUUID());
        System.out.println(IdUtil.simpleUUID());
        //

        System.out.println(UUID.fastUUID());
        System.out.println(UUID.randomUUID());
        System.out.println(UUID.randomUUID(true));
        System.out.println("Hello IRAN");
        System.out.println("Hello Egypt");
    }
}
