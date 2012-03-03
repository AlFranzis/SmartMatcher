package com.big.tuwien.SmartMatcher.strategy.pso.merge;

import static com.big.tuwien.SmartMatcher.strategy.pso.Cloner.without;

import java.util.List;
import java.util.Set;
import java.util.Vector;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.mmodel.MetaModel;
import com.big.tuwien.SmartMatcher.strategy.pso.C2;
import com.big.tuwien.SmartMatcher.strategy.pso.Cloner;
import com.big.tuwien.SmartMatcher.strategy.pso.Context;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOC2C;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOMapping;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOR2R;
import com.big.tuwien.SmartMatcher.strategy.pso.PSORuntimeException;
import com.big.tuwien.SmartMatcher.strategy.pso.QueryByExample;
import com.big.tuwien.SmartMatcher.strategy.pso.SwapperUtil;
import com.big.tuwien.SmartMatcher.strategy.pso.ReplacerUtil.Side;

public class R2RMergeView extends MergeView<PSOR2R> {
	private C2<PSOC2C> cxt;
	
	public R2RMergeView(C2<PSOC2C> origContext, final PSOMapping m1, 
			final PSOMapping m2, final MetaModel mm1,final MetaModel mm2) {
		
		super(
			PSOR2R.class,
			origContext,
			m1, 
			m2, 
			mm1, 
			mm2,
			new Picker<PSOR2R>() {
					public List<Element> pickUnmatchedLHS(Context cxt) {
						return new Vector<Element>(
								SwapperUtil.pickUnmatchedRefs(m1, 
										((C2<PSOC2C>) cxt).op1.lhs(),
										((C2<PSOC2C>) cxt).op2.lhs())
						);
					}
					
					public List<Element> pickUnmatchedRHS(Context cxt) {
						return new Vector<Element>(
								SwapperUtil.pickUnmatchedRefs(m1, 
										((C2<PSOC2C>) cxt).op1.rhs(),
										((C2<PSOC2C>) cxt).op2.rhs())
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
	

	public PSOMapping merge(Replacement<PSOR2R> rep) {
		reqMappings = neededR2RMappings(rep.from.getContext());
		
		MergeContainer<PSOR2R> merged = super.merged(rep);
		
		C2<PSOC2C> mergedCxt = (C2<PSOC2C>) merged.getMergedContext();
		
		PSOMapping mClone = mergeWithOriginal(m1, mergedCxt);
		return mClone;
	}
	
	
	public PSOMapping merge(Insert<PSOR2R> ins) {
		reqMappings = neededR2RMappings(ins.c.getContext());
		
		MergeContainer<PSOR2R> merged = super.merged(ins);
		
		C2<PSOC2C> mergedCxt = (C2<PSOC2C>) merged.getMergedContext();
		
		PSOMapping mClone = mergeWithOriginal(m1, mergedCxt);
		return mClone;
	}


	public PSOMapping merge(Removal<PSOR2R> rem) {
		reqMappings = neededR2RMappings(rem.c.getContext());
		
		MergeContainer<PSOR2R> merged = super.merged(rem);
		
		C2<PSOC2C> mergedCxt = (C2<PSOC2C>) merged.getMergedContext();
		
		PSOMapping mClone = mergeWithOriginal(m1, mergedCxt);
		return mClone;
	}


	/*
	 * Returns how many R2R-mappings are needed
	 * for a context to accomplish a maximal mapping.
	 */
	private int neededR2RMappings(C2<PSOC2C> c2cCxt) {
		PSOC2C c2c1 = c2cCxt.op1;
		PSOC2C c2c2 = c2cCxt.op2;
		int lhsRefs = SwapperUtil.getReferences(c2c1.lhs(), c2c2.lhs()).size();
		int rhsRefs = SwapperUtil.getReferences(c2c1.rhs(), c2c2.rhs()).size();
		
		return lhsRefs <= rhsRefs ? lhsRefs : rhsRefs;
	}
	
	
	/*
	 * Merges the original mapping with the merged context.
	 * All R2Rs of the original merge context are replaced by the 
	 * R2Rss of the merged context.
	 */
	private PSOMapping mergeWithOriginal(PSOMapping original, 
													C2<PSOC2C> mergedCxt) {
		// clone original mapping without R2Rs of the original merge context
		Cloner cloner = new Cloner(original, 
							without(cxt.descendents(PSOR2R.class)));
		PSOMapping mClone = cloner.getClone();
		
		PSOC2C cxtC2C1 = mergedCxt.op1;
		Set<PSOC2C> c2cs = QueryByExample.instance(mClone)
							.descendants(cxtC2C1, PSOC2C.class, true);
		assert c2cs.size() == 1;
		PSOC2C clonedC2C1 = c2cs.iterator().next();
		
		PSOC2C cxtC2C2 = mergedCxt.op2;
		c2cs = QueryByExample.instance(mClone)
							.descendants(cxtC2C2, PSOC2C.class, true);
		assert c2cs.size() == 1;
		PSOC2C clonedC2C2 = c2cs.iterator().next();
		
		// add all R2Rs of merged context to the cloned mapping
		for(PSOR2R r2r : mergedCxt.descendents(PSOR2R.class)) {
			PSOR2R r2rClone = 
				new PSOR2R(C2.c(clonedC2C1, clonedC2C2), r2r.lhs(), r2r.rhs());
			clonedC2C1.add(r2rClone);
			clonedC2C2.add(r2rClone);
		}
		
		return mClone;
	}
	
}
