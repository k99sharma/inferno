package com.k99sharma.inferno.config;

import com.k99sharma.inferno.aspect.InfernoAspect;
import com.k99sharma.inferno.model.FailureMode;
import com.k99sharma.inferno.service.InfernoStats;
import com.k99sharma.inferno.service.ScriptRunner;
import com.k99sharma.inferno.strategy.ChaosRegistry;
import com.k99sharma.inferno.strategy.impl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({InfernoConfig.class, InfernoProperties.class})
public class InfernoCoreConfiguration {
    private static final Logger log = LoggerFactory.getLogger(InfernoCoreConfiguration.class);

    public InfernoCoreConfiguration() {
        InfernoRuntime.markAnnotationEnabled();
    }

    @Bean
    public InfernoStartupLogger infernoStartupLogger(InfernoProperties infernoProperties) {
        return new InfernoStartupLogger(infernoProperties);
    }

    @Bean
    public ChaosRegistry chaosRegistry(InfernoConfig config) {
        ChaosRegistry registry = new ChaosRegistry();

        registry.register(FailureMode.LATENCY, new LatencyChaos(config.getLatencyMs()));
        registry.register(FailureMode.EXCEPTION, new ExceptionStrategy());
        registry.register(FailureMode.CPU_SPIKE, new CPUSpikeStrategy());
        registry.register(FailureMode.OUT_OF_MEMORY, new OutOfMemoryStrategy());
        registry.register(FailureMode.TIMEOUT, new TimeOutStrategy());

        log.debug("ChaosRegistry initialized with {} strategies", registry.getSize());

        return registry;
    }

    @Bean
    public InfernoAspect infernoAspect() {
        return new InfernoAspect();
    }

    @Bean
    public InfernoStats infernoStats() {
        return new InfernoStats();
    }

    @Bean
    public ScriptRunner scriptRunner(InfernoConfig config) {
        return new ScriptRunner(config);
    }
}
