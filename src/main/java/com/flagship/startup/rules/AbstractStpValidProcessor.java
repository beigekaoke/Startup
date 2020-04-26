package com.flagship.startup.rules;

import java.util.Comparator;

/**
 * @Author: flag ship
 * @Date: 2020/4/26 19:57
 */
public abstract class AbstractStpValidProcessor {

    //处理器优先级
    protected int priority;

    //处理器的下一个处理器
    protected AbstractStpValidProcessor nextStpValidProcessor;

    public AbstractStpValidProcessor setNextStpSValidProcessor (AbstractStpValidProcessor nextStpValidProcessor){
        this.nextStpValidProcessor = nextStpValidProcessor;
        return this.nextStpValidProcessor;
    }

    public AbstractStpValidProcessor getNextStpValidProcessor() {
        return nextStpValidProcessor;
    }

    abstract protected StpValidResult stpValidProcess(StpData data);
}
