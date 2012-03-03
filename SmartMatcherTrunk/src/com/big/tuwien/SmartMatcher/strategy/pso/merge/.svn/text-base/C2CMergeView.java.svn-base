package com.big.tuwien.SmartMatcher.strategy.pso.merge;

import static com.big.tuwien.SmartMatcher.strategy.pso.PredicatesUtil.disjointFrom;
import static com.google.common.base.Predicates.and;

import java.util.List;
import java.util.Set;
import java.util.Vector;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.mmodel.MetaModel;
import com.big.tuwien.SmartMatcher.strategy.pso.C1;
import com.big.tuwien.SmartMatcher.strategy.pso.C2;
import com.big.tuwien.SmartMatcher.strategy.pso.Cloner;
import com.big.tuwien.SmartMatcher.strategy.pso.Context;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOA2A;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOC2C;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOMapping;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOR2R;
import com.big.tuwien.SmartMatcher.strategy.pso.PSORuntimeException;
import com.big.tuwien.SmartMatcher.strategy.pso.SwapperUtil;
import com.big.tuwien.SmartMatcher.strategy.pso.ReplacerUtil.Side;
import com.big.tuwien.SmartMatcher.strategy.pso.generate.PSOR2RGenerator2;

public class C2CMergeView extends MergeView<PSOC2C> {


