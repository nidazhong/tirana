package com.ndz.wheat.mini;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.ndz.wheat.mini.common.enums.demo.PositionEnum;

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
    }
}
