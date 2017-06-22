package com.gastonlagaf.executorservice.internal;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.gastonlagaf.executorservice.api.SubstringDetector;

public class SubstringDetectorBenchmark {

	@State(Scope.Benchmark)
	public static class SubstringDetectorProvider {
		private SubstringDetector sd = new DefaultSubstringDetector();
		private String givenText;
		
		{
			InputStream is = getClass().getClassLoader().getResourceAsStream("CHANGELOG.txt");
			this.givenText = new BufferedReader(new InputStreamReader(is))
					  .lines().collect(Collectors.joining("\n"));
		}
	}
	
	@Benchmark
	@BenchmarkMode(Mode.Throughput)
	@OutputTimeUnit(TimeUnit.NANOSECONDS)
	public void measureAverageTimeKMP(SubstringDetectorProvider sdp) {
		sdp.sd.detectSubstringKMP(sdp.givenText, "issue");
	}
	
	@Benchmark
	@BenchmarkMode(Mode.Throughput)
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void measureAverageTimeOfLinearAlgorithm(SubstringDetectorProvider sdp) {
		sdp.sd.detectSubstringLinear(sdp.givenText, "issue");
	}
	
	@Benchmark
	@BenchmarkMode(Mode.Throughput)
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void measureAverageTimeOfBowerMourAlgorithm(SubstringDetectorProvider sdp) {
		sdp.sd.detectSubstringBM(sdp.givenText, "issue");
	}
	
}
