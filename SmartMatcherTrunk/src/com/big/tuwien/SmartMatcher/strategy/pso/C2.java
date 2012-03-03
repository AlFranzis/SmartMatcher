package com.big.tuwien.SmartMatcher.strategy.pso;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.big.tuwien.SmartMatcher.strategy.pso.Tuples.T2;

/**
 * Context2.
 * @author alex
 *
 * @param <E>
 */
public class C2<E extends Correspondence> extends T2<E, E> implements Context {
	public final E op1;
	public final E op2;

	public static <T extends Correspondence> C2<T> c(T op1, T op2) {
		return new C2<T>(op1, op2);
	}

	
	public static <T extends Correspondence> C2<T> c(T2<T,T> t2) {
		return new C2<T>(t2.e0, t2.e1);
	}


	public C2(E op1, E op2) {
		super(op1, op2);
		this.op1 = op1;
		this.op2 = op2;
	}


	@SuppressWarnings("unchecked")
	public List<E> toList() {
		return Arrays.<E>asList(e0, e1);
	}


	@Override
	public List<Correspondence> asList() {
		return Arrays.<Correspondence>asList((Correspondence) op1, (Correspondence) op2);
	}


	@Override
	public boolean contains(Correspondence c) {
		return asList().contains(c);
	}
	
	
	public <T extends Correspondence> Set<T> descendents(Class<T> ctype) {
		Set<T> cs1 = new HashSet<T>();
		for(T c : op1.descendents(ctype)) {
			if(equals(c.getContext()))
				cs1.add(c);
		}
		
		// think: there is really no need to look at descendents of op2! 
		
		return Collections.unmodifiableSet(cs1);
	}
	
	
	@Override
	public boolean equals(Object other) {
		if(this == other) return true;
		
		if(other == null || !(other instanceof C2<?>))
			return false;
		
		C2<?> that = (C2<?>) other;
		// context is agnostic to order of it's C2Cs!
		return (op1.equals(that.op1) 
				&& op2.equals(that.op2)) 
				|| (op1.equals(that.op2) 
						&& op2.equals(that.op1));
	}
	

	@Override
	public int hashCode() {
		return (251 * C2.class.hashCode()) 
					+ (67 * op1.hashCode())
					+ (67 * op2.hashCode());
	}
	
	
	@Override
	public boolean flatEquals(Context other) {
		if(this == other) return true;
		
		if(other == null || !(other instanceof C2<?>))
			return false;
		
		C2<?> that = (C2<?>) other;
		// context is agnostic to order of it's C2Cs!
		return (op1.flatEquals(that.op1) 
				&& op2.flatEquals(that.op2)) 
				|| (op1.flatEquals(that.op2) 
						&& op2.flatEquals(that.op1));
	}


	public String toString() {
		return "C2(" + op1 + "," + op2 + ")";
	}

}