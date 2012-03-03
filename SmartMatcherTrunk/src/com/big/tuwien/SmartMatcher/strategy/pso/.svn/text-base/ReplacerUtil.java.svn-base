package com.big.tuwien.SmartMatcher.strategy.pso;

import static com.google.common.collect.Lists.transform;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import com.big.tuwien.SmartMatcher.ClassElement;
import com.big.tuwien.SmartMatcher.EcoreElement;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.strategy.pso.Tuples.T2;
import com.big.tuwien.SmartMatcher.strategy.pso.merge.Insert;
import com.big.tuwien.SmartMatcher.strategy.pso.merge.Merge;
import com.big.tuwien.SmartMatcher.strategy.pso.merge.Removal;
import com.big.tuwien.SmartMatcher.strategy.pso.merge.Replacement;
import com.google.common.base.Function;

public class ReplacerUtil {

	private static enum SIDE {LHS, RHS};
	
	public static class Side {
		private SIDE side;
		
		private static Side lhs;
		private static Side rhs;
		
		
		public static Side LHS() {
			if(lhs == null)
				lhs = new Side(SIDE.LHS);
			return lhs;
		}
		
		
		public static Side RHS() {
			if(rhs == null)
				rhs = new Side(SIDE.RHS);
			return rhs;
		}
		
		
		private Side(SIDE side) {
			this.side = side;
		}
		
		
		public Side flip() {
			if(side.equals(SIDE.LHS))
				return RHS();
			else
				return LHS();
		}
		
		
		@Override
		public String toString() {
			return side.toString();
		}
	}
	
	
	
	
	public static <T extends Correspondence> 
				Merge<T> mergeOp(ExtPSOMapping m1, ExtPSOMapping m2, 
				List<Element> lhs, List<Element> rhs, Class<T> ctype) {	
		int lhsSize = lhs.size();
		int rhsSize = rhs.size();

		if(lhsSize == 0 || rhsSize == 0)
			return null;

		// LHS == RHS
		// only replacements possible
		if(lhsSize == rhsSize) {
			// shuffle for randomness
			Collections.shuffle(lhs);

			for(Element lhsE : lhs) {
				T c1 = m1.correspondenceFor(lhsE, ctype);
				T c2 = m2.correspondenceFor(lhsE, ctype);

				assert c1 != null && c2 != null;

				if(!c1.flatEquals(c2))
					return new Replacement<T>(Side.LHS(), c1, c2);
			}
			return null;	

			// LHS > RHS
			// This means in a complete mapping there are unmapped LHS elements
			// whereas on the RHS all elements are mapped.
			// replacements, inserts and removals are possible
		} else if(lhsSize > rhsSize) {
			// shuffle for randomness
			Collections.shuffle(lhs);

			for(Element lhsE : lhs) {
				T c1 = m1.correspondenceFor(lhsE, ctype);
				T c2 = m2.correspondenceFor(lhsE, ctype);

				if(c1 != null && c2 != null) {
					if(!c1.flatEquals(c2))
						return new Replacement<T>(Side.LHS(), c1, c2);
				} else if(c1 == null && c2 == null) {
					continue;
				} else if(c1 == null) {
					return new Insert<T>(Side.LHS(), c2);
				} else  {
					return new Removal<T>(Side.LHS(), c1);
				}
			}
			return null;

			// LHS < RHS
			// This means in a complete mapping there are unmapped RHS elements
			// whereas on the LHS all elements are mapped.
			// replacements, inserts and removals are possible
		} else {
			// shuffle for randomness
			Collections.shuffle(rhs);

			for(Element rhsE : rhs) {
				T c1 = m1.correspondenceFor(rhsE, ctype);
				T c2 = m2.correspondenceFor(rhsE, ctype);

				if(c1 != null && c2 != null) {
					// correspondences must be different
					if(!c1.flatEquals(c2))
						return new Replacement<T>(Side.RHS(), c1, c2);
				} else if(c1 == null && c2 == null) {
					// at least one correspondence must 
					// exist for the element
					continue;
				} else if(c1 == null) {
					return new Insert<T>(Side.RHS(), c2);
				} else  {
					return new Removal<T>(Side.RHS(), c1);
				}
			}
			return null;	
		}

	}


	public static List<Element> cast(List<? extends EcoreElement> elements) {
		return transform(
				elements, 
				new Function<EcoreElement,Element>() {
					@Override
					public Element apply(EcoreElement ee) {
						return ee.getRepresents();
					}
				});
	}

	
	public static <T extends Correspondence> 
				List<T2<T,T>> getFlatCommons(PSOMapping m1, PSOMapping m2, 
															Class<T> ctype) {
		Set<T> dcs1 = m1.descendents(ctype);
		Set<T> dcs2 = m2.descendents(ctype);
		
		List<T2<T,T>> commons = new Vector<T2<T,T>>();
		for(T c1 : dcs1) {
			for(T c2 : dcs2) {
				if(c1.flatEquals(c2))
					commons.add(Tuples.t(c1,c2));
			}
		}
		
		return commons;
	}
	
	
	public static List<T2<C2_,C2_>> compatibleC2s(
										PSOMapping m1, 
										PSOMapping m2) {
		List<C2_> c2cPairs1 = getC2s(m1);
		List<C2_> c2cPairs2 = getC2s(m2);

		List<T2<C2_,C2_>> compatible = new Vector<T2<C2_,C2_>>();
		
		// build pairs of compatible C2s
		for(C2_ c2cPair1 : c2cPairs1) {
			for(C2_ c2cPair2 : c2cPairs2) {
				if(c2cPair1.equals(c2cPair2) 
						|| c2cPair1.swap().equals(c2cPair2)) {
					compatible.add(Tuples.t(c2cPair1, c2cPair2));
				}
			}
		}

		return compatible;
	}
	
	
	public static class C2_ extends C2<PSOC2C> {

