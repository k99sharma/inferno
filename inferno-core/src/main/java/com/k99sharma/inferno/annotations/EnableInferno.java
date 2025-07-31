package com.k99sharma.inferno.annotations;

import com.k99sharma.inferno.config.InfernoCoreConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(InfernoCoreConfiguration.class)
public @interface EnableInferno {
    String[] profiles() default {};
}
