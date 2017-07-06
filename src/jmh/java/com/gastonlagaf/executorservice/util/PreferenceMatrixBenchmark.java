package com.gastonlagaf.executorservice.util;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.gastonlagaf.executorservice.entity.ShopItem;

public class PreferenceMatrixBenchmark {

	@State(Scope.Thread)
	public static class PreferenceMatrixProvider {
		private PreferenceMatrix<ShopItem> preferenceMatrix = new PreferenceMatrix<>(0.2, 6.0, true);
	}
	
	@State(Scope.Benchmark)
	public static class ItemsProvider {
		private ShopItem item1 = new ShopItem("Fan", 1d, 700d);
	}
	
	@Benchmark
	@BenchmarkMode(Mode.Throughput)
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void measureItemsAddition(ItemsProvider ip, PreferenceMatrixProvider pmp) {
		pmp.preferenceMatrix.add(ip.item1, ip.item1.getWeight(), ip.item1.getPrice());
	}
	
	//@Benchmark
	@BenchmarkMode(Mode.Throughput)
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void measureOptimumItemsDetermination(PreferenceMatrixProvider pmp) {
		pmp.preferenceMatrix.selectOptimumResultForUnitValue(6.0);
	}
	
}
