package com.gastonlagaf.executorservice.internal;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.gastonlagaf.executorservice.api.SubstringDetector;

public class DefaultSubstringDetector implements SubstringDetector {

	@Override
	public int[] detectSubstringKMP(String text, String needle) {
		int needleSize = needle.length();
		String sample = needle + "$" + text;
		int[] prefixFunction = calculatePrefixFunction(sample);
		int[] foundIndexes = new int[text.length()];
		for(int i = 0, j = 0; i < prefixFunction.length - needleSize; i++) {
			if(prefixFunction[needleSize + i] == needleSize) {
				foundIndexes[j] = i - needleSize;
				j++;
			}
		}
		return foundIndexes;
	}
	
	@Override
	public int[] detectSubstringBM(String text, String needle) {
		int[] result = new int[text.length()];
		Map<Character, Integer> offsetTable = new HashMap<>(needle.length());
		for(int i = 1; i <= needle.length(); i++) {
			offsetTable.putIfAbsent(needle.charAt(i - 1), i);
		}
		int resultIterable = 0;
		int needleIterable = needle.length() - 1;
		int textIterable = needleIterable;
		while(textIterable < text.length()) {
			int currentBadSymbolIdx = textIterable;
			if(text.charAt(textIterable) == needle.charAt(needleIterable)) {
				int matchIncrementor = 0;
				while(needleIterable >= 0 && text.charAt(textIterable) == needle.charAt(needleIterable)) {
					textIterable--;
					needleIterable--;
					matchIncrementor++;
				}
				if(matchIncrementor == needle.length()) {
					result[resultIterable] = ++textIterable;
					textIterable = currentBadSymbolIdx + 1;
					resultIterable++;
				} else {
					textIterable = currentBadSymbolIdx + Optional.ofNullable(offsetTable.get(text.charAt(currentBadSymbolIdx))).orElse(needle.length());
				}
				needleIterable = needle.length() - 1;
			} else {
				textIterable = currentBadSymbolIdx + Optional.ofNullable(offsetTable.get(text.charAt(currentBadSymbolIdx))).orElse(needle.length());
			}
		}
		return result;
	}
	
	@Override
	public int[] detectSubstringLinear(String text, String needle) {
		char[] textArr = text.toCharArray();
		char[] needleArr = needle.toCharArray();
		int[] result = new int[textArr.length];
		int resCount = 0;
		for(int i = 0; i < textArr.length; i++) {
			if(textArr[i] == needleArr[0]) {
				int k = 0;
				for(int j = 0; j < needleArr.length; j++) {
					if(i + k < textArr.length && textArr[i + k] != needleArr[k]) {
						break;
					}
					k++;
				}
				if(k == needleArr.length) {
					result[resCount] = i;
					resCount++;
				}
			}
		}
		return result;
	}
	
	private int[] calculatePrefixFunction(String sample) {
		char[] mergedSample = sample.toCharArray();
		int[] prefixFunction = new int[mergedSample.length];
		prefixFunction[0] = 0;
		for(int i = 1, j = 0; i < mergedSample.length; i++) {
			j = prefixFunction[i - 1];
			while(mergedSample[i] != mergedSample[j] && j > 0) {
				j = prefixFunction[j - 1];
			}
			if(mergedSample[i] == mergedSample[j]) {
				prefixFunction[i] = ++j;
			} else {
				prefixFunction[i] = 0;
			}
		}
		return prefixFunction;
	}
	
}
