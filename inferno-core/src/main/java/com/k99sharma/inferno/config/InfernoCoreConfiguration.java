package com.k99sharma.inferno.config;

import com.k99sharma.inferno.aspect.InjectInfernoAspect;
import com.k99sharma.inferno.model.FailureMode;
import com.k99sharma.inferno.strategy.ChaosRegistry;
import com.k99sharma.inferno.strategy.impl.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableConfigurationProperties({InfernoConfig.class, InfernoProperties.class})
public class InfernoCoreConfiguration {
    public InfernoCoreConfiguration() {
        InfernoRuntime.markAnnotationEnabled();
    }

    @Bean
    public InfernoStartupLogger infernoStartupLogger(InfernoProperties infernoProperties) {
        return new InfernoStartupLogger(infernoProperties);
    }

    @Bean
    public ChaosRegistry chaosRegistry() {
        ChaosRegistry registry = new ChaosRegistry();

        registry.register(FailureMode.LATENCY, new LatencyStrategy());
        registry.register(FailureMode.EXCEPTION, new ExceptionStrategy());
        registry.register(FailureMode.CPU_SPIKE, new CPUSpikeStrategy());
        registry.register(FailureMode.OUT_OF_MEMORY, new OutOfMemoryStrategy());
        registry.register(FailureMode.TIMEOUT, new TimeOutStrategy());

        log.debug("ChaosRegistry initialized with {} strategies", registry.getSize());

        return registry;
    }

    @Bean
    public InjectInfernoAspect infernoAspect() {
        return new InjectInfernoAspect();
    }
}
