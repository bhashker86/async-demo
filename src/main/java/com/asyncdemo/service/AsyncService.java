package com.asyncdemo.service;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {

	 private static final Logger logger = LoggerFactory.getLogger(AsyncService.class);

	    @Async("customExecutor")
	    public CompletableFuture<String> getDataAsync() {
	        try {
	            logger.info("Async method started in thread: {}", Thread.currentThread().getName());
	            Thread.sleep(6000); // Simulate delay (6 sec)

	            if (Math.random() > 0.7) { // Simulate an occasional error
	                throw new RuntimeException(" Error in Async Task!");
	            }

	            return CompletableFuture.completedFuture("Hello from Async Method!");

	        } catch (InterruptedException e) {
	            logger.error("Error in async method", e);
	            return CompletableFuture.failedFuture(e);
	        }
	    }
}
