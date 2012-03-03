package com.big.tuwien.SmartMatcher.operators;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

public class PriorityIterator {
	private Set<Measure> measures = new HashSet<Measure>();
	
	
	public PriorityIterator() {}
	
	
	public PriorityIterator(Collection<Measure> measures) {
		this.measures.addAll(measures);
	}
	
	
	public void addMeasure(Measure m) {
		this.measures.add(m);
	}
	
	
	public void setMeasures(Collection<Measure> measures) {
		this.measures.addAll(measures);
	}
	
	
	private <T extends Configuration<?>> Iterator<T> order(Iterator<T> it) {
		Comparator<T> avg  = new Comparator<T>() {

			public int compare(T c1, T c2) {
				float sum1 = 0, sum2 = 0;
				for(Measure m : measures) {
					sum1 += m.getSimilarity(c1);
					sum2 += m.getSimilarity(c2);
				}
				float avg1 = measures.isEmpty() ? 0 : sum1 / measures.size();
				float avg2 = measures.isEmpty() ? 0 : sum2 / measures.size();
				
				if(avg1 == avg2) return 0;
				else return avg1 < avg2 ? -1 : 1;
			}
		};
		
		List<T> configs = new Vector<T>();
		for(T c = null;it.hasNext();) {
			c = it.next();
			configs.add(c);
		}

		Collections.sort(configs, avg);
		Collections.reverse(configs);
		
		return configs.iterator();
	}
	
	
	public <S extends Configuration<?>> Iterator<S> getIterator(Iterator<S> it) {
		return order(it);
	}
	
	
}
