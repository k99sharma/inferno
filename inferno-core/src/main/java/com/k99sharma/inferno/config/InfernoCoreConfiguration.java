package com.k99sharma.inferno.config;

import com.k99sharma.inferno.annotations.EnableInferno;
import com.k99sharma.inferno.aspect.InjectInfernoAspect;
import com.k99sharma.inferno.model.FailureMode;
import com.k99sharma.inferno.strategy.ChaosRegistry;
import com.k99sharma.inferno.strategy.impl.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

@Slf4j
@Configuration
@EnableConfigurationProperties({InfernoConfig.class, InfernoProperties.class})
public class InfernoCoreConfiguration implements ImportAware {
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

    @Override
    public void setImportMetadata(AnnotationMetadata importMetadata) {
        Map<String, Object> attributes = importMetadata.getAnnotationAttributes(EnableInferno.class.getName());
        if (attributes != null) {
            String[] enabledInfernoProfiles = (String[]) attributes.get("profiles");
            InfernoRuntime.setProfiles(enabledInfernoProfiles);
        }
    }
}
