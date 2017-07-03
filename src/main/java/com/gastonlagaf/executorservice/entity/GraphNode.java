package com.gastonlagaf.executorservice.entity;

import java.util.HashMap;
import java.util.Map;

public class GraphNode {
	
	private String name;
	
	private Map<GraphNode, Integer> neighbours = new HashMap<>();

	public GraphNode() {
		super();
	}

	public GraphNode(String name, Map<GraphNode, Integer> neighbours) {
		super();
		this.name = name;
		this.neighbours = neighbours;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<GraphNode, Integer> getNeighbours() {
		return neighbours;
	}

	public void setNeighbours(Map<GraphNode, Integer> neighbours) {
		this.neighbours = neighbours;
	}

	@Override
	public String toString() {
		return "GraphNode [name=" + name + "]";
	}
	
}