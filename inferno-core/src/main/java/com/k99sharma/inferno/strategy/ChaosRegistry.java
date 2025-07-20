package com.k99sharma.inferno.strategy;

import com.k99sharma.inferno.model.FailureMode;

import java.util.EnumMap;
import java.util.Map;

public class ChaosRegistry {
    private final Map<FailureMode, ChaosStrategy> chaosMap = new EnumMap<>(FailureMode.class);

    public void register(FailureMode mode, ChaosStrategy strategy) {
        chaosMap.put(mode, strategy);
    }

    public int getSize() {
        return chaosMap.size();
    }

    public ChaosStrategy get(FailureMode mode) {
        return chaosMap.get(mode);
    }
}
