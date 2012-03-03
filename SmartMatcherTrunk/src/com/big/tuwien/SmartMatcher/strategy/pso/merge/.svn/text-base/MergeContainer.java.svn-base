/**
 * 
 */
package com.big.tuwien.SmartMatcher.strategy.pso.merge;


import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.strategy.pso.C1;
import com.big.tuwien.SmartMatcher.strategy.pso.C2;
import com.big.tuwien.SmartMatcher.strategy.pso.Context;
import com.big.tuwien.SmartMatcher.strategy.pso.Correspondence;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOA2A;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOC2C;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOMapping;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOR2R;
import com.big.tuwien.SmartMatcher.strategy.pso.PSORuntimeException;
import com.big.tuwien.SmartMatcher.strategy.pso.ReplacerUtil.Side;
import com.big.tuwien.SmartMatcher.strategy.pso.Tuples.T2;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class MergeContainer<T extends Correspondence> {
	private static Logger logger = Logger.getLogger(MergeContainer.class);
	
	private Context_ context_;
	
	/**
	 * Map that stores Original->Target bindings
	 */
	protected BiMap<T,T> oClones2tClones = 
					new HashBiMap<T,T>();
	/**
	 * Map that stores binding from original in m1
	 * to their copies in merged
	 */
	protected BiMap<T,T> originals2clones = 
		new HashBiMap<T,T>();
	/**
	 * Map that stores binding from targets in m2
	 * to their copies in merged
	 */
	protected BiMap<T,T> targets2clones = 
		new HashBiMap<T,T>();
	
	
	public static class MContext implements Context_ {
		private PSOMapping m;
		
		public MContext(PSOMapping m) {
			this.m = m;
		}
		
		public void add(Correspondence c) {
			m.add(c);
		}
		
		
		public <T extends Correspondence> T addClone(T c) {
			if(!(c instanceof PSOC2C))
				throw new PSORuntimeException("Unexpected type");
			PSOC2C c2c = (PSOC2C) c;
			PSOC2C c2cClone = new PSOC2C(m, c2c.lhs(), c2c.rhs());
			m.add(c2cClone);
			return (T) c2cClone;
		}
		
		
		public Set<PSOC2C> children() {
			return m.descendents(PSOC2C.class);
		}
		
		
		public <T extends Correspondence> Set<T> descendents(Class<T> ctype) {
			return m.descendents(ctype);
		}
		
		
		public void remove(Correspondence c) {
			m.remove(c);
		}
		
		public int size() {
			return m.descendents(PSOC2C.class).size();
		}
		
		public Context getContext() {
			return m;
		}
	}
	
	
	public static class C1Context implements Context_ {
		private PSOC2C c2c;
		
		public C1Context(C1<PSOC2C> c1) {
			c2c = c1.op1;
		}
		
		public void add(Correspondence c) {
			c2c.add(c);
		}
		
		public <T extends Correspondence> T addClone(T c) {
			if(!(c instanceof PSOA2A))
				throw new PSORuntimeException("Unexpected type");
			PSOA2A a2a = (PSOA2A) c;
			PSOA2A a2aClone = new PSOA2A(C1.c(c2c), a2a.lhs(), a2a.rhs());
			c2c.add(a2aClone);
			return (T) a2aClone;
		}
		
		public Set<PSOA2A> children() {
			return c2c.descendents(PSOA2A.class);
		}
		
		public <T extends Correspondence> Set<T> descendents(Class<T> ctype) {
			return c2c.descendents(ctype);
		}
		
		public void remove(Correspondence c) {
			c2c.remove(c);
		}
		
		public int size() {
			return c2c.descendents(PSOA2A.class).size();
		}
		
		public Context getContext() {
			return C1.c(c2c);
		}
	}
	
	
	public static class C2Context implements Context_ {
		private PSOC2C c2c1;
		private PSOC2C c2c2;
		
		public C2Context(C2<PSOC2C> c2) {
			c2c1 = c2.op1;
			c2c2 = c2.op2;
		}
		
		public void add(Correspondence c) {
			c2c1.add(c);
			c2c2.add(c);
		}
		
		public <T extends Correspondence> T addClone(T c) {
			if(!(c instanceof PSOR2R))
				throw new PSORuntimeException("Unexpected type");
			PSOR2R r2r = (PSOR2R) c;
			PSOR2R r2rClone = new PSOR2R(C2.c(c2c1, c2c2), r2r.lhs(), r2r.rhs());
			c2c1.add(r2rClone);
			c2c2.add(r2rClone);
			return (T) r2rClone;
		}
		
		public void remove(Correspondence c) {
			c2c1.remove(c);
			c2c2.remove(c);
		}
		
		public Set<PSOR2R> children() {
			return C2.c(c2c1, c2c2).descendents(PSOR2R.class);
		}
		
		public <T extends Correspondence> Set<T> descendents(Class<T> ctype) {
			return C2.c(c2c1, c2c2).descendents(ctype);
		}
		
		public int size() {
			return c2c1.descendents(PSOR2R.class).size();
		}

		public Context getContext() {
			return C2.c(c2c1, c2c2);
		}
		
	}
	
	
	public interface Context_ {
		public void remove(Correspondence c);
		public void add(Correspondence c);
		public <T extends Correspondence> T addClone(T c);
		public Set<? extends Correspondence> children();
		public <T extends Correspondence> Set<T> descendents(Class<T> ctype);
		public int size();
		public Context getContext();
	}
	
	
	public MergeContainer(Context context, Class<T> ctype) {
	
		if(ctype.equals(PSOC2C.class)) {
			PSOMapping m = (PSOMapping) context;
			
			context_ = new MContext(new PSOMapping());
			
			// add a flat copy of all original C2Cs to the merge container
			for(T c2c : m.descendents(ctype)) {
				T c2cClone = context_.addClone(c2c);
				originals2clones.put(c2c, c2cClone);
			}
		} else if(ctype.equals(PSOA2A.class)) {
			C1<PSOC2C> c1Cxt = (C1<PSOC2C>) context;
			PSOC2C c1C2C = c1Cxt.op1;
			
			PSOC2C cxtC2C = new PSOC2C(null, c1C2C.lhs(), c1C2C.rhs());
			C1<PSOC2C> cxt = C1.c(cxtC2C);
			
			context_ = new C1Context(cxt);
			
			// add a copy of all original A2As to the merge container
			for(T a2a : c1C2C.descendents(ctype)) {
				T a2aClone = context_.addClone(a2a);
				originals2clones.put(a2a, a2aClone);
			} 
		} else if(ctype.equals(PSOR2R.class)) {
			C2<PSOC2C> c2Cxt = (C2<PSOC2C>) context;
			PSOC2C c2C2C1 = c2Cxt.op1;
			PSOC2C c2C2C2 = c2Cxt.op2;
			
			PSOC2C cxtC2C1 = new PSOC2C(null, c2C2C1.lhs(), c2C2C1.rhs());
			PSOC2C cxtC2C2 = new PSOC2C(null, c2C2C2.lhs(), c2C2C2.rhs());
			C2<PSOC2C> cxt = C2.c(cxtC2C1, cxtC2C2);
			
			context_ = new C2Context(cxt);
			
			// add a copy of all original R2Rs to the merge container
			for(T r2r : c2Cxt.descendents(ctype)) {
				T r2rClone = context_.addClone(r2r);
				originals2clones.put(r2r, r2rClone);
			} 
		} else {
			throw new PSORuntimeException("Unexpected merge type " + ctype);
		}
	}

	
	public Context getMergedContext() {
		return context_.getContext();
	}	

	
	/**
	 * Returns the correspondences for an element.
	 * @param e Element (lhs or rhs) to get the correspondence for.
	 * @return Typed list of correspondences that map element e. If no 
	 * correspondence for element e exists then the returned list 
	 * is empty.
	 */
	public List<T> correspondencesFor(Element e) {
		List<T> cs = new Vector<T>();
		
		for(T candidate : children()) {
			T2<Collection<Element>,Collection<Element>> es = 
											candidate.elements();
			
			if(es.e0.contains(e) || es.e1.contains(e))
				cs.add(candidate);
		}
		return cs;
	}
	
	
	public T rhsAlignedOriginalCorrespondenceFor(T c) {
		List<T> cs =  correspondencesFor(
				c.elements().e1.iterator().next());
		for(T candidate : cs) {
			if(isOriginal(candidate))
				return candidate;
		}
		return null;
	}
	
	
	public T lhsAlignedOriginalCorrespondenceFor(T c) {
		List<T> cs =  correspondencesFor(
				c.elements().e0.iterator().next());
		for(T candidate : cs) {
			if(isOriginal(candidate))
				return candidate;
		}
		return null;
	}
	
	
	public T alignedOriginalCorrespondenceFor(T c, Side aligned) {
		if(aligned.equals(Side.LHS())) {
			return lhsAlignedOriginalCorrespondenceFor(c);
		} else if(aligned.equals(Side.RHS())) {
			return rhsAlignedOriginalCorrespondenceFor(c);
		} else {
			throw new PSORuntimeException("Unknown alignment " + aligned);
		}
	}
	
	
	public boolean isOriginal(T c) {
		return !oClones2tClones.containsKey(c) 
				&& !oClones2tClones.containsValue(c);
	}
	
	
	public void add(T c) {
		context_.add(c);
	}
	
	
	public void remove(T c) {
		context_.remove(c);
	}
	
	
	public Set<T> children() {
		return (Set<T>) context_.children();
	}
	
	
	public <S extends Correspondence> Set<S> descendents(Class<S> ctype) {
		return context_.descendents(ctype);
	}
	
	
	public int size() {
		return context_.size();
	}
	
	
	/**
	 * Applies (executes) a replacement merge operation to the container.
	 * @param rem
	 * @param internal
	 */
	public void apply(Replacement<T> rep, boolean internal) {
		logger.debug(
			"Apply replacement " + rep + ", internal " + internal
		);
		
		T fromClone = null;
		if(internal)
			fromClone = rep.from;
		else
			fromClone = originals2clones.get(rep.from);
		
		if(fromClone == null) {
			throw new PSORuntimeException(
			"Merged mapping does not contain original correspondence " 
				+ rep.from
			);
		}
		
		// remove original (clone) from merged mapping 
		remove(fromClone);
		
		T to = rep.to;
		
		// add target (clone) to merged mapping
		T toClone = context_.addClone(to);
		
		targets2clones.put(to, toClone);
		
		// store merge operation
		oClones2tClones.put(fromClone, toClone);
	}
	
	
	/**
	 * Applies (executes) a insert merge operation to the container.
	 * @param ins
	 */
	public void apply(Insert<T> ins) {
		logger.debug("Apply insert " + ins);
	
		T toClone = context_.addClone(ins.c);
		
		targets2clones.put(ins.c, toClone);
		
		// store merge operation
		Side side = ins.getSide();
		if(side.equals(Side.LHS())) {
			Element lhs = toClone.elements().e0.iterator().next();
			oClones2tClones.put(NotMapped.<T>createNotMapped(context_.getContext(), side, lhs), toClone);
		} else if(side.equals(Side.RHS())) {
			Element rhs = toClone.elements().e1.iterator().next();
			oClones2tClones.put(NotMapped.<T>createNotMapped(context_.getContext(), side, rhs), toClone);
		} else
			throw new PSORuntimeException("Unknown side " + side);
	}
	
	
	/**
	 * Applies (executes) a removal merge operation to the container.
	 * @param rem
	 * @param internal
	 */
	public void apply(Removal<T> rem, boolean internal) {
		logger.debug(
			"Apply removal " + rem + ", internal " + internal
		);
		
		T fromClone = null;
		if(internal)
			fromClone = rem.c;
		else
			fromClone = originals2clones.get(rem.c);
		
		if(fromClone == null) {
			throw new PSORuntimeException("Merged mapping " +
					"does not contain original correspondence " + rem.c);
		}
		
		remove(fromClone);
		
		// store merge operation
		Side side = rem.getSide();
		if(side.equals(Side.LHS())) {
			Element lhs = fromClone.elements().e0.iterator().next();
			oClones2tClones.put(fromClone, NotMapped.<T>createNotMapped(context_.getContext(), side, lhs));
		} else if(side.equals(Side.RHS())) {
			Element rhs = fromClone.elements().e1.iterator().next();
			oClones2tClones.put(fromClone, NotMapped.<T>createNotMapped(context_.getContext(), side, rhs));
		} else
			throw new PSORuntimeException("Unknown side " + side);
	}

}