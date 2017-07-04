package com.gastonlagaf.executorservice.api;

import java.util.Set;

import com.gastonlagaf.executorservice.entity.RadioStation;

public interface StationsSetPatron {
	
	Set<String> defineStations(Set<RadioStation> stations, Set<String> states); 
	
}
