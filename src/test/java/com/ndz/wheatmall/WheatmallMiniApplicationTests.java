package com.ndz.wheatmall;

import org.apache.commons.lang3.RegExUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * springboot 2.7 使用了junit5
 */
//@SpringBootTest
public class WheatmallMiniApplicationTests {
    @Test
    public void test01 () {
        System.out.println("hello world");
    }

    @Test
    public void test02 () {
        System.out.println("2023-01-12".matches("^\\d{4}-\\d{2}-\\d{2}"));
    }


}
