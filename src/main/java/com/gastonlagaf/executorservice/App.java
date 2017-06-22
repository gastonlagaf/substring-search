package com.gastonlagaf.executorservice;

import java.util.Arrays;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;

import com.gastonlagaf.executorservice.api.Sorter;
import com.gastonlagaf.executorservice.api.SubstringDetector;
import com.gastonlagaf.executorservice.internal.DefaultSorter;
import com.gastonlagaf.executorservice.internal.DefaultSubstringDetector;

public class App {

	public static final CyclicBarrier BARRIER = new CyclicBarrier(3, new Runnable() {
		public void run() {
			System.out.println("3 Calculators arrived");
		}
	});
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Sorter sorter = new DefaultSorter();
		System.out.println(Arrays.toString(sorter.fastSort(new int[] { 9, 4, 5, 3, 6, 1, 7, 2, 8 })));
	}

}
