package com.k99sharma.inferno.strategy.impl;

import com.k99sharma.inferno.strategy.ChaosStrategy;

public class LatencyChaos implements ChaosStrategy {
    private final int latencyMs;

    public LatencyChaos(int latencyMs) {
        this.latencyMs = latencyMs;
    }

    @Override
    public void execute() {
        try{
            Thread.sleep(latencyMs);
        }catch (InterruptedException ignore) {}
    }
}
