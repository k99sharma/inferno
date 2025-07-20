package com.k99sharma.inferno.config;

import com.k99sharma.inferno.aspect.InfernoAspect;
import com.k99sharma.inferno.service.InfernoStats;
import com.k99sharma.inferno.service.ScriptRunner;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        InfernoAspect.class,
        InfernoStats.class,
        ScriptRunner.class
})
@EnableConfigurationProperties({InfernoConfig.class, InfernoProperties.class})
public class InfernoCoreConfiguration {
    @PostConstruct
    public void logInfernoEnabled() {
        System.out.println("🔥 Inferno is ACTIVE");
    }
}
