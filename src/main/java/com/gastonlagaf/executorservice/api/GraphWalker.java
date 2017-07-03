package com.gastonlagaf.executorservice.api;

import java.util.List;
import java.util.Set;

import com.gastonlagaf.executorservice.entity.GraphNode;

public interface GraphWalker {
	
	List<GraphNode> definePath(Set<GraphNode> costs, GraphNode from, GraphNode to);
	
}
