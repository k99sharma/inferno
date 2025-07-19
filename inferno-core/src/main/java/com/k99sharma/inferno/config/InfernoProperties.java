package com.k99sharma.inferno.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "inferno")
public class InfernoProperties {
    private boolean enabled = true;

    public boolean isEnabled(){
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
