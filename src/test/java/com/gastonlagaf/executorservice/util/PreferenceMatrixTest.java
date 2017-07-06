package com.gastonlagaf.executorservice.util;

import org.junit.Before;
import org.junit.Test;

import com.gastonlagaf.executorservice.entity.ShopItem;

import static org.junit.Assert.*;

import java.util.List;

public class PreferenceMatrixTest {
	
	PreferenceMatrix<ShopItem> preferenceMatrix;
	
	ShopItem item1 = new ShopItem("Fan", 1d, 700d);
	ShopItem item2 = new ShopItem("Iron", 1.3, 850d);
	ShopItem item3 = new ShopItem("Coffemachine", 3d, 1700d);
	ShopItem item4 = new ShopItem("Boiler", 2d, 1150d);
	ShopItem item5 = new ShopItem("Monitor", 5d, 1450d);
	
	@Before
	public void setUp() {
		preferenceMatrix = new PreferenceMatrix<>(0.2, 6.0, true);
	}
	
	@Test
	public void testAddition() {
		Boolean result = preferenceMatrix.add(item1, item1.getWeight(), item1.getPrice());
		assertTrue(result);
	}
	
	@Test
	public void testOptimumAlternative() {
 		preferenceMatrix.add(item1, item1.getWeight(), item1.getPrice());
		preferenceMatrix.add(item2, item2.getWeight(), item2.getPrice());
		preferenceMatrix.add(item3, item3.getWeight(), item3.getPrice());
		preferenceMatrix.add(item4, item4.getWeight(), item4.getPrice());
		preferenceMatrix.add(item5, item5.getWeight(), item5.getPrice());
		
		List<ShopItem> result = preferenceMatrix.selectOptimumResultForUnitValue(6.0);
		
		assertTrue(result.contains(item3) && result.contains(item4));
	}
	
}
