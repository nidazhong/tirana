package com.ndz.tirana;

import com.alibaba.fastjson2.JSON;
import com.ndz.tirana.common.enums.demo.PositionEnum;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class NormalTests {

    @Test
    public void test01() {
        String s = JSON.toJSONString(Arrays.asList(PositionEnum.COO, PositionEnum.STAFF));
        System.out.println(s);
    }

    @Test
    public void test02() {
        System.out.println("Hello IRAN");
        System.out.println("Hello Egypt");
    }
}
