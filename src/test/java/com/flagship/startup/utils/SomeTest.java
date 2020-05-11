package com.flagship.startup.utils;

import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static com.flagship.startup.utils.DateUtils.getAutoDate;

/**
 * @Author: flag ship
 * @Date: 2020/3/28 19:34
 */
public class SomeTest {

    @Test
    public void test1() {
        Integer f1 = 100, f2 = 100, f3 = 150, f4 = 150;
        int ii = 32;
        System.out.println(f1 == f2);
        System.out.println(f3 == f4);

        String ss = "weqwrqwer";
        byte[] bbs = ss.getBytes();
    }

    @Test
    public void testDateFormat() throws ParseException {
        System.out.println(getAutoDate("1987-11-04"));
        System.out.println(getAutoDate("1987-11-04 12:50"));
        System.out.println(getAutoDate("1987-11-04 12:50:15"));
        System.out.println(getAutoDate("1987-11-04 12:50:15.000"));
        System.out.println(getAutoDate("19871104"));
        System.out.println(getAutoDate("04/11/1987"));
        System.out.println(getAutoDate("1/1/1987"));
    }

}
