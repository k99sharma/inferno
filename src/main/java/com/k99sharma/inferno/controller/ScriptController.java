package com.k99sharma.inferno.controller;

import com.k99sharma.inferno.service.ScriptRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("inferno/script")
public class ScriptController {
    private final ScriptRunner runner;

    public ScriptController(ScriptRunner runner) {
        this.runner = runner;
    }

    @PostMapping("/start")
    public String startScript() {
        if(runner.isRunning()) {
            return "Script already running.";
        }

        new Thread(runner).start();
        return "Inferno chaos script started!";
    }

    @GetMapping("/status")
    public String getStatus() {
        return runner.isRunning() ? "Script running" : "Idle";
    }
}