	public C2CMergeView(PSOMapping origCxt, final PSOMapping m1, 
			final PSOMapping m2, final MetaModel mm1,final MetaModel mm2) {
	
		super(
			PSOC2C.class,
			origCxt,
			m1, 
			m2, 
			mm1, 
			mm2,
			new Picker<PSOC2C>() {
				public List<Element> pickUnmatchedLHS(Context context) {
					return new Vector<Element>(
							SwapperUtil.pickUnmatchedClasses(m1, mm1)
						);
				}
				
				public List<Element> pickUnmatchedRHS(Context context) {
					return new Vector<Element>(
							SwapperUtil.pickUnmatchedClasses(m1, mm2)
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
	}

	
	public PSOMapping merge(Replacement<PSOC2C> rep) {
		reqMappings = neededC2CMappings(mm1, mm2);
		MergeContainer<PSOC2C> merged = super.merged(rep);
		
		mergeA2As(merged);
		
		mergeR2Rs(merged);
		
		return (PSOMapping) merged.getMergedContext();
	}
	
	
	public PSOMapping merge(Insert<PSOC2C> ins) {
		reqMappings = neededC2CMappings(mm1, mm2);
		MergeContainer<PSOC2C> merged = super.merged(ins);
		
		mergeA2As(merged);
		
		mergeR2Rs(merged);
		
		return (PSOMapping) merged.getMergedContext();
	}
	
	
	public PSOMapping merge(Removal<PSOC2C> rem) {
		reqMappings = neededC2CMappings(mm1, mm2);
		MergeContainer<PSOC2C> merged = super.merged(rem);
		
		mergeA2As(merged);
		
		mergeR2Rs(merged);
		
		return (PSOMapping) merged.getMergedContext();
	}
	
	
	/*
	 * Returns how many C2C-mappings are needed
	 * to accomplish a maximal mapping.
	 */
	private int neededC2CMappings(MetaModel lhsMM, MetaModel rhsMM) {
		int lhsClasses = lhsMM.getClasses().size();
		int rhsClasses = rhsMM.getClasses().size();
		
		return lhsClasses <= rhsClasses ? lhsClasses : rhsClasses;
	}
	
	
	/**
	 * Adds all A2As 
	 * @param merged
	 * @param rep
	 */
	private void mergeA2As(MergeContainer<PSOC2C> merged) {
		for(PSOC2C mergedC2C : merged.children()) {
			// if merged C2C is an original (= from m1) -> copy all A2As from original
			if(merged.isOriginal(mergedC2C)) {
				PSOC2C origC2C = (PSOC2C) merged.originals2clones.inverse().get(mergedC2C);
				for(PSOA2A origA2A : origC2C.descendents(PSOA2A.class)) {
					PSOA2A mergedA2A = new PSOA2A(C1.c(mergedC2C), origA2A.lhs(), origA2A.rhs());
					mergedC2C.add(mergedA2A);
				}
			// if merged C2C is an not original (= from m2) -> copy all A2As from target
			} else {
				PSOC2C tarC2C = (PSOC2C) merged.targets2clones.inverse().get(mergedC2C);
				for(PSOA2A tarA2A : tarC2C.descendents(PSOA2A.class)) {
					PSOA2A mergedA2A = new PSOA2A(C1.c(mergedC2C), tarA2A.lhs(), tarA2A.rhs());
					mergedC2C.add(mergedA2A);
				}
			}
		}
	}
	
	
	
	private void mergeR2Rs(MergeContainer<PSOC2C> merged) {
		addCompatibleOriginalR2Rs(merged);
		addCompatibleM2R2Rs(merged);
		generateAdditionalR2Rs(merged);
	}
	
	
	private void addCompatibleOriginalR2Rs(MergeContainer<PSOC2C> merged) {
		List<PSOC2C> mergedC2Cs = new Vector<PSOC2C>(merged.children());
		for(int i = 0; i < mergedC2Cs.size(); i++) {
			for(int j = i + 1; j < mergedC2Cs.size(); j++) {
				C2<PSOC2C> mergedC2CPair = C2.c(mergedC2Cs.get(i), mergedC2Cs.get(j));
				for(PSOR2R origR2R : m1.descendents(PSOR2R.class)) {
					if(origR2R.compatible(mergedC2CPair)) {
						// r2r is compatible
						PSOR2R mergedR2R = new PSOR2R(mergedC2CPair, origR2R.lhs(), origR2R.rhs());
						mergedC2CPair.op1.add(mergedR2R);
						mergedC2CPair.op2.add(mergedR2R);
					}
				}
			}
		}
	}
	
	
	private void addCompatibleM2R2Rs(MergeContainer<PSOC2C> merged) {
		Set<PSOR2R> presetMergedR2Rs = merged.descendents(PSOR2R.class);
		
		Set<PSOR2R> candidateTarR2Rs = m2.descendents(
										and(
											disjointFrom(presetMergedR2Rs),
											Cloner.type(PSOR2R.class)
											)
										);
		
		List<PSOC2C> mergedC2Cs = new Vector<PSOC2C>(merged.descendents(PSOC2C.class));
		for(int i = 0; i < mergedC2Cs.size(); i++) {
			for(int j = i + 1; j < mergedC2Cs.size(); j++) {
				C2<PSOC2C> mergedC2CPair = C2.c(mergedC2Cs.get(i), mergedC2Cs.get(j));
				for(PSOR2R targetR2R : candidateTarR2Rs) {
					if(targetR2R.compatible(mergedC2CPair)) {
						// r2r is compatible
						PSOR2R mergedR2R = new PSOR2R(mergedC2CPair, targetR2R.lhs(), targetR2R.rhs());
						mergedC2CPair.op1.add(mergedR2R);
						mergedC2CPair.op2.add(mergedR2R);
					}
				}
			}
		}
	}
	
	
	private void generateAdditionalR2Rs(MergeContainer<PSOC2C> merged) {
		PSOR2RGenerator2 r2rGenerator = new PSOR2RGenerator2();
		
		Set<PSOR2R> presetMergedR2Rs = merged.descendents(PSOR2R.class);
		List<PSOC2C> mergedC2Cs = new Vector<PSOC2C>(merged.descendents(PSOC2C.class));
		for(int i = 0; i < mergedC2Cs.size(); i++) {
			for(int j = i + 1; j < mergedC2Cs.size(); j++) {
				C2<PSOC2C> mergedC2CPair = C2.c(mergedC2Cs.get(i), mergedC2Cs.get(j));
				r2rGenerator.generateR2Rss(mergedC2CPair, presetMergedR2Rs);
				// generated R2Rs are added to context implicitly
			}
		}
	}
	
}
