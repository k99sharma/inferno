package com.k99sharma.inferno.model;

import lombok.Data;

@Data
public class ChaosStep {
    private FailureMode mode;
    private double rate;
    private int latencyMs = 1000;
    private int duration;
}
