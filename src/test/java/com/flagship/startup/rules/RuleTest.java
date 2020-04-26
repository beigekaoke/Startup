package com.flagship.startup.rules;

import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: flag ship
 * @Date: 2020/4/26 21:38
 */
public class RuleTest {

    @Test
    public void testRules() throws ParseException {
        StpData data1 = new StpData();
        data1.setName("ssssss");
        data1.setAmt(new BigDecimal(12));
        data1.setChgDate(new Date());
        StpData data2 = new StpData();
        data2.setName("_ssssss");
        data2.setAmt(new BigDecimal(12));
        data2.setChgDate(new Date());
        StpData data3 = new StpData();
        data3.setName("ssssss");
        data3.setAmt(new BigDecimal(0));
        data3.setChgDate(new Date());
        StpData data4 = new StpData();
        data4.setName("ssssss");
        data4.setAmt(new BigDecimal(12));
        data4.setChgDate(getDateStr("2021-11-11"));

        AbstractStpValidProcessor processor1 = new NameStpValidProcessor();
        processor1.setNextStpSValidProcessor(new AmtStpValidProcessor()).setNextStpSValidProcessor(new ChgdateStpValidProcessor());


        System.out.println(processor1.stpValidProcess(data1));
        System.out.println(processor1.stpValidProcess(data2));
        System.out.println(processor1.stpValidProcess(data3));
        System.out.println(processor1.stpValidProcess(data4));

    }

    private Date getDateStr(String dateStr) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
        Date date = simpleDateFormat.parse(dateStr);
        return date;
    }
}
