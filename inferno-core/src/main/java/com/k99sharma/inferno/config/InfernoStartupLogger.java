package com.k99sharma.inferno.config;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InfernoStartupLogger {
    private final InfernoProperties infernoProps;

    public InfernoStartupLogger(InfernoProperties infernoProps) {
        this.infernoProps = infernoProps;
    }

    @PostConstruct
    public void logStartup() {

        if(Boolean.TRUE.equals(infernoProps.isEnabled()) || InfernoRuntime.isAnnotationEnabled()) {
            log.info("INFERNO ENABLED");
        }else {
            log.info("INFERNO DISABLED");
        }
    }
}
