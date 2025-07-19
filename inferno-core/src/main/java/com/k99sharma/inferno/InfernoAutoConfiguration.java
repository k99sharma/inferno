package com.k99sharma.inferno;

import com.k99sharma.inferno.aspect.InfernoAspect;
import com.k99sharma.inferno.config.InfernoConfig;
import com.k99sharma.inferno.config.InfernoProperties;
import com.k99sharma.inferno.service.InfernoStats;
import com.k99sharma.inferno.service.ScriptRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        InfernoAspect.class,
        InfernoStats.class,
        InfernoConfig.class,
        InfernoProperties.class,
        ScriptRunner.class
})
public class InfernoAutoConfiguration {
}
