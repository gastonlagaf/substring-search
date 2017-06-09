package com.gastonlagaf.executorservice.api;

import java.util.Map;

@FunctionalInterface
public interface CharOccurenceStreakCounter {
	
	public Map<Character, Integer> calculateOccurence(String str);
	
}
