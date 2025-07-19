package com.k99sharma.inferno.aspect;

import com.k99sharma.inferno.annotations.InjectInferno;
import com.k99sharma.inferno.config.InfernoConfig;
import com.k99sharma.inferno.config.InfernoProperties;
import com.k99sharma.inferno.service.InfernoStats;
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

    @Autowired
    private InfernoStats infernoStats;

    @Autowired
    private InfernoProperties props;

    @Around("@annotation(inferno)")
    public Object injectChaos(ProceedingJoinPoint joinPoint, InjectInferno inferno) throws Throwable {
        // disable chaos globally
        if(!props.isEnabled())
            return joinPoint.proceed();

        infernoStats.recordRequest();

        FailureMode mode = inferno.mode();
        double rate = inferno.rate();
        int latencyMs = inferno.latencyMs();

        // if AUTO -> get from com.k99sharma.inferno.infernoCore.com.k99sharma.inferno.config
        if(mode == FailureMode.AUTO){
            mode = config.getMode();
            rate = config.getRate();
            latencyMs = config.getLatencyMs();
        }

        if(random.nextDouble() < rate) {
            infernoStats.recordInjection(mode);

            switch (mode) {
                case LATENCY -> {
                    Thread.sleep(latencyMs);
                }
                case EXCEPTION -> {
                    throw new RuntimeException("Inferno injected an exception!");
                }
                case TIMEOUT -> {
                    Thread.sleep(10000);
                }
                case CPU_SPIKE -> {
                    long start = System.currentTimeMillis();
                    while(System.currentTimeMillis() - start < 3000){
                        double waste = Math.pow(Math.random(), Math.random());
                    }
                }
                case MEMORY_BOMB -> {
                    try {
                        byte[][] memoryHog = new byte[512][1024 * 1024]; // ~512MB
                        Thread.sleep(1000);
                        memoryHog = null;
                        System.gc(); // try to clean up
                    } catch (OutOfMemoryError e) {
                        System.err.println("Memory bomb triggered OOM (expected)");
                    }
                }
                default -> {}
            }
        }

        return joinPoint.proceed();
    }
}
