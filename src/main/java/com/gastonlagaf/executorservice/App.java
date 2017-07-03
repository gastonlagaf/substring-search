package com.gastonlagaf.executorservice;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import com.gastonlagaf.executorservice.api.GraphWalker;
import com.gastonlagaf.executorservice.entity.GraphNode;
import com.gastonlagaf.executorservice.internal.DefaultGraphWalker;

public class App {
	
	public static void main(String[] args) {
		Set<GraphNode> graph = new LinkedHashSet<>();
		
		GraphWalker walker = new DefaultGraphWalker();
		
		GraphNode node1 = new GraphNode();
		GraphNode node2 = new GraphNode();
		GraphNode node3 = new GraphNode();
		GraphNode node4 = new GraphNode();
		GraphNode node5 = new GraphNode();
		GraphNode node6 = new GraphNode();
		
		node1.setName("A");
		node2.setName("B");
		node3.setName("C");
		node4.setName("D");
		node5.setName("E");
		node6.setName("F");
		
		node1.getNeighbours().put(node2, 5);
		node2.getNeighbours().put(node3, 7);
		node2.getNeighbours().put(node6, 3);
		node3.getNeighbours().put(node4, 5);
		node3.getNeighbours().put(node5, 6);
		node4.getNeighbours().put(node5, 4);
		node6.getNeighbours().put(node5, 1);
		
		
		graph.addAll(Arrays.asList(node1, node2, node3, node4, node5, node6));
		
		System.out.println(walker.definePath(graph, node1, node5));
	}

}
