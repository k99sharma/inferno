package com.k99sharma.simulation.controller;

import com.k99sharma.inferno.annotations.InjectInferno;
import com.k99sharma.inferno.model.FailureMode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inferno/test")
public class ChaosModeController {
    @GetMapping("/latency")
    @InjectInferno(mode = FailureMode.LATENCY, rate = 0.3, latencyMs = 2000)
    public String latencyTest() {
        return "Latency Mode";
    }

    @GetMapping("/exception")
    @InjectInferno(mode = FailureMode.EXCEPTION, rate = 0.3)
    public String exceptionTest() {
        return "Exception Mode";
    }

    @GetMapping("/timeout")
    @InjectInferno(mode = FailureMode.TIMEOUT, rate = 0.3)
    public String timeoutTest() {
        return "Timeout Mode";
    }

    @GetMapping("/cpu-spike")
    @InjectInferno(mode = FailureMode.CPU_SPIKE, rate = 0.3)
    public String cpuSpikeTest() {
        return "CPU Spike Mode";
    }

    @GetMapping("/out-of-memory")
    @InjectInferno(mode = FailureMode.OUT_OF_MEMORY, rate = 0.3)
    public String outOfMemoryTest() {
        return "Out of Memory Mode";
    }
}
