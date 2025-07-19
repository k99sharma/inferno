package com.k99sharma.inferno.aspect;

import com.k99sharma.inferno.annotations.InjectInferno;
import com.k99sharma.inferno.config.InfernoConfig;
import com.k99sharma.inferno.model.FailureMode;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Aspect
@Component
public class InfernoAspect {
    private final Random random = new Random();

    @Autowired
    private InfernoConfig config;

    @Around("@annotation(inferno)")
    public Object injectChaos(ProceedingJoinPoint joinPoint, InjectInferno inferno) throws Throwable {
        FailureMode mode = inferno.mode();
        double rate = inferno.rate();
        int latencyMs = inferno.latencyMs();

        // if AUTO -> get from config
        if(mode == FailureMode.AUTO){
            mode = config.getMode();
            rate = config.getRate();
            latencyMs = config.getLatencyMs();
        }

        if(random.nextDouble() < rate) {
            switch (mode) {
                case LATENCY -> {
                    Thread.sleep(latencyMs);
                    break;
                }
                case EXCEPTION -> {
                    throw new RuntimeException("Inferno injected an exception!");
                }
                case TIMEOUT -> {
                    Thread.sleep(10000);
                    break;
                }
                default -> {
                    break;
                }
            }
        }

        return joinPoint.proceed();
    }
}
