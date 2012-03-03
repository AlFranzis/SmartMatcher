package com.big.tuwien.SmartMatcher.strategy.pso.merge;

import static com.big.tuwien.SmartMatcher.strategy.pso.ReplacerUtil.mergeOp;

import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.mmodel.MetaModel;
import com.big.tuwien.SmartMatcher.strategy.pso.ExtPSOMapping;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOC2C;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOMapping;
import com.big.tuwien.SmartMatcher.strategy.pso.PSORuntimeException;

public class C2CMerger implements Merger { 
	private static Logger logger = Logger.getLogger(C2CMerger.class);
	
	private MetaModel lhsMM; 
	private MetaModel rhsMM;

	
	public C2CMerger(MetaModel lhsMM, MetaModel rhsMM) {
		this.lhsMM = lhsMM;
		this.rhsMM = rhsMM;
	}

	
	/**
	 * Merges a randomly chosen C2C-mapping in m1 with a randomly chosen 
	 * C2C-mapping from m2. 
	 * If additional operations (replacements, inserts, removals of other 
	 * mappings) are necessary to gain a valid and complete result mapping then 
	 * these operations are performed.
	 * Informal semantics of this operation: This operation moves m1 towards m2. 
	 * @param m1 Complete mapping 
	 * @param m2 Complete mapping
	 * @return A complete mapping that differs from m1 in at least one
	 * C2C-mapping.
	 */
	public PSOMapping merge(PSOMapping m1, PSOMapping m2) {
		Merge<PSOC2C> mergeOp = pick(
									new ExtPSOMapping(m1, lhsMM, rhsMM), 
									new ExtPSOMapping(m2, lhsMM, rhsMM));
		
		// if no merging possible return original m1
		if(mergeOp == null) {
			logger.info("No C2C merging possible for given mappings, m1: " + m1 + " , m2: " + m2);
			return m1;
		}
		
		C2CMergeView mView = new C2CMergeView(m1, m1, m2, lhsMM, rhsMM);
		
		PSOMapping combined = null;
		if(mergeOp instanceof Replacement<?>) {
			combined = mView.merge((Replacement<PSOC2C>)mergeOp);
		} else if(mergeOp instanceof Insert<?>) {
			combined = mView.merge((Insert<PSOC2C>)mergeOp);
		} else if(mergeOp instanceof Removal<?>) {
			combined = mView.merge((Removal<PSOC2C>)mergeOp);
		} else {
			throw new PSORuntimeException(
					"Method not implemented " +
					"for merge operation type " + mergeOp);
		}
		
		return combined;
	}
	
	
	/**
	 * Picks a suitable merge operation for a random pair of C2Cs 
	 * (c2c_1,c2c2_2) with the first C2C being a member of m1 
	 * (c2c_1 element of m1) and the second being a member of m2 
	 * (c2c_2 element of m2). 
	 * @param m1 Mapping to pick a C2C from.
	 * @param m2 Mapping to pick a C2C from.
	 * @return Merge operation .
	 */
	protected Merge<PSOC2C> pick(ExtPSOMapping m1, ExtPSOMapping m2) {
		// check(m1, m2);
		
		List<Element> lhsClasses = new Vector<Element>(lhsMM.getClasses());
		List<Element> rhsClasses = new Vector<Element>(rhsMM.getClasses());
		
		Merge<PSOC2C> mergeOp = mergeOp(m1, m2, lhsClasses, rhsClasses, 
													PSOC2C.class);
		
		return mergeOp;
	}

}
