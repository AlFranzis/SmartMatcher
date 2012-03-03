package com.big.tuwien.SmartMatcher.strategy.pso.merge;

import static com.big.tuwien.SmartMatcher.strategy.pso.Cloner.without;

import java.util.List;
import java.util.Set;
import java.util.Vector;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.mmodel.MetaModel;
import com.big.tuwien.SmartMatcher.strategy.pso.C1;
import com.big.tuwien.SmartMatcher.strategy.pso.Cloner;
import com.big.tuwien.SmartMatcher.strategy.pso.Context;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOA2A;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOC2C;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOMapping;
import com.big.tuwien.SmartMatcher.strategy.pso.PSORuntimeException;
import com.big.tuwien.SmartMatcher.strategy.pso.QueryByExample;
import com.big.tuwien.SmartMatcher.strategy.pso.ReplacerUtil;
import com.big.tuwien.SmartMatcher.strategy.pso.SwapperUtil;
import com.big.tuwien.SmartMatcher.strategy.pso.ReplacerUtil.Side;

public class A2AMergeView extends MergeView<PSOA2A> {
	private C1<PSOC2C> cxt;

	public A2AMergeView(C1<PSOC2C> origContext, final PSOMapping m1, 
			final PSOMapping m2, final MetaModel mm1,final MetaModel mm2) {
		
		super(
			PSOA2A.class,
			origContext,
			m1, 
			m2, 
			mm1, 
			mm2,
			new Picker<PSOA2A>() {
					public List<Element> pickUnmatchedLHS(Context cxt) {
						return new Vector<Element>(
								SwapperUtil.pickUnmatchedAttributes(m1, 
										((C1<PSOC2C>) cxt).op1.lhs())
						);
					}
					
					public List<Element> pickUnmatchedRHS(Context cxt) {
						return new Vector<Element>(
								SwapperUtil.pickUnmatchedAttributes(m1,
										((C1<PSOC2C>) cxt).op1.rhs())
						);
					}

					@Override
					public List<Element> pickUnmatched(Side side,
							Context cxt) {
						if(side.equals(Side.LHS())) {
							return pickUnmatchedLHS(cxt);
						} else if(side.equals(Side.RHS())) {
							return pickUnmatchedRHS(cxt);
						} else {
							throw new PSORuntimeException("Unknown side " + side);
						}
					}
				}
		);
		cxt = origContext;
	}

	
	public PSOMapping merge(Replacement<PSOA2A> rep) {
		reqMappings = neededA2AMappings(rep.from.getContext());
		
		MergeContainer<PSOA2A> merged = super.merged(rep);
		C1<PSOC2C> mergedCxt = (C1<PSOC2C>) merged.getMergedContext();
		
		PSOMapping mClone = mergeWithOriginal(m1, mergedCxt);
		
		return mClone;
	}
	
	
	public PSOMapping merge(Insert<PSOA2A> ins) {
		reqMappings = neededA2AMappings(ins.c.getContext());
		
		MergeContainer<PSOA2A> merged = super.merged(ins);
		C1<PSOC2C> mergedCxt = (C1<PSOC2C>) merged.getMergedContext();
		
		PSOMapping mClone = mergeWithOriginal(m1, mergedCxt);
		
		return mClone;
	}


	public PSOMapping merge(Removal<PSOA2A> rem) {
		reqMappings = neededA2AMappings(rem.c.getContext());
		
		MergeContainer<PSOA2A> merged = super.merged(rem);
		C1<PSOC2C> mergedCxt = (C1<PSOC2C>) merged.getMergedContext();
		
		PSOMapping mClone = mergeWithOriginal(m1, mergedCxt);
		
		return mClone;
	}


	/*
	 * Returns how many A2A-mappings are needed
	 * for a context to accomplish a maximal mapping.
	 */
	private int neededA2AMappings(C1<PSOC2C> c1) {
		int lhsAtts = ReplacerUtil.getAttributes(c1.op1.lhs()).size();
		int rhsAtts = ReplacerUtil.getAttributes(c1.op1.rhs()).size();
		
		return lhsAtts <= rhsAtts ? lhsAtts : rhsAtts;
	}
	
	
	/*
	 * Merges the original mapping with the merged context.
	 * All A2A of the original merge context are replaced by the 
	 * A2As of the merged context.
	 */
	private PSOMapping mergeWithOriginal(PSOMapping original, 
												C1<PSOC2C> mergedCxt) {
		PSOC2C cxtC2C = mergedCxt.op1;
		
		// clone original mapping without A2As of the original merge context
		Cloner cloner = new Cloner(original, 
								without(cxt.descendents(PSOA2A.class)));
		PSOMapping mClone = cloner.getClone();
		
		Set<PSOC2C> c2cs = QueryByExample.instance(mClone)
								.descendants(cxtC2C, PSOC2C.class, true);
		assert c2cs.size() == 1;
		PSOC2C clonedC2C = c2cs.iterator().next();
		
		// add all A2As of merged context to the cloned mapping
		for(PSOA2A a2a : cxtC2C.descendents(PSOA2A.class)) {
			PSOA2A a2aClone = new PSOA2A(C1.c(clonedC2C), a2a.lhs(), a2a.rhs());
			clonedC2C.add(a2aClone);
		}
		
		return mClone;
	}
	
}
