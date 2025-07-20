package com.k99sharma.inferno.strategy.impl;

import com.k99sharma.inferno.strategy.ChaosStrategy;

public class LatencyStrategy implements ChaosStrategy {
    @Override
    public void execute(long annotationLatency, long configLatency) {
        try{
            long latency = annotationLatency > 0 ? annotationLatency : configLatency;
            Thread.sleep(latency);
        }catch (InterruptedException ignore) {}
    }
}
