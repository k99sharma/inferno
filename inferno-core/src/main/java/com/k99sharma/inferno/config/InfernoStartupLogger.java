package com.k99sharma.inferno.config;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InfernoStartupLogger {
    private static final Logger log = LoggerFactory.getLogger(InfernoStartupLogger.class);

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