		public C2_(PSOC2C c2c1, PSOC2C c2c2) {
			super(c2c1, c2c2);
		}
		
		public C2_ swap() {
			PSOC2C swapped1 = new PSOC2C(null, op1.lhs, op2.rhs);
			PSOC2C swapped2 = new PSOC2C(null, op2.lhs, op1.rhs);
			return new C2_(swapped1, swapped2);
		}
		
		/**
		 * Implements FLAT equal().
		 */
		@Override
		public boolean equals(Object other) {
			if(this == other) return true;
			if(other == null || !(other instanceof C2_)) return false;
			C2_ that = (C2_) other;
			// agnostic to ordering:
			// for C2Cs c2c1 and c2c2:_ C2(c2c1,c2c2) == C2(c2c2,c2c1)
			return 
				(this.e0.flatEquals(that.e0) && this.e1.flatEquals(that.e1))
				|| (this.e0.flatEquals(that.e1) && this.e1.flatEquals(that.e0));
		}
		
		/**
		 * Implements FLAT hashCode().
		 */
		@Override
		public int hashCode() {
			return 57* (op1.flatHashCode() + op2.flatHashCode());
		}
		
	}
	
	
	/**
	 * Returns all C2-contexts (= R2R-contexts) in a mapping.
	 * @param m
	 * @return
	 */
	public static List<C2_> getC2s(PSOMapping m) {
		List<C2_> pairs = new Vector<C2_>();
		
		Set<PSOR2R> r2rs = m.descendents(PSOR2R.class);
		for(PSOR2R r2r : r2rs) {
			C2<PSOC2C> cxt = r2r.getContext();
			C2_ pair = new C2_(cxt.op1, cxt.op2);
			if(!pairs.contains(pair)) 
					pairs.add(pair);
		}
		
		return pairs;
	}
	
	
	/**
	 * Returns the attributes of a class.
	 * @param clazz
	 * @return
	 */
	public static List<Element> getAttributes(Element clazz) {
		return new Vector<Element>(
				ReplacerUtil.cast(
					((ClassElement)clazz.getRepresentedBy()).getAttributes()
				));
	}
	
	
//	public static List<PSOR2R> r2rsOfContext(C2<PSOC2C> c2) {
//		Set<PSOR2R> r2rs1 = c2.op1.descendents(PSOR2R.class);
//		Set<PSOR2R> r2rs2 = c2.op2.descendents(PSOR2R.class);
//		
//		List<PSOR2R> r2rs = new Vector<PSOR2R>(r2rs1.size() + r2rs2.size());
//		r2rs.addAll(r2rs1);
//		r2rs.addAll(r2rs2);
//		
//		List<PSOR2R> filtered = new ArrayList<PSOR2R>(
//									Collections2.filter(r2rs, hasContext2(c2))
//								);
//		return filtered;
//	}
	
	
//	public static Predicate<PSOR2R> hasC2Context(final C2<PSOC2C> c2) {
//		return new Predicate<PSOR2R>() {
//			@Override
//			public boolean apply(PSOR2R r2r) {
//				C2<PSOC2C> _c2 = r2r.getContext();
//				return _c2.op1.flatEquals(c2.op1) 
//					&& _c2.op2.flatEquals(c2.op2);
//			}
//			
//		};
//	}
	
	
//	public static Predicate<Correspondence> hasContext(final Context cxt) {
//		return new Predicate<Correspondence>() {
//			@Override
//			public boolean apply(Correspondence c) {
//				Context _cxt = c.getContext();
//				return _cxt.asList().size() == cxt.asList().size()
//					&& _cxt.asList().containsAll(cxt.asList());
//			}	
//		};
//	}
//	
//	
//	public static Predicate<Correspondence> hasContext2(final Context cxt) {
//		return new Predicate<Correspondence>() {
//			@Override
//			public boolean apply(Correspondence c) {
//				Context _cxt = c.getContext();
//				return flatEqual(_cxt.asList(), cxt.asList());
//			}	
//		};
//	}
	
	
	
	/**
	 * Returns if collection cs1 is flat equal to collection cs2.
	 */
	public static boolean flatEqual(Collection<Correspondence> cs1, Collection<Correspondence> cs2) {
		if(cs1.size() != cs2.size())
			return false;
		
		List<Correspondence> cs1_ = new Vector<Correspondence> (cs1);
		List<Correspondence> cs2_ = new Vector<Correspondence> (cs2);
		for(int i = 0; i < cs1_.size(); i++) {
			boolean match = false;
			for(int j = 0; j < cs2_.size(); j++) {
				if(cs1_.get(i).flatEquals(cs2_.get(j))) {
					match = true;
					cs2_.remove(j);
					break;
				}
			}
			if(!match)
				return false;
		}
		return true;
	}

	
	
}
