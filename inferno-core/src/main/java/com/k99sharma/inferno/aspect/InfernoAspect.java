package com.k99sharma.inferno.aspect;

import com.k99sharma.inferno.annotations.InjectInferno;
import com.k99sharma.inferno.config.InfernoConfig;
import com.k99sharma.inferno.config.InfernoProperties;
import com.k99sharma.inferno.service.InfernoStats;
import com.k99sharma.inferno.model.FailureMode;
import com.k99sharma.inferno.strategy.ChaosRegistry;
import com.k99sharma.inferno.strategy.ChaosStrategy;
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

    @Autowired
    private ChaosRegistry chaosRegistry;

    @Around("@annotation(inferno)")
    public Object injectChaos(ProceedingJoinPoint joinPoint, InjectInferno inferno) throws Throwable {
        // disable chaos globally
        if(!props.isEnabled())
            return joinPoint.proceed();

        infernoStats.recordRequest();

        FailureMode mode = inferno.mode();
        double rate = inferno.rate();

        // if AUTO -> get from config
        if(mode == FailureMode.AUTO){
            mode = config.getMode();
            rate = config.getRate();
        }

        if(random.nextDouble() < rate) {
            infernoStats.recordInjection(mode);

            ChaosStrategy strategy = chaosRegistry.get(mode);
            strategy.execute();
        }

        return joinPoint.proceed();
    }
}
