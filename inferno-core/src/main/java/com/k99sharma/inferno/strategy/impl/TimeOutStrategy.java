package com.k99sharma.inferno.strategy.impl;

import com.k99sharma.inferno.strategy.ChaosStrategy;

public class TimeOutStrategy implements ChaosStrategy {
    private static final long TIMEOUT_MILLIS = 10000;

    @Override
    public void execute() {
        try{
            Thread.sleep(TIMEOUT_MILLIS);
        }catch (InterruptedException ignore) {}
    }
}
