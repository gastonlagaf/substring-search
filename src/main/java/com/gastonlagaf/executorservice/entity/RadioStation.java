package com.gastonlagaf.executorservice.entity;

import java.util.Set;

public class RadioStation {
	
	private String name;
	
	private Set<String> statesCovered;

	public RadioStation() {
		super();
	}

	public RadioStation(String name, Set<String> statesCovered) {
		super();
		this.name = name;
		this.statesCovered = statesCovered;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<String> getStatesCovered() {
		return statesCovered;
	}

	public void setStatesCovered(Set<String> statesCovered) {
		this.statesCovered = statesCovered;
	}

	@Override
	public String toString() {
		return "RadioStation [name=" + name + "]";
	}
}
