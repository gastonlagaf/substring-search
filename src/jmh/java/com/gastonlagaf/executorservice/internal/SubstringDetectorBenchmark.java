package com.gastonlagaf.executorservice.internal;

import java.util.concurrent.TimeUnit;

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
	}
	
	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.NANOSECONDS)
	public void measureAverageTimeKMP(SubstringDetectorProvider sdp) {
		sdp.sd.detectSubstringKMP("abcdddabdddaabcddbcadddaaaabcbabdbababbbaadbddbddabdabadddabdabababababfbebsbabsdbfaefbawefbddddabadbabefawebgaewfbawebfawefbawefbawefbawefbawefbfaew"
				+ "bfaewbfbafbafawuibfawibfawgiorboaebgfuoauwgpawrgfaubhfpgawghfabaaaabbbdbdbadddbaadababbaaabbbaabbbbabebfpoabupfbabbbaaabbaabdddabbdbaebdddbbbdabibaevvbddbaadddda"
				+ "fafjpoajfpdddapojpfjewpfadaddagfewgrgpojdddbojkpoawgdadddajpajpfajegfaddpojagjpajgawjrgesgsejphddaiojgrepadddddadogihjserghesrgnvarhgaddddafweafergeshlop;lhdddddd", "ddda");
	}
	
	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.NANOSECONDS)
	public void measureAverageTimeOfLinearAlgorithm(SubstringDetectorProvider sdp) {
		sdp.sd.detectSubstringLinear("abcdddabdddaabcddbcadddaaaabcbabdbababbbaadbddbddabdabadddabdabababababfbebsbabsdbfaefbawefbddddabadbabefawebgaewfbawebfawefbawefbawefbawefbawefbfaew"
				+ "bfaewbfbafbafawuibfawibfawgiorboaebgfuoauwgpawrgfaubhfpgawghfabaaaabbbdbdbadddbaadababbaaabbbaabbbbabebfpoabupfbabbbaaabbaabdddabbdbaebdddbbbdabibaevvbddbaadddda"
				+ "fafjpoajfpdddapojpfjewpfadaddagfewgrgpojdddbojkpoawgdadddajpajpfajegfaddpojagjpajgawjrgesgsejphddaiojgrepadddddadogihjserghesrgnvarhgaddddafweafergeshlop;lhdddddd", "ddda");
	}
	
	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.NANOSECONDS)
	public void measureAverageTimeOfBowerMourAlgorithm(SubstringDetectorProvider sdp) {
		sdp.sd.detectSubstringBM("abcdddabdddaabcddbcadddaaaabcbabdbababbbaadbddbddabdabadddabdabababababfbebsbabsdbfaefbawefbddddabadbabefawebgaewfbawebfawefbawefbawefbawefbawefbfaew"
				+ "bfaewbfbafbafawuibfawibfawgiorboaebgfuoauwgpawrgfaubhfpgawghfabaaaabbbdbdbadddbaadababbaaabbbaabbbbabebfpoabupfbabbbaaabbaabdddabbdbaebdddbbbdabibaevvbddbaadddda"
				+ "fafjpoajfpdddapojpfjewpfadaddagfewgrgpojdddbojkpoawgdadddajpajpfajegfaddpojagjpajgawjrgesgsejphddaiojgrepadddddadogihjserghesrgnvarhgaddddafweafergeshlop;lhdddddd", "ddda");
	}
	
}
