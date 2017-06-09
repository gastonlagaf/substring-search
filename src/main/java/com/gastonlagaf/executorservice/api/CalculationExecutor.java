package com.gastonlagaf.executorservice.api;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CalculationExecutor {

	private ExecutorService executorService;
	
	private Class<?> taskClass;
	
	public CalculationExecutor(int poolCapacity) {
		this.executorService = Executors.newFixedThreadPool(poolCapacity);
	}
	
	public CalculationExecutor withTask(Class<? extends Callable<Integer>> clazz) {
		for(Class<?> implInterface : clazz.getInterfaces()) {
			if("Callable".equals(implInterface.getSimpleName())) {
				this.taskClass = clazz;
			}
		}
		return this;
	}
	
	public CalculationExecutor withRunnable(Class<? extends Runnable> clazz) {
		for(Class<?> implInterface : clazz.getInterfaces()) {
			if("Runnable".equals(implInterface.getSimpleName())) {
				this.taskClass = clazz;
			}
		}
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public Future<Integer> submit() {
		Callable<Integer> task = null;
		try {
			task = Callable.class.cast(taskClass.getConstructor().newInstance());
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return executorService.submit(task);
	}
	
	public void shutdown() {
		try {
			executorService.shutdown();
			executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
