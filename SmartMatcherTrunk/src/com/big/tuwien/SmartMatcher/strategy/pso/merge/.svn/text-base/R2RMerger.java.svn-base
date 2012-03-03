package com.big.tuwien.SmartMatcher.strategy.pso.merge;

import static com.big.tuwien.SmartMatcher.strategy.pso.ReplacerUtil.mergeOp;
import static com.big.tuwien.SmartMatcher.strategy.pso.SwapperUtil.getReferences;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.mmodel.MetaModel;
import com.big.tuwien.SmartMatcher.strategy.pso.C2;
import com.big.tuwien.SmartMatcher.strategy.pso.ExtPSOMapping;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOC2C;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOMapping;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOR2R;
import com.big.tuwien.SmartMatcher.strategy.pso.PSORuntimeException;
import com.big.tuwien.SmartMatcher.strategy.pso.ReplacerUtil;
import com.big.tuwien.SmartMatcher.strategy.pso.Tuples;
import com.big.tuwien.SmartMatcher.strategy.pso.ReplacerUtil.C2_;
import com.big.tuwien.SmartMatcher.strategy.pso.Tuples.T2;

public class R2RMerger implements Merger { 
	private static Logger logger = Logger.getLogger(R2RMerger.class);
	
	private MetaModel lhsMM; 
	private MetaModel rhsMM;

	
	public R2RMerger(MetaModel lhsMM, MetaModel rhsMM) {
		this.lhsMM = lhsMM;
		this.rhsMM = rhsMM;
	}

	
	/**
	 * Merges a randomly chosen R2R-mapping in m1 with a randomly chosen 
	 * R2R-mapping from m2. 
	 * If additional operations (replacements, inserts, removals of other 
	 * mappings) are necessary to gain a valid and complete result mapping then 
	 * these operations are performed.
	 * Informal semantics of this operation: This operation moves m1 towards m2. 
	 * @param m1 Complete mapping 
	 * @param m2 Complete mapping
	 * @return A complete mapping that differs from m1 in at least one
	 * R2R-mapping.
	 */
	public PSOMapping merge(PSOMapping m1, PSOMapping m2) {
		T2<C2<PSOC2C>,Merge<PSOR2R>> merge = pick(
									new ExtPSOMapping(m1, lhsMM, rhsMM), 
									new ExtPSOMapping(m2, lhsMM, rhsMM));
		
		// if no merging possible return original m1
		if(merge == null) {
			logger.info("No R2R merging possible for given mappings, m1: " + m1 + " , m2: " + m2);
			return m1;
		}
		
		R2RMergeView mView = new R2RMergeView(merge.e0, m1,m2, lhsMM, rhsMM);
		
		Merge<PSOR2R> mergeOp = merge.e1;
		
		PSOMapping combined = null;
		if(mergeOp instanceof Replacement<?>) {
			combined = mView.merge((Replacement<PSOR2R>)mergeOp);
		} else if(mergeOp instanceof Insert<?>) {
			combined = mView.merge((Insert<PSOR2R>)mergeOp);
		} else if(mergeOp instanceof Removal<?>) {
			combined = mView.merge((Removal<PSOR2R>)mergeOp);
		} else {
			throw new PSORuntimeException(
					"Method not implemented " +
					"for merge operation type " + mergeOp);
		}
		
		return combined;
	}
	
	
	/**
	 * Picks a suitable merge operation for a random pair of R2Rs 
	 * (r2r_1,r2r_2) with the first R2R being a member of m1 
	 * (r2r_1 element of m1) and the second being a member of m2 
	 * (r2r_2 element of m2). 
	 * @param m1 Mapping to pick an R2R from.
	 * @param m2 Mapping to pick an R2R from.
	 * @return Merge operation .
	 */
	protected T2<C2<PSOC2C>,Merge<PSOR2R>> pick(ExtPSOMapping m1, ExtPSOMapping m2) {
		List<T2<C2_,C2_>> commonCxts = ReplacerUtil.compatibleC2s(m1, m2);
		
		if(commonCxts.isEmpty()) return null;
		
		// shuffle for randomness
		Collections.shuffle(commonCxts);
		
		for(T2<C2_,C2_> commonCxt : commonCxts) {
			C2_ pair1 = commonCxt.e0;
			// C2CPair pair2 = commonCxt.e1;
			
			List<Element> lhsRefs = getReferences(pair1.e0.lhs(), 
												pair1.e1.lhs());
			List<Element> rhsRefs = getReferences(pair1.e0.rhs(), 
												pair1.e1.rhs());
			Merge<PSOR2R> mergeOp = mergeOp(m1, m2, 
									lhsRefs, rhsRefs, PSOR2R.class);
			if(mergeOp != null) 
				return Tuples.t(C2.c(pair1.e0, pair1.e1), mergeOp);
		}
		
		return null;
	}
		
}
