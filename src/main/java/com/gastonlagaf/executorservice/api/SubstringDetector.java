package com.gastonlagaf.executorservice.api;

public interface SubstringDetector {

	int[] detectSubstringKMP(String text, String needle);
	
	int detectSubstringBM(String text, String needle);
	
	int[] detectSubstringLinear(String text, String needle);
	
}
