package com.big.tuwien.smartmatcher.strategy.sreuse.fragmentize;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import com.big.tuwien.smartmatcher.strategy.sreuse.Operator;

public class OpDecoratorFactory {
	
	
	public static Set<Operator> decorate(Set<Operator> ops) {
		Map<Operator,OpDecorator> opMap = new HashMap<Operator, OpDecorator>();
		Set<Operator> roots = new CopyOnWriteArraySet<Operator>();
		
		Map<Operator,Set<Operator>> strucMap = structureOps(ops);
		
		for(Operator op : strucMap.keySet()) {
			OpDecorator opDec;
			if(!opMap.containsKey(op)) {
				opDec = deepen(op, opMap, strucMap);
			} else {
				opDec = opMap.get(op);
			}
			
			if(!op.hasParents()) {
				roots.add(opDec);
			}
		}
		
		return roots;
	}
	
	
	/*
	 * Turns the given flat operator (that has no children) into a deep and 
	 * decorated operator with children contained in the strucMap. 
	 */
	private static OpDecorator deepen(Operator op,
								Map<Operator,OpDecorator> opMap, 
								final Map<Operator,Set<Operator>> strucMap) {
		Set<Operator> _children = new CopyOnWriteArraySet<Operator>();
		for(Operator child : strucMap.get(op)) {
			if(opMap.containsKey(child)) {
				_children.add(opMap.get(child));
			} else {
				_children.add(deepen(child, opMap, strucMap));
			}
		}
			
		OpDecorator opDec = new OpDecorator(op, _children);
		opMap.put(op, opDec);
			
		return opDec;
	}
	
	
	
	/*
	 * Structures the given set of flat operators. This means a map is built 
	 * that contains operators as keys and their children as values.
	 * Only children contained in the set are considered as children.  
	 */
	private static Map<Operator,Set<Operator>> structureOps(Set<Operator> ops) {
		Map<Operator,Set<Operator>> strucMap = 
					new HashMap<Operator, Set<Operator>>();
		
		for(Operator o : ops) {
			Set<Operator> children = new CopyOnWriteArraySet<Operator>();
			strucMap.put(o, children);
			
			if(o.hasChildren()) {
				Set<Operator> others = new CopyOnWriteArraySet<Operator>(ops);
				others.remove(o);
				
				for(Operator oo : others) {
					if(o.getChildren().contains(oo))
						children.add(oo);
				}
			}
		}
		
		return strucMap;
	}
}
