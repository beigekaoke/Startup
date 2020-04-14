package com.flagship.startup.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @Author: flag ship
 * @Date: 2020/4/11 20:19
 */
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ComData {

    int refNo;

    String memberName;

    BigDecimal amt;

}
