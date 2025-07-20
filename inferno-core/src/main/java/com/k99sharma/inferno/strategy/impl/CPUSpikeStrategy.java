package com.k99sharma.inferno.strategy.impl;

import com.k99sharma.inferno.strategy.ChaosStrategy;

public class CPUSpikeStrategy implements ChaosStrategy {
    private static final long spikeTime = 30000;

    @Override
    public void execute() {
        long startTime = System.currentTimeMillis();

        while(System.currentTimeMillis() - startTime < spikeTime){
            double waste = Math.pow(Math.random(), Math.random());
        }
    }
}
