package com.flagship.startup.rules;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: flag ship
 * @Date: 2020/4/26 21:23
 */

@Data
@AllArgsConstructor
public class StpValidResult {

    private boolean rs;

    private String rsMsg;
}
