package com.gastonlagaf.executorservice.internal;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.gastonlagaf.executorservice.api.GraphWalker;
import com.gastonlagaf.executorservice.entity.GraphNode;

public class DefaultGraphWalker implements GraphWalker {

	@Override
	public List<GraphNode> definePath(Set<GraphNode> graph, GraphNode from, GraphNode to) {
		Map<GraphNode, Integer> costs = new HashMap<>(graph.size());
		Map<GraphNode, GraphNode> parents = new LinkedHashMap<>(graph.size()); 
		for (GraphNode node : graph) {
			Map<GraphNode, Integer> neighbours = node.getNeighbours();
			for (GraphNode neighbour : neighbours.keySet()) {
				parents.putIfAbsent(neighbour, node);
				costs.putIfAbsent(neighbour, neighbours.get(neighbour));
				if (costs.containsKey(neighbour)) {
					Integer cost = costs.getOrDefault(node, 0);
					Integer new_cost = cost + neighbours.get(neighbour);
					if (costs.get(neighbour) > new_cost) {
						costs.put(neighbour, new_cost);
						parents.put(neighbour, node);
					}
				}
			}
		}
		return formPath(parents, from, to);
	}

	private List<GraphNode> formPath(Map<GraphNode, GraphNode> parents, GraphNode from, GraphNode to) {
		List<GraphNode> result = new LinkedList<>();
		result.add(to);
		GraphNode node = to;
		while ((node = parents.get(node)) != from) {
			result.add(node);
		}
		result.add(from);
		Collections.reverse(result);
		return result;
	}

}
