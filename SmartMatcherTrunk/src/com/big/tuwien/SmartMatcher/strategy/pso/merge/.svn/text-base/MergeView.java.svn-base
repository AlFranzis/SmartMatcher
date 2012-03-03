package com.big.tuwien.SmartMatcher.strategy.pso.merge;


import java.util.Collections;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.mmodel.MetaModel;
import com.big.tuwien.SmartMatcher.strategy.pso.Context;
import com.big.tuwien.SmartMatcher.strategy.pso.Correspondence;
import com.big.tuwien.SmartMatcher.strategy.pso.ExtPSOMapping;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOMapping;
import com.big.tuwien.SmartMatcher.strategy.pso.PSORuntimeException;
import com.big.tuwien.SmartMatcher.strategy.pso.ReplacerUtil.Side;

public class MergeView<T extends Correspondence> {
	private static Logger logger = Logger.getLogger(MergeView.class);
	
	protected ExtPSOMapping m1;
	protected ExtPSOMapping m2;
	protected MetaModel mm1; 
	protected MetaModel mm2;
	
	protected Context origCxt;
	protected Class<T> ctype;
	
	protected int reqMappings;
	protected Picker<T> picker;
	
	
	public MergeView(Class<T> ctype, Context origCxt, PSOMapping m1, 
					PSOMapping m2, MetaModel mm1, MetaModel mm2, 
					Picker<T> picker) {
		this.ctype = ctype;
		this.origCxt = origCxt;
		this.m1 = new ExtPSOMapping(m1, mm1, mm2);
		this.m2 = new ExtPSOMapping(m2, mm2, mm2);
		this.mm1 = mm1;
		this.mm2 = mm2;
		this.picker = picker;
	}
	
	
	public MergeView(PSOMapping m1, PSOMapping m2, 
			MetaModel mm1, MetaModel mm2) {
		this.m1 = new ExtPSOMapping(m1, mm1, mm2);
		this.m2 = new ExtPSOMapping(m2, mm2, mm2);
		this.mm1 = mm1;
		this.mm2 = mm2;
	}

	
	public MergeContainer<T> merged(Replacement<T> rep) {
		logger.debug("Merging of replacement " + rep);
		MergeContainer<T> merged = 
				new MergeContainer<T>(origCxt, (Class<T>) rep.getType());
		
		List<Merge<?>> transformations = new Vector<Merge<?>>();
		transformations.add(rep);
		
		chain2(rep, transformations, merged, false);
		return merged;
	}
	
	
	
