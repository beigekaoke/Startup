package com.flagship.startup.rules;

/**
 * @Author: flag ship
 * @Date: 2020/4/26 21:21
 */
public class NameStpValidProcessor extends AbstractStpValidProcessor{

    @Override
    protected StpValidResult stpValidProcess(StpData data) {
        if(data.getName().contains("_")){
            return new StpValidResult(false,"contain some invalid word!");
        }else{
            if(this.getNextStpValidProcessor() != null){
                return this.getNextStpValidProcessor().stpValidProcess(data);
            }else{
                return new StpValidResult(true,"");
            }
        }
    }
}
