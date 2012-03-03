package com.big.tuwien.SmartMatcher.util;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class SMProfiler {
	
	private HashMap<String, Long> startTimerList = null; 
	private HashMap<String, Long> stopTimerList = null; 
	private HashMap<String, List<Long>> intervalTimerList = null; 
	private static SMProfiler instance = null;
	
	
	private SMProfiler(){
		this.startTimerList = new HashMap<String, Long>(); 
		this.stopTimerList = new HashMap<String, Long>(); 
		this.intervalTimerList = new HashMap<String, List<Long>>(); 
	}
	
	
	public static SMProfiler getInstance(){
		if(instance == null) {
			instance = new SMProfiler();
		}
		return instance;
	}
	
	
	public boolean startTimer(String name){
		if(! this.startTimerList.containsKey(name)){
			long start = System.currentTimeMillis();
			startTimerList.put(name, start);
			return true;
		}
		
		return false;
	}
	
	
	public boolean stopTimer(String name){
		if(!this.stopTimerList.containsKey(name)) {
			long stop = System.currentTimeMillis();
			startTimerList.put(name, stop);
			return true;
		}		
		return false;
	}
	
	
	public float getTimeFor(String name){
		float time = 0;
		float start = this.startTimerList.get(name);
		float end = this.stopTimerList.get(name);
		time = end-start;
		return time;
	}
	
	
	public void startIntervalTime(String name){
		if(!this.intervalTimerList.containsKey(name)) {
			Vector<Long> timeVactor = new Vector <Long>();
			timeVactor.add(System.currentTimeMillis());
			this.intervalTimerList.put(name, timeVactor);
		} else {
			this.intervalTimerList.get(name).add(System.currentTimeMillis());
		}

	}
	
	
	public boolean stopIntervalTime(String name){
		if(this.intervalTimerList.containsKey(name)) {
			this.intervalTimerList.get(name).add(System.currentTimeMillis());
			return true;
		}
		return false;
	}
	
	
	public long getTimeForInterval(String name, int interval){
		List<Long> v = this.intervalTimerList.get(name);
		if(interval > 0) {
			return v.get(interval-1 ) - v.get(interval);	
		}

		return 0;
	}
	
	
	public long getCompleteTimeForInterval(String name){
		List<Long> v = this.intervalTimerList.get(name);
			int end = v.size();
			if(end >1) {
				return v.get(end-1) - v.get(0);	
			} else {
				return System.currentTimeMillis() - v.get(0);
			}
	}
	
	
	public String intervalTimerToString(String name){
		String result = "";
		
		List<Long> v =this.intervalTimerList.get(name);
		for(int index = 0; index < v.size();index++) {
			if(index+1 < v.size()){ // the last entry is the stop time
				float value =  v.get(index+1) -v.get(index);
				result +=  value +", ";
			}
		}
		
		return result;
	}

	
	public int getNumberOfIntervals(String name) {
		List<Long> v = this.intervalTimerList.get(name);
		if(v != null){
			return v.size()-1; // n entries == n-1 intervals
		}
		return 0;
	}
	
}
