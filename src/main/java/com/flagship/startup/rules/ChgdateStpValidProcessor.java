package com.flagship.startup.rules;

import java.util.Date;

/**
 * @Author: flag ship
 * @Date: 2020/4/26 21:30
 */
public class ChgdateStpValidProcessor  extends AbstractStpValidProcessor{
    @Override
    protected StpValidResult stpValidProcess(StpData data) {
        if(data.getChgDate().after(new Date())){
            return new StpValidResult(false,"future date!");
        }else{
            if(this.getNextStpValidProcessor() != null){
                return this.getNextStpValidProcessor().stpValidProcess(data);
            }else{
                return new StpValidResult(true,"");
            }
        }
    }
}
