package com.big.tuwien.SmartMatcher.strategy.pso;

import static com.big.tuwien.SmartMatcher.strategy.pso.C1.c;
import static com.big.tuwien.SmartMatcher.strategy.pso.C2.c;

import java.util.List;
import java.util.Vector;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.operators.homogenic.HomogenicConfiguration;
import com.big.tuwien.SmartMatcher.operators.homogenic.a2a.A2A;
import com.big.tuwien.SmartMatcher.operators.homogenic.a2a.A2ABubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.a2a.A2AConfiguration;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2C;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CBubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration;
import com.big.tuwien.SmartMatcher.operators.homogenic.r2r.R2R;
import com.big.tuwien.SmartMatcher.operators.homogenic.r2r.R2RBubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.r2r.R2RConfiguration;
import com.big.tuwien.SmartMatcher.strategy.pso.Tuples.T2;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble;

public class Utils {
	
	public static Bubble<C2C> wrap(PSOC2C c2c) {
		C2CBubble b = new C2CBubble();
		C2CConfiguration c = new C2CConfiguration();
		c.setLHSFocusElement(c2c.lhs);
		c.setRHSFocusElement(c2c.rhs);
		b.setConfiguration(c);
		return b;
	}
	
	
	public static Bubble<R2R> wrap(PSOR2R r2r) {
		R2RBubble b = new R2RBubble();
		R2RConfiguration c = new R2RConfiguration();
		c.setLHSFocusElement(r2r.lhs);
		c.setRHSFocusElement(r2r.rhs);
		T2<PSOC2C,PSOC2C> c2cs = r2r.getContext();
		b.setContext1((C2CBubble) wrap(c2cs.e0));
		b.setContext2((C2CBubble) wrap(c2cs.e1));
		b.setConfiguration(c);
		return b;
	}
	
	
	public static List<Bubble<R2R>> wrap(List<PSOR2R> r2rs) {
		List<Bubble<R2R>> wrapped = new Vector<Bubble<R2R>>();
		for(PSOR2R r2r : r2rs) 
			wrapped.add(wrap(r2r));
		
		return wrapped;
	}
	
	
	public static PSOR2R unwrap(Bubble<C2C> c2c1, Bubble<C2C> c2c2, Bubble<R2R> r2r) {
		Element lhs = ((HomogenicConfiguration<R2R>)r2r.getConfiguration()).getLHSFocusElement();
		Element rhs = ((HomogenicConfiguration<R2R>)r2r.getConfiguration()).getRHSFocusElement();
		PSOR2R unwrapped = new PSOR2R(c(unwrap(c2c1),unwrap(c2c2)), lhs, rhs);
		return unwrapped;
	}
	
	
	public static PSOR2R unwrap(Bubble<R2R> r2r) {
		R2RBubble _a2a = (R2RBubble) r2r;
		R2RConfiguration a2aConfig = (R2RConfiguration) _a2a.getConfiguration();
		Element lhs = a2aConfig.getLHSFocusElement();
		Element rhs = a2aConfig.getRHSFocusElement();
		C2CBubble contextC2C1 = _a2a.getContext1();
		C2CBubble contextC2C2 = _a2a.getContext2();
		PSOR2R unwrapped = new PSOR2R(c(unwrap(contextC2C1), unwrap(contextC2C2)), lhs, rhs);
		return unwrapped;
	}
	
	
	public static PSOA2A unwrap(Bubble<C2C> c2c, Bubble<A2A> a2a) {
		Element lhs = ((HomogenicConfiguration<A2A>)a2a.getConfiguration()).getLHSFocusElement();
		Element rhs = ((HomogenicConfiguration<A2A>)a2a.getConfiguration()).getRHSFocusElement();
		PSOA2A unwrapped = new PSOA2A(c(unwrap(c2c)), lhs, rhs);
		return unwrapped;
	}
	
	
	public static PSOA2A unwrap(Bubble<A2A> a2a) {
		A2ABubble _a2a = (A2ABubble) a2a;
		A2AConfiguration a2aConfig = (A2AConfiguration) _a2a.getConfiguration();
		Element lhs = a2aConfig.getLHSFocusElement();
		Element rhs = a2aConfig.getRHSFocusElement();
		C2CBubble contextC2C = _a2a.getContext();
		PSOA2A unwrapped = new PSOA2A(c(unwrap(contextC2C)), lhs, rhs);
		return unwrapped;
	}
	
	
	//TODO unwrap children ?
	public static PSOC2C unwrap(Bubble<C2C> c2c) {	
		Element lhs = ((HomogenicConfiguration<C2C>)c2c.getConfiguration()).getLHSFocusElement();
		Element rhs = ((HomogenicConfiguration<C2C>)c2c.getConfiguration()).getRHSFocusElement();
		PSOC2C unwrapped = new PSOC2C(null, lhs, rhs);
		return unwrapped;
	}
	
	
	public static List<PSOR2R> unwrap(Bubble<C2C> c2c1, Bubble<C2C> c2c2, List<Bubble<R2R>> r2rs) {
		List<PSOR2R> unwrapped = new Vector<PSOR2R>();
		for(Bubble<R2R> r2r : r2rs)
			unwrapped.add(unwrap(c2c1, c2c2, r2r));
		
		return unwrapped;
	}
	
	
	public static List<PSOR2R> unwrapR2Rs(List<Bubble<R2R>> r2rs) {
		List<PSOR2R> unwrapped = new Vector<PSOR2R>();
		for(Bubble<R2R> r2r : r2rs)
			unwrapped.add(unwrap(r2r));
		
		return unwrapped;
	}
	
	
	public static List<PSOA2A> unwrap(Bubble<C2C> c2c, List<Bubble<A2A>> a2as) {
		List<PSOA2A> unwrapped = new Vector<PSOA2A>();
		for(Bubble<A2A> a2a : a2as)
			unwrapped.add(unwrap(c2c, a2a));
		
		return unwrapped;
	}
	
	
	public static List<PSOA2A> unwrapA2As(List<Bubble<A2A>> a2as) {
		List<PSOA2A> unwrapped = new Vector<PSOA2A>();
		for(Bubble<A2A> a2a : a2as)
			unwrapped.add(unwrap(a2a));
		
		return unwrapped;
	}
}
