package com.gastonlagaf.executorservice.internal;

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
		char[] textArr = text.toCharArray();
		char[] needleArr = needle.toCharArray();
		int[] result = new int[textArr.length];
		int[] offsetTable = new int[255];
		for(int i = 0; i < offsetTable.length; i++) {
			offsetTable[i] = needleArr.length;
		}
		for(int i = needleArr.length; i > 0; i--) {
			offsetTable[needleArr[i - 1]] = i;
		}
		int resultIterable = 0;
		int needleIterable = needleArr.length - 1;
		int textIterable = needleIterable;
		while(textIterable < textArr.length) {
			int curIdx = textIterable;
			while(needleIterable != 0 & textArr[textIterable] == needleArr[needleIterable]) {
				needleIterable--;
				textIterable--;
			}
			if(needleIterable == 0) {
				result[resultIterable++] = textIterable;
			}
			textIterable = curIdx + offsetTable[textArr[curIdx]];
			needleIterable = needleArr.length - 1;
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
