package com.gastonlagaf.executorservice.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PreferenceMatrix<T> {

	private Double rankingFactor;

	private Integer capacity;

	private Double maxUnitValue;

	private Boolean guaranteedUniqueItems = false;

	private List<Column> columns;

	public PreferenceMatrix(Double rankingFactor, Double maxUnitValue) {
		super();
		this.rankingFactor = rankingFactor;
		this.maxUnitValue = maxUnitValue;
		Double capacityCheck = Math.round(this.maxUnitValue / this.rankingFactor * 10) / 10.0;
		if (capacityCheck % 1 != 0) {
			throw new IllegalArgumentException(
					"Unacceptable maximum value. Division of this value to ranking factor should result a decimal number");
		}
		this.capacity = capacityCheck.intValue();
		this.columns = new ArrayList<>(capacity);
		for (int i = 1; i <= capacity; i++) {
			columns.add(new Column(i * rankingFactor));
		}
	}

	public PreferenceMatrix(Double rankingFactor, Double maxUnitValue, Boolean guaranteedUniqueItems) {
		this(rankingFactor, maxUnitValue);
		this.guaranteedUniqueItems = guaranteedUniqueItems;
	}

	@SuppressWarnings("serial")
	public boolean add(T item, Double weight, Double rate) {
		Double approximateWeight = weight;
		if (approximateWeight % rankingFactor != 0) {
			approximateWeight = Math.round(weight / rankingFactor * rankingFactor * 10) / 10.0;
		}
		Item currentItem = new Item(item, approximateWeight, rate);
		for (int i = columns.indexOf(getColumn(weight)); i < capacity; i++) {
			Column column = columns.get(i);
			List<Item> itemSet = new ArrayList<Item>() {{ add(currentItem); }};
			List<Item> optimizedCombination = column.getOptimizedCombination();
			if (optimizedCombination != null) {
				Double optimizedWeight = getSummaryWeight(optimizedCombination);
				Double optimizedRate = getSummaryRate(optimizedCombination);
				if (optimizedWeight + currentItem.weight <= column.unitValue) {
					itemSet.addAll(optimizedCombination);
					column.optimizedKey = currentItem;						
				} else {
					Double capacityLeft = Math.round((column.unitValue - currentItem.weight) * 10) / 10.0;
					List<Item> optimizedRemains = selectOptimumSetForUnitValue(capacityLeft, currentItem);
					if (optimizedRemains != null) {
						itemSet.addAll(optimizedRemains);
						if (getSummaryRate(itemSet) > optimizedRate) {
							column.optimizedKey = currentItem;
						}
					} else if(currentItem.rate > optimizedRate) {
						column.optimizedKey = currentItem;
					} else {
						itemSet = optimizedCombination;
					}
				}
			} else {
				column.optimizedKey = currentItem;
			}
			column.matrixColumn.put(currentItem, itemSet);
		}
		return true;
	}

	public List<T> selectOptimumResultForUnitValue(Double unitValue) {
		List<Item> unitItemsList = getColumn(unitValue).getOptimizedCombination();
		if (unitItemsList == null) {
			return null;
		}
		return getStoredElements(unitItemsList);
	}

	private List<Item> selectOptimumSetForUnitValue(Double unitValue, Item item) {
		if ((Math.round(unitValue * 10) % Math.round(rankingFactor * 10)) / 10.0 != 0.0 || unitValue == 0.0) {
			return null;
		}
		Column columnForUnit = getColumn(unitValue);
		List<Item> result = columnForUnit.getOptimizedCombination();
		if (result != null && guaranteedUniqueItems) {
			if (result.contains(item)) {
				Double maxRate = getSummaryRate(result);
				Double newMax = 0d;
				List<Item> newResult = null;
				for (List<Item> itemList : columnForUnit.matrixColumn.values()) {
					Double currentRate = getSummaryRate(itemList);
					if (currentRate > newMax & currentRate < maxRate) {
						newMax = currentRate;
						newResult = itemList;
					}
				}
				result = newResult;
			}
		}
		return result;
	}

	private Column getColumn(Double unitValue) {
		return columns.get((int) Math.round(unitValue / rankingFactor) - 1);
	}
	
	private Double getSummaryWeight(List<Item> items) {
		Double result = 0d;
		for(Item item : items) {
			result += item.weight;
		}
		return result;
	}
	
	private Double getSummaryRate(List<Item> items) {
		Double result = 0d;
		for(Item item : items) {
			result += item.rate;
		}
		return result;
	}
	
	private List<T> getStoredElements(List<Item> items) {
		List<T> result = new ArrayList<>();
		for(Item item : items) {
			result.add(item.item);
		}
		return result;
	}

	private class Column {

		private Double unitValue;

		private Map<Item, List<Item>> matrixColumn;

		private Item optimizedKey;

		public Column(Double unitValue) {
			super();
			this.unitValue = unitValue;
			this.matrixColumn = new HashMap<>();
		}

		public List<Item> getOptimizedCombination() {
			return matrixColumn.get(optimizedKey);
		}

	}

	private class Item {

		private Double weight;

		private Double rate;

		private T item;

		public Item(T item, Double weight, Double rate) {
			super();
			this.weight = weight;
			this.item = item;
			this.rate = rate;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + PreferenceMatrix.this.hashCode();
			return result;
		}

	}

}
