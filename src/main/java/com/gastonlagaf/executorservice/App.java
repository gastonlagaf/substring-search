package com.gastonlagaf.executorservice;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.gastonlagaf.executorservice.api.GraphWalker;
import com.gastonlagaf.executorservice.entity.GraphNode;
import com.gastonlagaf.executorservice.internal.DefaultGraphWalker;

public class App {
	
	public static void main(String[] args) {
		Set<GraphNode> graph = new HashSet<>();
		
		GraphWalker walker = new DefaultGraphWalker();
		
		GraphNode node1 = new GraphNode();
		GraphNode node2 = new GraphNode();
		GraphNode node3 = new GraphNode();
		GraphNode node4 = new GraphNode();
		GraphNode node5 = new GraphNode();
		
		node1.setName("mimi");
		node2.setName("baby");
		node3.setName("nani");
		node4.setName("papi");
		node5.setName("zazi");
		
		node1.getNeighbours().put(node2, 5);
		node2.getNeighbours().put(node3, 7);
		node3.getNeighbours().put(node4, 1);
		node3.getNeighbours().put(node5, 6);
		node4.getNeighbours().put(node5, 4);
		
		graph.addAll(Arrays.asList(node1, node2, node3, node4, node5));
		
		walker.definePath(graph);
	}

}
