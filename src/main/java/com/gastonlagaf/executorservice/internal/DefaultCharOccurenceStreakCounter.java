package com.gastonlagaf.executorservice.internal;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.gastonlagaf.executorservice.api.CharOccurenceStreakCounter;

public class DefaultCharOccurenceStreakCounter implements CharOccurenceStreakCounter {

	@Override
	public Map<Character, Integer> calculateOccurence(final String str) {
		Map<Character, Integer> result = new HashMap<>();
		char[] strToChar = str.toCharArray();
		char iterableChar = 0;
		int occurenceCounter = 0;
		Arrays.sort(strToChar);
		for(int i = 0, len = strToChar.length; i < len + 1; i++) {
			char c = strToChar[i];
			if(iterableChar != c) {
				result.put(iterableChar, occurenceCounter);
				iterableChar = c;
				occurenceCounter = Optional.ofNullable(result.get(c)).orElse(0);
				if(i == --len) { break; }
			}
			occurenceCounter++;
		}
		return result;
	}
	
}
