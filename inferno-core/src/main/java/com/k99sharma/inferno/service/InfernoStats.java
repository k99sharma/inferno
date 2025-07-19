package com.k99sharma.inferno.service;

import com.k99sharma.inferno.model.FailureMode;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class InfernoStats {
    private final AtomicLong totalRequests = new AtomicLong();
    private final AtomicLong totalInjected = new AtomicLong();
    private final Map<FailureMode, AtomicLong> modeCount = new EnumMap<> (FailureMode.class);

    public void recordRequest() {
        totalRequests.incrementAndGet();
    }

    public void recordInjection(FailureMode mode) {
        totalInjected.incrementAndGet();
        modeCount.computeIfAbsent(mode, m -> new AtomicLong()).incrementAndGet();
    }

    public long getTotalRequests() {
        return totalRequests.get();
    }

    public long getTotalInjections() {
        return totalInjected.get();
    }

    public Map<FailureMode, Long> getModeStats() {
        Map<FailureMode, Long> result = new EnumMap<>(FailureMode.class);
        modeCount.forEach((mode, count) -> result.put(mode, count.get()));
        return result;
    }

    public void reset() {
        totalRequests.set(0);
        totalInjected.set(0);
        modeCount.clear();
    }
}
