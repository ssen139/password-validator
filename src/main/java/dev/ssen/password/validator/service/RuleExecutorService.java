package dev.ssen.password.validator.service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import dev.ssen.password.validator.util.PropertyReader;

public class RuleExecutorService{

	private ExecutorService service;
	
	protected void executeInParallel(List<Runnable> tasks) {
		try {
			service = Executors.newFixedThreadPool(getConcurrentThreadCount());
			tasks.forEach(task -> service.submit(task));
			awaitTerminationAfterShutdown(service);
		} catch (InterruptedException e) {
			service.shutdownNow();
			Thread.currentThread().interrupt();
		}
	}
	
	private void awaitTerminationAfterShutdown(ExecutorService threadPool) throws InterruptedException {
	    threadPool.shutdown();
	    if (!threadPool.awaitTermination(getThreadTimeOutInSecs(), TimeUnit.SECONDS)) {
            threadPool.shutdownNow();
        }
	    
	}

	private int getConcurrentThreadCount() {
		return Integer.valueOf(PropertyReader.getInstance().getProperty("concurrentThreadCount").orElse("5"));
	}
	
	private int getThreadTimeOutInSecs() {
		return Integer.valueOf(PropertyReader.getInstance().getProperty("threadTimeOutInSecs").orElse("3"));
	}

}
