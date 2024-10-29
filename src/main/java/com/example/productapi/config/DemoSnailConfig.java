package com.example.productapi.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class DemoSnailConfig {

    public static final Duration SNAIL_SLEEP_DURATION = Duration.ZERO;
    Logger log = LoggerFactory.getLogger(getClass());

    record SnailBean() {

    }

    @Bean
    public SnailBean snailBean() throws InterruptedException {
        Thread.sleep(SNAIL_SLEEP_DURATION);
        log.info("Creating SnailBean...");
        Thread.sleep(Duration.ofSeconds(1));
        log.info("Initializing SnailBean...");
        Thread.sleep(Duration.ofSeconds(1));
        log.info("Finalizing SnailBean...");
        return new SnailBean();
    }

}
