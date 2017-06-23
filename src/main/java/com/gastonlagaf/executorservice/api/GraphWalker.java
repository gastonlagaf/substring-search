package com.gastonlagaf.executorservice.api;

import java.util.List;
import java.util.Map;

public interface GraphWalker<T> {
	
	List<T> definePath(Map<T, List<T>> graph);
	
}
