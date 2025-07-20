package com.k99sharma.inferno.strategy;

public interface ChaosStrategy {
    void execute(long annotationLatency, long configLatency);
}
