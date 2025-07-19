package com.k99sharma.inferno.config;

import com.k99sharma.inferno.model.FailureMode;
import org.springframework.stereotype.Component;

@Component
public class InfernoConfig {
    private FailureMode mode = FailureMode.LATENCY;
    private double rate = 0.3;
    private int latencyMs = 1000;

    public synchronized FailureMode getMode() {
        return mode;
    }

    public synchronized void setMode(FailureMode mode) {
        this.mode = mode;
    }

    public synchronized double getRate() {
        return rate;
    }

    public synchronized void setRate(double rate) {
        this.rate = rate;
    }

    public synchronized int getLatencyMs() {
        return latencyMs;
    }

    public synchronized void setLatencyMs(int latencyMs) {
        this.latencyMs = latencyMs;
    }
}
