package com.gastonlagaf.executorservice.internal;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class IntRandomizer {
	
	static {
		instance = new IntRandomizer();
	}
	
	private Integer number;
	
	private static IntRandomizer instance;
	
	private Lock lock = new ReentrantLock();
	
	private IntRandomizer() {
		super();
		this.number = (int)(Math.random() * 10);
		System.out.println("Instance initialized with number = " + this.number);
	}
	
	public static IntRandomizer getInstance() {
		return instance;
	}
	
	public Integer incrementNumber(Integer value) {
		lock.lock();
		System.out.println(Thread.currentThread().getName() + ": Got " + this.number + " value");
		this.number += value;
		lock.unlock();
		return number;
	}
	
}
