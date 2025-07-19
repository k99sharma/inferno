package com.k99sharma.inferno.controller;

import com.k99sharma.inferno.service.InfernoStats;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/inferno/metrics")
public class InfernoMetricsController {
    private final InfernoStats stats;

    public InfernoMetricsController(InfernoStats stats) {
        this.stats = stats;
    }

    @GetMapping
    public Map<String, Object> getMetrics() {
        return Map.of(
                "totalRequests", stats.getTotalRequests(),
                "totalInjected", stats.getTotalInjections(),
                "byMode", stats.getModeStats()
        );
    }

    @PostMapping
    public String resetMetrics() {
        stats.reset();
        return "Metrics reset.";
    }
}
