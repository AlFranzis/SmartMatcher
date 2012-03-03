package com.big.tuwien.SmartMatcher.strategy.pso.merge;

import static com.big.tuwien.SmartMatcher.strategy.pso.ReplacerUtil.getAttributes;
import static com.big.tuwien.SmartMatcher.strategy.pso.ReplacerUtil.getFlatCommons;
import static com.big.tuwien.SmartMatcher.strategy.pso.ReplacerUtil.mergeOp;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.mmodel.MetaModel;
import com.big.tuwien.SmartMatcher.strategy.pso.C1;
import com.big.tuwien.SmartMatcher.strategy.pso.ExtPSOMapping;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOA2A;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOC2C;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOMapping;
import com.big.tuwien.SmartMatcher.strategy.pso.PSORuntimeException;
import com.big.tuwien.SmartMatcher.strategy.pso.Tuples;
import com.big.tuwien.SmartMatcher.strategy.pso.Tuples.T2;

public class A2AMerger implements Merger { 
	private static Logger logger = Logger.getLogger(A2AMerger.class);
	
	private MetaModel lhsMM; 
	private MetaModel rhsMM;

	
	public A2AMerger(MetaModel lhsMM, MetaModel rhsMM) {
		this.lhsMM = lhsMM;
		this.rhsMM = rhsMM;
	}

	
	/**
	 * Merges a randomly chosen A2A-mapping in m1 with a randomly chosen 
	 * A2A-mapping from m2. 
	 * If additional operations (replacements, inserts, removals of other 
	 * mappings) are necessary to gain a valid and complete result mapping 
	 * then these operations are performed.
	 * Informal semantics of this operation: This operation moves m1 
	 * towards m2. 
	 * @param m1 Complete mapping 
	 * @param m2 Complete mapping
	 * @return A complete mapping that differs from m1 in at least one
	 * A2A-mapping.
	 */
	public PSOMapping merge(PSOMapping m1, PSOMapping m2) {
		T2<C1<PSOC2C>,Merge<PSOA2A>> merge = pick(
									new ExtPSOMapping(m1, lhsMM, rhsMM), 
									new ExtPSOMapping(m2, lhsMM, rhsMM));
		
		// if no merging possible return original m1
		if(merge == null) {
			logger.info("No A2A merging possible for given mappings, m1: " + m1 + " , m2: " + m2);
			return m1;
		}
		
		A2AMergeView mView = new A2AMergeView(merge.e0, m1, m2, 
										lhsMM, rhsMM);
		
		Merge<PSOA2A> mergeOp = merge.e1;
		
		PSOMapping combined = null;
		if(mergeOp instanceof Replacement<?>) {
			combined = mView.merge((Replacement<PSOA2A>)mergeOp);
		} else if(mergeOp instanceof Insert<?>) {
			combined = mView.merge((Insert<PSOA2A>)mergeOp);
		} else if(mergeOp instanceof Removal<?>) {
			combined = mView.merge((Removal<PSOA2A>)mergeOp);
		} else {
			throw new PSORuntimeException(
					"Merge not implemented " +
					"for merge operation type " + mergeOp);
		}
		
		return combined;
	}
	
	
	/**
	 * Picks a suitable merge operation for a random pair of A2As 
	 * (a2a_1,a2a_2) with the first A2A being a member of m1 
	 * (a2a_1 element of m1) and the second being a member of m2 
	 * (a2a_2 element of m2). 
	 * @param m1 Mapping to pick an A2A from.
	 * @param m2 Mapping to pick an A2A from.
	 * @return Merge operation .
	 */
	protected T2<C1<PSOC2C>,Merge<PSOA2A>> pick(ExtPSOMapping m1, ExtPSOMapping m2) {
		// common C2Cs of m1 and m2
		List<T2<PSOC2C,PSOC2C>> commonC2Cs = getFlatCommons(m1, m2, 
													PSOC2C.class);
		
		if(commonC2Cs.isEmpty()) return null;
		
		// shuffle for randomness
		Collections.shuffle(commonC2Cs);
		
		for(T2<PSOC2C,PSOC2C> commonC2C : commonC2Cs) {
			PSOC2C c2c1 = commonC2C.e0;
			PSOC2C c2c2 = commonC2C.e1;
			
			assert c2c1.lhs().equals(c2c2.lhs()) && c2c1.rhs().equals(c2c2.rhs());
			
			List<Element> lhsAttributes = getAttributes(c2c1.lhs());
			List<Element> rhsAttributes = getAttributes(c2c1.rhs());
					
			Merge<PSOA2A> mergeOp = mergeOp(m1, m2, 
										lhsAttributes, rhsAttributes, 
										PSOA2A.class);
			if(mergeOp != null) 
				return Tuples.t(C1.c(c2c1), mergeOp);
		}
		
		return null;
		
	}
		
}
