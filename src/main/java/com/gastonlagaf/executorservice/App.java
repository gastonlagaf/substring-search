package com.gastonlagaf.executorservice;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import com.gastonlagaf.executorservice.api.GraphWalker;
import com.gastonlagaf.executorservice.api.StationsSetPatron;
import com.gastonlagaf.executorservice.entity.GraphNode;
import com.gastonlagaf.executorservice.entity.RadioStation;
import com.gastonlagaf.executorservice.entity.ShopItem;
import com.gastonlagaf.executorservice.internal.DefaultGraphWalker;
import com.gastonlagaf.executorservice.internal.DefaultStationsSetPatron;
import com.gastonlagaf.executorservice.util.PreferenceMatrix;

public class App {
	
	public static void main(String[] args) {
		dynamicProgrammingTest();
	}
	
	private static void dynamicProgrammingTest() {
		PreferenceMatrix<ShopItem> preferenceMatrix = new PreferenceMatrix<>(0.2, 6.0, true);
		ShopItem item1 = new ShopItem("Fan", 1.3, 900d);
		ShopItem item2 = new ShopItem("Iron", 1d, 850d);
		ShopItem item3 = new ShopItem("Coffemachine", 3d, 1700d);
		ShopItem item4 = new ShopItem("Boiler", 2d, 1150d);
		ShopItem item5 = new ShopItem("Monitor", 5d, 1450d);
		preferenceMatrix.add(item1, item1.getWeight(), item1.getPrice());
		preferenceMatrix.add(item2, item2.getWeight(), item2.getPrice());
		preferenceMatrix.add(item3, item3.getWeight(), item3.getPrice());
		preferenceMatrix.add(item4, item4.getWeight(), item4.getPrice());
		preferenceMatrix.add(item5, item5.getWeight(), item5.getPrice());
		System.out.println(preferenceMatrix.selectOptimumResultForUnitValue(1.3));
	}
	
	private static void defineRadioStationsTest() {
		Set<String> states = new HashSet<>(Arrays.asList("al", "bc", "ce", "wt", "iw", "nm", "ju", "hn", "sb", "qq"));
		RadioStation r1 = new RadioStation("BBC 1", new HashSet<>(Arrays.asList("bc", "wt", "nm", "hn", "qq", "ce", "ju")));
		RadioStation r2 = new RadioStation("BBC 1 Extra", new HashSet<>(Arrays.asList("al", "bc", "wt", "sb")));
		RadioStation r3 = new RadioStation("BBC 2", new HashSet<>(Arrays.asList("ju", "hn", "ju")));
		RadioStation r4 = new RadioStation("BBC News", new HashSet<>(Arrays.asList("qq", "iw", "nm")));
		
		Set<RadioStation> stations = new HashSet<>(Arrays.asList(r1, r2, r3, r4));
		
		StationsSetPatron sSP = new DefaultStationsSetPatron();
		System.out.println(sSP.defineStations(stations, states));
	}
	
	private static void defineGraphPathTest() {
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
