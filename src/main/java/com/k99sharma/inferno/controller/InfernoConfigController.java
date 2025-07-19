package com.k99sharma.inferno.controller;

import com.k99sharma.inferno.config.InfernoConfig;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inferno/config")
public class InfernoConfigController {
    private final InfernoConfig config;

    public InfernoConfigController(InfernoConfig config) {
        this.config = config;
    }

    @GetMapping("/status")
    public InfernoConfig getStatus() {
        return this.config;
    }

    @PostMapping("/update")
    public String updateConfig(@RequestBody InfernoConfig newConfig) {
        config.setMode(newConfig.getMode());
        config.setRate(newConfig.getRate());
        config.setLatencyMs(newConfig.getLatencyMs());

        return "Chaos config updated.";
    }
}