	public MergeContainer<T> merged(Insert<T> ins) {
		logger.debug("Merging of insert " + ins);
		MergeContainer<T> merged = 
				new MergeContainer<T>(origCxt, (Class<T>) ins.getType());
		
		List<Merge<?>> transformations = new Vector<Merge<?>>();
		transformations.add(ins);
		
		chain2(ins, transformations, merged, false);
		
		return merged;
	}
	
	
	public MergeContainer<T> merged(Removal<T> rem) {
		logger.debug("Merging of removal " + rem);
		MergeContainer<T> merged = 
				new MergeContainer<T>(origCxt, (Class<T>) rem.getType());
		
		List<Merge<?>> transformations = new Vector<Merge<?>>();
		transformations.add(rem);
		
		chain2(rem, transformations, merged, false);
		
		return merged;
	}
	
	
//	protected void chain(Replacement<T> rep, 
//			List<Merge<?>> chain, 
//			MergeContainer<T> merged, 
//			boolean internal) {
//		// execute replacement in merge container
//		merged.apply(rep, internal);
//
//		// look for necessary additional replacements
//		// to get a consistent and complete merged mapping
//
//		// oClone from merged mapping
//		T c1 = merged.rhsAlignedOriginalCorrespondenceFor(
//				rep.to);
//		if(c1 != null) {
//			T c2 = m2.lhsAlignedCorrespondenceFor(c1);
//
//			// replacement
//			if(c2 != null) {
//				Replacement<T> crep = new Replacement<T>(c1, c2);
//				chain.add(crep);
//				chain(crep, chain, merged, true);
//				// removal
//			} else {
//				Removal<T> rem = new Removal<T>(c1);
//				chain.add(rem);
//				chain(rem, chain, merged, true);
//			}
//		}
//	}
	
	
	protected void chain2(Replacement<T> rep, 
			List<Merge<?>> chain, 
			MergeContainer<T> merged, 
			boolean internal) {
		Side side = rep.getSide();
		
		// execute replacement in merge container
		merged.apply(rep, internal);

		// look for necessary additional replacements
		// to get a consistent and complete merged mapping

		// oClone from merged mapping
		T c1 = merged.alignedOriginalCorrespondenceFor(
				rep.to, side.flip());
		if(c1 != null) {
			T c2 = m2.alignedCorrespondenceFor(c1, side);

			// replacement
			if(c2 != null) {
				Replacement<T> crep = new Replacement<T>( side, c1, c2);
				chain.add(crep);
				chain2(crep, chain, merged, true);
				// removal
			} else {
				Removal<T> rem = new Removal<T>(side, c1);
				chain.add(rem);
				chain2(rem, chain, merged, true);
			}
		}
	}
	
	
//	protected void chain(Removal<T> rem, 
//						List<Merge<?>> chain,
//						MergeContainer<T> merged, 
//						boolean internal) {
//		// execute removal on merge container
//		merged.apply(rem, internal);
//
//		// after the removal the merged mapping is not complete
//		// -> additional insert is necessary
//		
//		if(merged.size() < reqMappings) {
//			List<Element> lhsUnmatched = picker.pickUnmatchedLHS(origCxt);
//
//			if(lhsUnmatched.isEmpty()) {
//				throw new PSORuntimeException("No unmatched LHS elements");
//			}
//
//			Collections.shuffle(lhsUnmatched);
//
//			boolean inserted = false;
//			int i = 0;
//			while(!inserted) {
//				T c = (T) m2.correspondenceFor(lhsUnmatched.get(i), ctype);
//				if(c != null) {
//					Insert<T> ins = new Insert<T>(c);
//					chain.add(ins);
//					chain(ins, chain, merged, true);
//					inserted = true;
//				}
//				i++;
//			}
//
//			if(!inserted)
//				throw new PSORuntimeException();
//		}
//
//	}
	
	
	protected void chain2(Removal<T> rem, 
			List<Merge<?>> chain,
			MergeContainer<T> merged, 
			boolean internal) {
		Side side = rem.getSide();
		
		// execute removal on merge container
		merged.apply(rem, internal);

		// after the removal the merged mapping is not complete
		// -> additional insert is necessary

		if(merged.size() < reqMappings) {
			List<Element> unmatched = picker.pickUnmatched(side, origCxt);

			if(unmatched.isEmpty()) {
				throw new PSORuntimeException("No unmatched elements on " + side);
			}

			Collections.shuffle(unmatched);

			boolean inserted = false;
			int i = 0;
			while(!inserted) {
				T c = (T) m2.correspondenceFor(unmatched.get(i), ctype);
				if(c != null) {
					Insert<T> ins = new Insert<T>(side, c);
					chain.add(ins);
					chain2(ins, chain, merged, true);
					inserted = true;
				}
				i++;
			}

			if(!inserted)
				throw new PSORuntimeException();
		}

	}
	
	
//	protected void chain(Insert<T> ins, 
//			List<Merge<?>> chain, 
//			MergeContainer<T> merged, boolean internal) {
//		// execute insert on merge container
//		merged.apply(ins);
//		
//		// external insert
//		if(!internal) {
//			// external insert leads to too much mappings
//			// -> remove colliding mapping
//			if(!(merged.size() > reqMappings)) 
//				throw new PSORuntimeException();
//
//			T c1 = merged.rhsAlignedOriginalCorrespondenceFor(ins.c);
//			if(c1 != null) {
//				Removal<T> crem = new Removal<T>(c1);
//				chain.add(crem);
//				chain(crem, chain, merged, true);
//			} else {
//				throw new PSORuntimeException();
//			}
//
//			// internal insert
//		} else {
//
//			// the internal insert can lead to an inconsistent merged
//			// mapping -> additional replacements may be necessary
//			// to get a complete and consistent merged mapping.
//			T c1 = merged.rhsAlignedOriginalCorrespondenceFor(ins.c);
//			if(c1 != null) {
//				T c2 = m2.lhsAlignedCorrespondenceFor(c1);
//
//				Replacement<T> crep = new Replacement<T>(c1, c2);
//				chain.add(crep);
//				chain(crep, chain, merged, true);
//			}
//
//		}
//	}
	
	
	protected void chain2(Insert<T> ins, 
			List<Merge<?>> chain, 
			MergeContainer<T> merged, boolean internal) {
		Side side = ins.getSide();
		
		// execute insert on merge container
		merged.apply(ins);
		
		// external insert
		if(!internal) {
			// external insert leads to too much mappings
			// -> remove colliding mapping
			if(!(merged.size() > reqMappings)) 
				throw new PSORuntimeException();

			T c1 = merged.alignedOriginalCorrespondenceFor(ins.c, side.flip());
			if(c1 != null) {
				Removal<T> crem = new Removal<T>(side, c1);
				chain.add(crem);
				chain2(crem, chain, merged, true);
			} else {
				throw new PSORuntimeException();
			}

		// internal insert
		} else {

			// the internal insert can lead to an inconsistent merged
			// mapping -> additional replacements may be necessary
			// to get a complete and consistent merged mapping.
			T c1 = merged.alignedOriginalCorrespondenceFor(ins.c, side.flip());
			if(c1 != null) {
				T c2 = m2.alignedCorrespondenceFor(c1, side);

				Replacement<T> crep = new Replacement<T>(side, c1, c2);
				chain.add(crep);
				chain2(crep, chain, merged, true);
			}

		}
	}
		
}
