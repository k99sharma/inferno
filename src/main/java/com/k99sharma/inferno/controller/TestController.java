package com.k99sharma.inferno.controller;

import com.k99sharma.inferno.annotations.InjectInferno;
import com.k99sharma.inferno.model.FailureMode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/ping")
    @InjectInferno(mode = FailureMode.LATENCY, rate = 0.5, latencyMs = 2000)
    public String ping() {
        return "pong";
    }

    @GetMapping("/crash")
    @InjectInferno(mode = FailureMode.EXCEPTION, rate = 0.3)
    public String crashTest() {
        return "This might crash";
    }
}
