package com.k99sharma.inferno;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@AutoConfiguration
@ConditionalOnProperty(prefix = "inferno", name = "enabled", havingValue = "true", matchIfMissing = false)
public class InfernoAutoConfiguration {}
