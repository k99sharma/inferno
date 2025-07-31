package com.k99sharma.inferno.aspect;

import com.k99sharma.inferno.annotations.InjectInferno;
import com.k99sharma.inferno.config.InfernoConfig;
import com.k99sharma.inferno.config.InfernoProperties;
import com.k99sharma.inferno.config.InfernoRuntime;
import com.k99sharma.inferno.model.FailureMode;
import com.k99sharma.inferno.strategy.ChaosRegistry;
import com.k99sharma.inferno.strategy.ChaosStrategy;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Aspect
@Component
public class InjectInfernoAspect {
    private final Random random = new Random();

    @Autowired
    private InfernoConfig config;

    @Autowired
    private InfernoProperties props;

    @Autowired
    private ChaosRegistry chaosRegistry;

    @Around("@annotation(inferno)")
    public Object injectChaos(ProceedingJoinPoint joinPoint, InjectInferno inferno) throws Throwable {
        // disable chaos globally
        if(!(props.isEnabled() || InfernoRuntime.isAnnotationEnabled()))
            return joinPoint.proceed();

        FailureMode mode = inferno.mode();
        double rate = inferno.rate();

        // if AUTO -> get from config
        if(mode == FailureMode.AUTO){
            mode = config.getMode();
            rate = config.getRate();
        }

        // generating random rate between 0 and 1
        double randomRate = ThreadLocalRandom.current().nextDouble();
        if(randomRate < rate) {
            ChaosStrategy strategy = chaosRegistry.get(mode);
            strategy.execute(inferno.latencyMs(), config.getLatencyMs());
        }

        return joinPoint.proceed();
    }
}
