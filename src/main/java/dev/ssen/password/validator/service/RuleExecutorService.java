package dev.ssen.password.validator.service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RuleExecutorService{

	private ExecutorService service;
	
	protected void executeInParallel(List<Runnable> tasks) {
		try {
			service = Executors.newFixedThreadPool(5);
			tasks.forEach(task -> service.submit(task));
			awaitTerminationAfterShutdown(service);
		} catch (InterruptedException e) {
			service.shutdownNow();
			Thread.currentThread().interrupt();
		}
	}
	
	private void awaitTerminationAfterShutdown(ExecutorService threadPool) throws InterruptedException {
	    threadPool.shutdown();
	    if (!threadPool.awaitTermination(3, TimeUnit.SECONDS)) {
            threadPool.shutdownNow();
        }
	    
	}


}
