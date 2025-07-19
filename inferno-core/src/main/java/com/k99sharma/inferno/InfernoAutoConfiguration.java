package com.k99sharma.inferno;

import com.k99sharma.inferno.aspect.InfernoAspect;
import com.k99sharma.inferno.config.InfernoConfig;
import com.k99sharma.inferno.config.InfernoProperties;
import com.k99sharma.inferno.service.InfernoStats;
import com.k99sharma.inferno.service.ScriptRunner;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

@AutoConfiguration
@Import({
        InfernoAspect.class,
        InfernoStats.class,
        ScriptRunner.class
})
@EnableConfigurationProperties({InfernoConfig.class, InfernoProperties.class})
public class InfernoAutoConfiguration {}
