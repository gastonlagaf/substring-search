package com.gastonlagaf.executorservice.api;

import java.util.Set;

import com.gastonlagaf.executorservice.entity.GraphNode;

public interface GraphWalker {
	
	Set<GraphNode> definePath(Set<GraphNode> graph);
	
}
