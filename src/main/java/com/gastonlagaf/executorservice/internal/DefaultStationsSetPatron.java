package com.gastonlagaf.executorservice.internal;

import java.util.HashSet;
import java.util.Set;

import com.gastonlagaf.executorservice.api.StationsSetPatron;
import com.gastonlagaf.executorservice.entity.RadioStation;

public class DefaultStationsSetPatron implements StationsSetPatron {
	
	@Override
	public Set<String> defineStations(Set<RadioStation> stations, Set<String> states) {
		Set<String> statesNeeded = new HashSet<>(states);
		while(!statesNeeded.isEmpty()) {
			RadioStation bestStation = null;
			for(RadioStation station : stations) {
				if(station.getStatesCovered().size() > bestStation.getStatesCovered().size() || bestStation == null) {
					bestStation = station;
				}
			}
		}
		return null;
	}
	
}
