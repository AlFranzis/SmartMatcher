package com.big.tuwien.SmartMatcher.strategy.pso.generate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import com.big.tuwien.SmartMatcher.AttributeElement;
import com.big.tuwien.SmartMatcher.ClassElement;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.ReferenceElement;
import com.big.tuwien.SmartMatcher.mmodel.MetaModel;
import com.big.tuwien.SmartMatcher.operators.homogenic.a2a.A2A;
import com.big.tuwien.SmartMatcher.operators.homogenic.a2a.A2ABubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.a2a.A2AConfiguration;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2C;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CBubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration;
import com.big.tuwien.SmartMatcher.operators.homogenic.r2r.R2R;
import com.big.tuwien.SmartMatcher.operators.homogenic.r2r.R2RBubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.r2r.R2RConfiguration;
import com.big.tuwien.SmartMatcher.strategy.pso.Mapping;
import com.big.tuwien.SmartMatcher.strategy.pso.MappingGenerator;
import com.big.tuwien.SmartMatcher.strategy.pso.MappingImpl;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble;

/**
 * Generates random mappings.
 * @author alex
 *
 */
public class RandomMappingGenerator implements MappingGenerator {
	protected MetaModel lhsMM;
	protected MetaModel rhsMM;
	
	
	public RandomMappingGenerator(MetaModel lhsMM, MetaModel rhsMM) {
		this.lhsMM = lhsMM;
		this.rhsMM = rhsMM;
	}


	public void setLHSMetaModel(MetaModel lhsMM) {
		this.lhsMM = lhsMM;
	}
	
	
	public void setRHSMetaModel(MetaModel rhsMM) {
		this.rhsMM = rhsMM;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Mapping generate() {
		MappingImpl mapping = new MappingImpl();
		
		List<Bubble<C2C>> c2cs = generateC2Cs();
		mapping.addBubbles((List)c2cs);
		
		for(Bubble<C2C> c2c : c2cs) {
			List<Bubble<A2A>> a2as = generateA2As(c2c);
			mapping.addBubbles((List)a2as);
		}
		
		List<Bubble<C2C>> _c2cs = new Vector<Bubble<C2C>>(c2cs);
		for(Bubble<C2C> c2c1 : c2cs) {
			_c2cs.remove(c2c1);
			for(Bubble<C2C> c2c2 : _c2cs) {		
				List<Bubble<R2R>> r2rs = generateR2Rss(c2c1, c2c2);
				mapping.addBubbles((List)r2rs);
			}
		}
		
		
//		for(Bubble<C2C> c2c1 : c2cs) {
//			for(Bubble<C2C> c2c2 : c2cs) {
//				if(c2c1 == c2c2) continue;
//				
//				List<Bubble<R2R>> r2rs = generateR2Rss(c2c1, c2c2);
//				mapping.addBubbles((List)r2rs);
//			}
//		}
		
		return mapping;
	}
	
	
	protected List<Bubble<C2C>> generateC2Cs() {
		List<Element> lhsClasses = new Vector<Element>(lhsMM.getClasses());
		List<Element> rhsClasses = new Vector<Element>(rhsMM.getClasses());
		
		// random shuffle classes to get random mappings
		Collections.shuffle(lhsClasses);
		Collections.shuffle(rhsClasses);
		
		List<Bubble<C2C>> c2cs = new ArrayList<Bubble<C2C>>();
		
		int len = Math.min(lhsClasses.size(), rhsClasses.size());
		for(int i = 0; i < len; i++) {
			C2CBubble c2c = new C2CBubble();
			C2CConfiguration c2cConfig = new C2CConfiguration();
			c2cConfig.setLHSFocusElement(lhsClasses.get(i));
			c2cConfig.setRHSFocusElement(rhsClasses.get(i));
			c2c.setConfiguration(c2cConfig);
			c2cs.add(c2c);
		}
		
		return c2cs;
	}
	
	
	protected List<Bubble<A2A>> generateA2As(Bubble<C2C> contextC2C) {
		Element lhsClass = contextC2C.getConfiguration().getLHSElements().iterator().next();
		Element rhsClass = contextC2C.getConfiguration().getRHSElements().iterator().next();
		
		List<AttributeElement> lhsAttributes = new Vector<AttributeElement>(
												((ClassElement)lhsClass.getRepresentedBy()).getAttributes()
												);
		List<AttributeElement> rhsAttributes = new Vector<AttributeElement>(
												((ClassElement)rhsClass.getRepresentedBy()).getAttributes()
												);
		// random shuffle attributes to get random mappings
		Collections.shuffle(lhsAttributes);
		Collections.shuffle(rhsAttributes);
		
		List<Bubble<A2A>> a2as = new ArrayList<Bubble<A2A>>();
		
		int len = Math.min(lhsAttributes.size(), rhsAttributes.size());
		for(int i = 0; i < len; i++) {
			A2ABubble a2a = new A2ABubble();
			A2AConfiguration a2aConfig = new A2AConfiguration();
			a2aConfig.setLHSFocusElement(lhsAttributes.get(i).getRepresents());
			a2aConfig.setRHSFocusElement(rhsAttributes.get(i).getRepresents());
			a2a.setConfiguration(a2aConfig);
			a2a.setContext((C2CBubble) contextC2C);
			a2as.add(a2a);
		}
		
		return a2as;
	}
	
	
	protected List<Bubble<R2R>> generateR2Rss(Bubble<C2C> contextC2C1, Bubble<C2C> contextC2C2) {
		Element lhsClass1 = contextC2C1.getConfiguration().getLHSElements().iterator().next();
		Element rhsClass1 = contextC2C1.getConfiguration().getRHSElements().iterator().next();
		Element lhsClass2 = contextC2C2.getConfiguration().getLHSElements().iterator().next();
		Element rhsClass2 = contextC2C2.getConfiguration().getRHSElements().iterator().next();
		
		List<Element> lhsRefs = getReferences(lhsClass1, lhsClass2);
		List<Element> rhsRefs = getReferences(rhsClass1, rhsClass2);
		
		// random shuffle references to get random mappings
		Collections.shuffle(lhsRefs);
		Collections.shuffle(rhsRefs);
			
		List<Bubble<R2R>> r2rs = new ArrayList<Bubble<R2R>>();
		
		int len = Math.min(lhsRefs.size(), rhsRefs.size());
		for(int i = 0; i < len; i++) {
			R2RBubble r2r = new R2RBubble();
			R2RConfiguration r2rConfig = new R2RConfiguration();
			r2rConfig.setLHSFocusElement(lhsRefs.get(i));
			r2rConfig.setRHSFocusElement(rhsRefs.get(i));
			r2r.setConfiguration(r2rConfig);
			r2r.setContext1((C2CBubble) contextC2C1);
			r2r.setContext2((C2CBubble) contextC2C2);
			r2rs.add(r2r);
		}
		
		return r2rs;
	}
	
	
	protected List<Element> getReferences(Element clazz1, Element clazz2) {
		List<Element> refs = new Vector<Element>();
		
		List<ReferenceElement> clazz1Refs = ((ClassElement) clazz1.getRepresentedBy()).getReferences();
		for(ReferenceElement ref : clazz1Refs) {
			if(ref.getPointsTo().getRepresents().equals(clazz2))
				refs.add(ref.getRepresents());
		}
		
		List<ReferenceElement> clazz2Refs = ((ClassElement) clazz2.getRepresentedBy()).getReferences();
		for(ReferenceElement ref : clazz2Refs) {
			if(ref.getPointsTo().getRepresents().equals(clazz1))
				refs.add(ref.getRepresents());
		}
		
		return refs;
	}
}
