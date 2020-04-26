package com.flagship.startup.rules;

import java.math.BigDecimal;

/**
 * @Author: flag ship
 * @Date: 2020/4/26 21:27
 */
public class AmtStpValidProcessor extends AbstractStpValidProcessor{

    @Override
    protected StpValidResult stpValidProcess(StpData data) {
        if(data.getAmt().compareTo(new BigDecimal(0)) <= 0){
            return new StpValidResult(false,"amt <= 0!");
        }else{
            if(this.getNextStpValidProcessor() != null){
                return this.getNextStpValidProcessor().stpValidProcess(data);
            }else{
                return new StpValidResult(true,"");
            }
        }
    }
}
