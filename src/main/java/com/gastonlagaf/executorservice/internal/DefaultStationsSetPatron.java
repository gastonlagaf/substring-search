package com.gastonlagaf.executorservice.internal;

import java.util.HashSet;
import java.util.Set;

import com.gastonlagaf.executorservice.api.StationsSetPatron;
import com.gastonlagaf.executorservice.entity.RadioStation;

public class DefaultStationsSetPatron implements StationsSetPatron {
	
	@Override
	public Set<RadioStation> defineStations(Set<RadioStation> stations, Set<String> states) {
		Set<RadioStation> result = new HashSet<>();
		Set<String> statesNeeded = new HashSet<>(states);
		while(!statesNeeded.isEmpty()) {
			RadioStation bestStation = null;
			Set<String> coveredStates = new HashSet<>();
			for(RadioStation station : stations) {
				Set<String> stationCoveredStates = new HashSet<>(statesNeeded);
				stationCoveredStates.retainAll(station.getStatesCovered());
				if(stationCoveredStates.size() > coveredStates.size()) {
					bestStation = station;
					coveredStates = bestStation.getStatesCovered();
				}
			}
			result.add(bestStation);
			statesNeeded.removeAll(bestStation.getStatesCovered());
			coveredStates.clear();
		}
		return result;
	}
	
}
