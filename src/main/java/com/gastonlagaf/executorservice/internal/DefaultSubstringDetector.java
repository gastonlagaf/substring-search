package com.gastonlagaf.executorservice.internal;

import java.util.HashMap;
import java.util.Map;

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
	public int detectSubstringBM(String text, String needle) {
		int[] result = new int[text.length()];
		Map<Character, Integer> offsetTable = new HashMap<>(needle.length());
		for(int i = 0, j = needle.length() - 1; i < needle.length() - 1; i++, j--) {
			offsetTable.put(needle.charAt(i), j);
		}
		offsetTable.put(needle.charAt(needle.length() - 1), needle.length());
		int i = needle.length() - 1;
		int j = 0;
		int k = i;
		while(i > 0 && j < text.length()) {
			if(text.charAt(k) == text.charAt(i)) {
				for(int n = i, t = k; n > 0; n--, t--) {
					if(text.charAt(t) == needle.charAt(n)) { break; }
					k--;
					j--;
				}
				i += offsetTable.get(text.charAt(i));
			}
		}
		if (k >= text.length() - needle.length()) {
			return -1;
		} else {
			return k + 1;
		}
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
