package com.big.tuwien.SmartMatcher.strategy.pso;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.big.tuwien.SmartMatcher.strategy.pso.Tuples.T1;

/**
 * Context1.
 * @author alex
 *
 * @param <E>
 */
public class C1<E extends Correspondence> extends T1<E> implements Context {
	public final E op1;

	public static <T extends Correspondence> C1<T> c(T op) {
		return new C1<T>(op);
	}


	public C1(E op1) {
		super(op1);
		this.op1 = op1;
	}


	@SuppressWarnings("unchecked")
	public List<E> toList() {
		return Arrays.<E>asList(e0);
	}


	@Override
	public List<Correspondence> asList() {
		return Arrays.<Correspondence>asList((Correspondence) op1);
	}


	@Override
	public boolean contains(Correspondence c) {
		return asList().contains(c);
	}
	
	
	public <T extends Correspondence> Set<T> descendents(Class<T> ctype) {
		return op1.descendents(ctype);
	}
	
	
	@Override
	public boolean equals(Object other) {
		if(this == other) return true;
		
		if(other == null || !(other instanceof C1<?>))
			return false;
		
		C1<?> that = (C1<?>) other;
		return op1.equals(that.op1);
	}
	
	
	@Override
	public int hashCode() {
		return (133 * C1.class.hashCode()) 
					+ (173 * op1.hashCode());
	}
	
	
	@Override
	public boolean flatEquals(Context other) {
		if(this == other) return true;
		
		if(other == null || !(other instanceof C1<?>))
			return false;
		
		C1<?> that = (C1<?>) other;
		return op1.flatEquals(that.op1);
	}


	public String toString() {
		return "C1(" + op1 + ")";
	}
}

