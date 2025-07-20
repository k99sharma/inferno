package com.k99sharma.inferno;

import com.k99sharma.inferno.config.InfernoCoreConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Import;

@AutoConfiguration
@ConditionalOnProperty(prefix = "inferno", name = "enabled", havingValue = "true", matchIfMissing = false)
@Import(InfernoCoreConfiguration.class)
public class InfernoAutoConfiguration {}
