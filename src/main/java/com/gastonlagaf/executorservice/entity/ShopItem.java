package com.gastonlagaf.executorservice.entity;

public class ShopItem {
	
	private String name;
	
	private Double weight;
	
	private Double price;
	
	public ShopItem() {
		super();
	}

	public ShopItem(String name, Double weight, Double price) {
		super();
		this.name = name;
		this.weight = weight;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ShopItem [name=" + name + "]";
	}
	
}
