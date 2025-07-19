package com.k99sharma.inferno.annotations;

import com.k99sharma.inferno.model.FailureMode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectInferno {
    FailureMode mode() default FailureMode.LATENCY;
    double rate() default 0.3;
    int latencyMs() default 1000;
}
