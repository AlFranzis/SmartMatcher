package com.big.tuwien.smartmatcher.strategy.sreuse.helpers;

import static com.big.tuwien.smartmatcher.strategy.sreuse.Helpers.asArraySet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import com.big.tuwien.SmartMatcher.mmodel.MetaModel;
import com.big.tuwien.SmartMatcher.strategy.pso.Tuples;
import com.big.tuwien.SmartMatcher.strategy.pso.Tuples.T2;
import com.big.tuwien.smartmatcher.strategy.sreuse.Fragment;
import com.big.tuwien.smartmatcher.strategy.sreuse.FragmentMapping;
import com.big.tuwien.smartmatcher.strategy.sreuse.Operator;
import com.big.tuwien.smartmatcher.strategy.sreuse.helpers.DescBuilder.MappingDesc;
import com.big.tuwien.smartmatcher.strategy.sreuse.helpers.DescBuilder.OpDesc;
import com.big.tuwien.smartmatcher.strategy.sreuse.helpers.DescBuilder.RootOpDesc;
import com.big.tuwien.smartmatcher.strategy.sreuse.helpers.FragmentBuilder.Fragment_;
import com.big.tuwien.smartmatcher.strategy.sreuse.helpers.FragmentMappingBuilder.Operator_;

public class CombinedFragmentBuilder {
//	private EasyMM lhsMM;
//	private EasyMM rhsMM;
	private FragmentBuilder lhsFb;
	private FragmentBuilder rhsFb;
	
	
	public CombinedFragmentBuilder(MetaModel lhsMM,
			MetaModel rhsMM) {
//		this.lhsMM = new EasyMM(lhsMM);
//		this.lhsMM = new EasyMM(rhsMM);
		lhsFb = new FragmentBuilder(lhsMM);
		rhsFb = new FragmentBuilder(rhsMM);
	}
	

	public T2<Fragment,Fragment> fm(MappingDesc md) {
		T2<Set<String>,Set<String>> eNames = elements(md);
		Fragment_ lhsFrag = lhsFb.f(eNames.e0.toArray(new String[0]));
		Fragment_ rhsFrag = rhsFb.f(eNames.e1.toArray(new String[0]));
		
		FragmentMapping fm = fm(md, lhsFrag, rhsFrag);
		
		lhsFrag.setMappings(asArraySet(fm));
		rhsFrag.setMappings(asArraySet(fm));
		
		return Tuples.<Fragment,Fragment>t(lhsFrag, rhsFrag);
	}
	
	
	private static FragmentMapping fm(MappingDesc md, Fragment_ lhsFrag, 
														Fragment_ rhsFrag) {
		FragmentMappingBuilder fmb = new FragmentMappingBuilder(lhsFrag, rhsFrag);
		Map<OpDesc, Operator_> opMap = new HashMap<OpDesc, Operator_>();
		
		Set<Operator> rootOps = new CopyOnWriteArraySet<Operator>();
		for(RootOpDesc rootOd : md.getOperators()) {
			Operator rootOp = convert(rootOd, fmb, opMap);
			rootOps.add(rootOp);
		}
		
		FragmentMapping fm = new FragmentMapping();
		fm.setOperators(rootOps);
		
		return fm;
	}
	
	
	private static Operator_ convert(OpDesc od, FragmentMappingBuilder fmb, 
												Map<OpDesc, Operator_> opMap) {
		if(opMap.containsKey(od))
			return opMap.get(od);
		
		List<Operator_> cChildren = new ArrayList<Operator_>();
		for(OpDesc c : od.getChildren()) {
			Operator_ co = convert(c, fmb, opMap);
			cChildren.add(co);
		}
		
		Operator_ op = fmb.op(od.getName(), od.getLhsRoles(), od.getRhsRoles(), cChildren);
		opMap.put(od, op);
		return op;
	}
	

	private static T2<Set<String>,Set<String>> elements(MappingDesc md) {
		return elements(md.getOperators());
	}
	
	
	/*
	 * Returns all LHS and RHS element names mapped by the given op descriptions;
	 */
	private static T2<Set<String>,Set<String>> elements(Set<? extends OpDesc> ods) {
		Set<String> lhsElements = new HashSet<String>();
		Set<String> rhsElements = new HashSet<String>();
		for(OpDesc od : ods) {
			lhsElements.addAll(od.getLhsRoles().values());
			rhsElements.addAll(od.getRhsRoles().values());
			Set<OpDesc> children = od.getChildren();
			T2<Set<String>,Set<String>> cElements = elements(children);
			lhsElements.addAll(cElements.e0);
			rhsElements.addAll(cElements.e1);
		}
		return Tuples.t(lhsElements,rhsElements);
	}
	
}
