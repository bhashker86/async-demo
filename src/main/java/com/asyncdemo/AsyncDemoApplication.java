package com.asyncdemo;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.asyncdemo.service.AsyncService;

@SpringBootApplication
public class AsyncDemoApplication  implements CommandLineRunner {
	
	@Autowired
	private AsyncService asyncService;

	public static void main(String[] args) {
		SpringApplication.run(AsyncDemoApplication.class, args);
	}

	@Override
    public void run(String... args) {
        System.out.println("Main thread executing...");

        // Calling async method
        CompletableFuture<String> futureResult = asyncService.getDataAsync()
                .thenApply(result -> {
                    System.out.println("Async Task Success! Result: " + result);
                    return result;
                })
                .exceptionally(ex -> {
                    System.err.println("Async Task Failed: " + ex.getMessage());
                    return "Default Response (due to error)";
                });

        // Main thread continues executing
        System.out.println("Main thread continues...");
        System.out.println("Main thread is free to do other tasks...");

        // Blocking main thread until async task completes 
        futureResult.join();
    }
}
