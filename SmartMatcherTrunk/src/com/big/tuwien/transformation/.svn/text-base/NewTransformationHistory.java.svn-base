package com.big.tuwien.transformation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.eclipse.emf.ecore.EObject;

import com.big.tuwien.ModelManager.imodel.ClassInstance;
import com.big.tuwien.SmartMatcher.operators.Configuration;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CBubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CRole;


public class NewTransformationHistory {
	private Map<C2CBubble, Map<C2CRole, Set<EObject>>> history = 
								new HashMap<C2CBubble, Map<C2CRole,Set<EObject>>>();
	private List<Configuration<?>> configurations = new Vector<Configuration<?>>();
	private List<Entry<EObject>> entries = new Vector<Entry<EObject>>();
	private List<Entry<Object>> valueEntries = new Vector<Entry<Object>>();
	private List<Entry<EObject[]>> linkEntries = new Vector<Entry<EObject[]>>();
	
	private List<Entry<ClassInstance>> classEntries = new Vector<Entry<ClassInstance>>();
	
	
	public void addTransformedBubble(C2CBubble b, Map<C2CRole, Set<EObject>> tMap) {
		history.put(b, tMap);
	}
	
	
	public boolean transformed(C2CBubble b) {
		return history.containsKey(b);
	}
	
	
	public Map<C2CRole, Set<EObject>> getTransformationMap(C2CBubble b) {
		return history.get(b);
	}
	
	
	public void addTransformedConfiguration(Configuration<?> config) {
		this.configurations.add(config);
	}
	
	
	public boolean isTransformed(Configuration<?> config) {
		return this.configurations.contains(config);
	}
	
	
	public boolean isTransformed(EObject lhsObject) {
		return getEntry(lhsObject) != null;
	}
	
	
	public boolean isTransformed(ClassInstance lhsInstance) {
		return getEntry(lhsInstance) != null;
	}
	
	
	public boolean isTransformed(Object lhsObject) {
		return getEntry(lhsObject) != null;
	}
	
	
	public Entry<ClassInstance> getEntry(ClassInstance lhsInstance) {
		for(Entry<ClassInstance> entry : this.classEntries) {
			for(ClassInstance historyObject : entry.getLHSHistoryElements()) {
				if(historyObject.equals(lhsInstance)) {
					return entry;
				}
			}
		}
		return null;
	}
	
	public Entry<ClassInstance> getEntry(EObject lhsElement) {
		for(Entry<ClassInstance> entry : this.classEntries) {
			for(ClassInstance historyObject : entry.getLHSHistoryElements()) {
				if(historyObject.getInstance().equals(lhsElement)) {
					return entry;
				}
			}
		}
		return null;
	}
	
	
	public Entry<Object> getEntry(Object lhsElement) {
		for(Entry<Object> entry : this.valueEntries) {
			for(Object historyObject : entry.getLHSHistoryElements()) {
				if(historyObject.equals(lhsElement)) {
					return entry;
				}
			}
		}
		return null;
	}
	
	
	public Entry<EObject[]> getEntry(EObject[] lhsElement) {
		for(Entry<EObject[]> entry : this.linkEntries) {
			for(EObject[] historyObject : entry.getLHSHistoryElements()) {
				if(historyObject.equals(lhsElement)) {
					return entry;
				}
			}
		}
		return null;
	}
	
	
	public void trace(List<EObject> lhs, List<EObject> rhs){
		this.entries.add(new Entry<EObject>(lhs, rhs));
	}
	
	
	public void trace2(List<ClassInstance> lhs, List<ClassInstance> rhs){
		this.classEntries.add(new Entry<ClassInstance>(lhs, rhs));
	}
	
	
	public void traceValues(List<Object> lhs, List<Object> rhs){
		this.valueEntries.add(new Entry<Object>(lhs, rhs));
	}
	
	
	public void traceLink(List<EObject[]> lhs, List<EObject[]> rhs){
		this.linkEntries.add(new Entry<EObject[]>(lhs, rhs));
	}
	
	
	public void trace(EObject l,EObject r){
		Vector<EObject> lHS = new Vector<EObject>();
		lHS.add(l);
		Vector<EObject> rHS = new Vector<EObject>();
		rHS.add(r);
		this.trace(lHS, rHS);
	}
	
	
	public void trace2(ClassInstance l, ClassInstance r) {
		Vector<ClassInstance> lHS = new Vector<ClassInstance>();
		lHS.add(l);
		Vector<ClassInstance> rHS = new Vector<ClassInstance>();
		rHS.add(r);
		this.trace2(lHS, rHS);
	}
	
	public void traceValues(Object l,Object r){
		Vector<Object> lHS = new Vector<Object>();
		lHS.add(l);
		Vector<Object> rHS = new Vector<Object>();
		rHS.add(r);
		this.traceValues(lHS, rHS);
	}
	
	
	public void traceLink(EObject[] l,EObject[] r){
		Vector<EObject[]> lHS = new Vector<EObject[]>();
		lHS.add(l);
		Vector<EObject[]> rHS = new Vector<EObject[]>();
		rHS.add(r);
		this.traceValues(lHS, rHS);
	}
	
	
	public void clear() {
		this.configurations.clear();
		this.entries.clear();
		this.history.clear();
		this.linkEntries.clear();
		this.valueEntries.clear();
		this.classEntries.clear();
	}
	
	
	public class Entry<T> {
		private List<T> lhsHistoryElements;
		private List<T> rhsHistoryElements;

		
		public Entry(List<T> lHS, List<T> rHS){
			this.setLHSHistoryElements(lHS);
			this.setRHSHistoryElements(rHS);
		}

		
		public List<T> getLHSHistoryElements(){
			return lhsHistoryElements;
		}

		
		protected void setLHSHistoryElements(List<T> newVal){
			lhsHistoryElements = newVal;
		}

		
		public List<T> getRHSHistoryElements(){
			return rhsHistoryElements;
		}

		
		protected void setRHSHistoryElements(List<T> newVal){
			rhsHistoryElements = newVal;
		}
	}
}
