package com.gastonlagaf.executorservice.internal;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;

import com.gastonlagaf.executorservice.App;

public class CalculatorOfFaith implements Callable<Integer> {
	
	@Override
	public Integer call() {
		System.out.println(Thread.currentThread().getName() + ": Engaged");
		IntRandomizer numberSupplier = IntRandomizer.getInstance();
		try {
			App.BARRIER.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
		Integer incrementValue = (int)(Math.random() * 100);
		return numberSupplier.incrementNumber(incrementValue);
	}
	
}
