package com.asyncdemo.config;

import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {
	private static final Logger logger = LoggerFactory.getLogger(AsyncConfig.class);

    @Bean(name = "customExecutor")
    public Executor taskExecutor() {
        logger.info("Creating Custom Executor with Pool Size: 3");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(3); // Number of threads
        executor.setMaxPoolSize(5);
        executor.setQueueCapacity(10);
        executor.setThreadNamePrefix("Async-Thread-");
        executor.initialize();
        return executor;
    }

}
