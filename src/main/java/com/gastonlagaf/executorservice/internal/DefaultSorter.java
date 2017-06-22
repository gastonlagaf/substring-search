package com.gastonlagaf.executorservice.internal;

import java.util.stream.IntStream;

import com.gastonlagaf.executorservice.api.Sorter;

public class DefaultSorter implements Sorter {
	
	@Override
	public int[] fastSort(int[] arr) {
		if(arr.length < 2) {
			return arr;
		} else {
			int pivot = arr[0];
			int[] less = IntStream.of(arr).filter(i -> i < pivot).toArray();
			int[] more = IntStream.of(arr).filter(i -> i > pivot).toArray();
			int[] result = new int[arr.length];
			System.arraycopy(fastSort(less), 0, result, 0, less.length);
			result[less.length] = pivot;
			System.arraycopy(fastSort(more), 0, result, less.length + 1, more.length);
			return result;
		}
	}
	
}
