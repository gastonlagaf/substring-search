package com.gastonlagaf.executorservice;

import java.util.Arrays;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;

import com.gastonlagaf.executorservice.api.SubstringDetector;
import com.gastonlagaf.executorservice.internal.DefaultSubstringDetector;

public class App {

	public static final CyclicBarrier BARRIER = new CyclicBarrier(3, new Runnable() {
		public void run() {
			System.out.println("3 Calculators arrived");
		}
	});
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		SubstringDetector s = new DefaultSubstringDetector();
		System.out.println(s.detectSubstringBM("abcdddabdddaabcddbcaddda", "ddda"));
		System.out.println(Arrays.toString(s.detectSubstringKMP("abcdddabdddaabcddbcaddda", "ddda")));
		System.out.println(Arrays.toString(s.detectSubstringLinear("abcdddabdddaabcddbcaddda", "ddda")));
	}

}
