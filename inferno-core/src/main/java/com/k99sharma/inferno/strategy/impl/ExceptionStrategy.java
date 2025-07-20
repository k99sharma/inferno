package com.k99sharma.inferno.strategy.impl;

import com.k99sharma.inferno.strategy.ChaosStrategy;

public class ExceptionStrategy implements ChaosStrategy {
    @Override
    public void execute(long annotationLatency, long configLatency) {
        String message = "Inferno injected an exception!";

        throw new RuntimeException(message);
    }
}
