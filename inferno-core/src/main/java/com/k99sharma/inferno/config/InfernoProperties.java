package com.k99sharma.inferno.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@ConfigurationProperties(prefix = "inferno")
public class InfernoProperties {
    private Boolean enabled = true;

    public Boolean isEnabled() {
        return enabled != null && enabled;
    }
}
