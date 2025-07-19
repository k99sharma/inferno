package com.k99sharma.inferno.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.k99sharma.inferno.config.InfernoConfig;
import com.k99sharma.inferno.model.ChaosScript;
import com.k99sharma.inferno.model.ChaosStep;
import com.k99sharma.inferno.model.FailureMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class ScriptRunner implements Runnable{
    private static final Logger logger = LoggerFactory.getLogger(ScriptRunner.class);

    private final InfernoConfig config;
    private final AtomicBoolean running = new AtomicBoolean(false);

    public ScriptRunner(InfernoConfig infernoConfig){
        config = infernoConfig;
    }

    @Override
    public void run() {
        if(running.get())
            return;

        running.set(true);

        try(InputStream input = getClass().getClassLoader().getResourceAsStream("chaos-stream.yml")){
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            ChaosScript script = mapper.readValue(input, ChaosScript.class);

            for(ChaosStep step : script.getSequence()) {
                FailureMode mode = step.getMode();

                if(mode == FailureMode.OFF) {
                    config.setMode(FailureMode.LATENCY);
                    config.setRate(0.0);
                }else{
                    config.setMode(mode);
                    config.setRate(step.getRate());
                    config.setLatencyMs(step.getLatencyMs());
                }

                System.out.println("[InfernoScript] Step: " + mode + " for " + step.getDuration() + "s");
                Thread.sleep(step.getDuration() * 1000L);
            }
        } catch (Exception e){
            logger.error(e.getMessage());
            logger.error(e.toString());
        }finally {
            config.setMode(FailureMode.LATENCY);
            config.setRate(0.0);
            running.set(false);
            System.out.println("✅ Inferno script completed.");
        }
    }
}
