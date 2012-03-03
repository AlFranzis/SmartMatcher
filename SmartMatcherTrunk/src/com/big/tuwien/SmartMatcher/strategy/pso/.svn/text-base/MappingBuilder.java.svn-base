package com.big.tuwien.SmartMatcher.strategy.pso;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.strategy.pso.MetaModelBuilder.MetaModel_;

public class MappingBuilder { 
	private Map<String, PSOC2C_> cBindings = new HashMap<String,PSOC2C_>();
	private MetaModel_ lhsMM;
	private MetaModel_ rhsMM;
	
	
	public MappingBuilder() {}
	
	
	public MappingBuilder(MetaModel_ lhsMM, MetaModel_ rhsMM) {
		init(lhsMM, rhsMM);
	}
	
	
	public void init(MetaModel_ lhsMM, MetaModel_ rhsMM) {
		this.lhsMM = lhsMM;
		this.rhsMM = rhsMM;
	}

	
	public class PSOC2C_ extends PSOC2C {
		
		public PSOC2C_(Element lhs, Element rhs) {
			// no context -> set it later
			super(null, lhs, rhs);
		}
		
		public PSOC2C_ as(String name) {
			cBindings.put(name, this);
			return this;
		}
		
		public void setContext(PSOMapping context) {
			this.context = context;
		}
		
	}
	
	
	public class PSOA2A_ extends PSOA2A {

		public PSOA2A_(Element lhs, Element rhs) {
			super(null, lhs, rhs);
		}
		
		public void setContext(C1<PSOC2C> context) {
			this.context = context;
		}
		
	}

	
	public PSOMapping m(List<? extends Correspondence> cs) {
		PSOMapping m = new PSOMapping();
		for(Correspondence c : cs) {
			if(c instanceof PSOC2C_) {
				((PSOC2C_) c).setContext(m);
				m.add(c);
			}
		}
		return m;
	}
	
	
	public PSOC2C_ c2c(String lhs, String rhs) {
		return c2c(lhs, rhs, Collections.<PSOA2A_>emptyList());
	}
	
	
	public PSOC2C_ c2c(Element lhs, Element rhs) {
		return c2c(lhs, rhs, Collections.<PSOA2A_>emptyList());
	}


	public PSOC2C_ c2c(String lhsClass, String rhsClass, List<PSOA2A_> a2as) {
		PSOC2C_ c2c = new PSOC2C_(lhsMM.get(lhsClass), rhsMM.get(rhsClass));
		for(PSOA2A_ a2a : a2as) {
			a2a.setContext(C1.<PSOC2C>c(c2c));
			c2c.add(a2a);
		}
		return c2c;
	} 
	
	
	public PSOC2C_ c2c(Element lhs, Element rhs, List<PSOA2A_> a2as) {
		PSOC2C_ c2c = new PSOC2C_(lhs, rhs);
		for(PSOA2A_ a2a : a2as) {
			a2a.setContext(C1.<PSOC2C>c(c2c));
			c2c.add(a2a);
		}
		return c2c;
	}
	
	
	public PSOC2C_ c2c(String name) {
		if(!cBindings.containsKey(name))
			throw new IllegalArgumentException("No binding for: " + name);
		return cBindings.get(name);
	}
	
	
	public PSOA2A_ a2a(String lhsAtt, String rhsAtt) {
		return new PSOA2A_(lhsMM.get(lhsAtt), rhsMM.get(rhsAtt));
	}
	
	
	public PSOA2A_ a2a(Element lhs, Element rhs) {
		return new PSOA2A_(lhs, rhs);
	}
	
	
	public PSOR2R r2r(String lhsRef, String rhsRef, PSOC2C c2c1, PSOC2C c2c2) {
		PSOR2R r2r = new PSOR2R(C2.c(c2c1, c2c2), lhsMM.get(lhsRef), rhsMM.get(rhsRef));
		c2c1.add(r2r);
		c2c2.add(r2r);
		return r2r;
	}
	
	
	public PSOR2R r2r(Element lhs, Element rhs, PSOC2C c2c1, PSOC2C c2c2) {
		PSOR2R r2r = new PSOR2R(C2.c(c2c1, c2c2), lhs, rhs);
		c2c1.add(r2r);
		c2c2.add(r2r);
		return r2r;
	}
	
	
	public <T extends Correspondence> List<T> li(T ...cs) {
		return Arrays.<T>asList(cs);
	}
	
}
