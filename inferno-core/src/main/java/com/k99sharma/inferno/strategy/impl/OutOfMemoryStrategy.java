package com.k99sharma.inferno.strategy.impl;

import com.k99sharma.inferno.strategy.ChaosStrategy;

public class OutOfMemoryStrategy implements ChaosStrategy {
    @Override
    public void execute() {
        try {
            byte[][] memoryHog = new byte[512][1024 * 1024]; // ~512MB
            Thread.sleep(1000);
            memoryHog = null;

            System.gc(); // try to clean up
        }catch (InterruptedException ignore) {}
        catch (OutOfMemoryError e) {
            System.err.println("Memory bomb triggered OOM (expected)");
        }
    }
}
