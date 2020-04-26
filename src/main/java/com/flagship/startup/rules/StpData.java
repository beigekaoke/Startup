package com.flagship.startup.rules;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: flag ship
 * @Date: 2020/4/26 21:19
 */

@Data
public class StpData {

    private String name;

    private BigDecimal amt;

    private Date chgDate;
}
