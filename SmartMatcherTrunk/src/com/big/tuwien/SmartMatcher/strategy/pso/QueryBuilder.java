package com.big.tuwien.SmartMatcher.strategy.pso;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.big.tuwien.SmartMatcher.strategy.pso.QueryPSOMapping.Template;

/**
 * This class implements a DSL that allows building Query-Templates in a 
 * descriptive way.
 * @author alex
 *
 */
public class QueryBuilder {
	private Map<String,C2CTemplate> tBindings = new HashMap<String,C2CTemplate>();
	
	private final AnyA2A anyA2A = new AnyA2A();
	private final AnyR2R anyR2R = new AnyR2R();
	
	
	protected class A2ATemplate implements Template {
		private String lhs;
		private String rhs;
		
		
		public A2ATemplate(String lhs, String rhs) {
			this.lhs = lhs;
			this.rhs = rhs;
		}
		
		
		@Override
		public boolean apply(Correspondence c) {
			if(!(c instanceof PSOA2A)) return false;
			
			PSOA2A a2a = (PSOA2A) c;
			return lhs.equals(a2a.lhs.getName()) && rhs.equals(a2a.rhs.getName());
		}
	}
	
	
	/**
	 * Matches any A2A.
	 * @author alex
	 *
	 */
	protected class AnyA2A extends A2ATemplate {
		
		public AnyA2A() {
			super(null, null);
		}
		
		
		@Override
		public boolean apply(Correspondence c) {
			return c instanceof PSOA2A;
		}	
	}
	
	
	protected class R2RTemplate implements Template {
		private String lhs;
		private String rhs;
		
		
		public R2RTemplate(String lhs, String rhs) {
			this.lhs = lhs;
			this.rhs = rhs;
		}
		
		
		@Override
		public boolean apply(Correspondence c) {
			if(!(c instanceof PSOR2R)) return false;
			
			PSOR2R a2a = (PSOR2R) c;
			return lhs.equals(a2a.lhs.getName()) && rhs.equals(a2a.rhs.getName());
		}
	}
	
	
	/**
	 * Matches any R2R.
	 * @author alex
	 *
	 */
	protected class AnyR2R extends R2RTemplate {
		
		public AnyR2R() {
			super(null, null);
		}
		
		
		@Override
		public boolean apply(Correspondence c) {
			return c instanceof PSOR2R;
		}	
	}
	
	
	protected class C2CTemplate implements Template {
		private String lhs;
		private String rhs;
		private List<A2ATemplate> a2aTs = new Vector<A2ATemplate>();
		private List<R2RTemplate> r2rTs = new Vector<R2RTemplate>();
		
		
		public C2CTemplate(String lhs, String rhs, List<A2ATemplate> a2aTs) {
			this.lhs = lhs;
			this.rhs = rhs;
			this.a2aTs = a2aTs;
		}
		
		
		public void addR2R(R2RTemplate r2rT) {
			this.r2rTs.add(r2rT);
		}
		
		
		public C2CTemplate as(String name) {
			tBindings.put(name, this);
			return this;
		}
		
		
		@Override
		public boolean apply(Correspondence c) {
			if(!(c instanceof PSOC2C)) return false;
			
			PSOC2C c2c = (PSOC2C) c;
			boolean elMatch = lhs.equals(c2c.lhs.getName()) && rhs.equals(c2c.rhs.getName());
			if(!elMatch) return false;
			
			return a2asMatch(c2c) && r2rsMatch(c2c);
		}
		
		
		private boolean a2asMatch(PSOC2C c2c) {
			List<A2ATemplate> concreteA2ATs = new Vector<A2ATemplate>(a2aTs);
			// remove Anys and count them
 			int anys = 0;
			while(concreteA2ATs.contains(anyA2A())) {
				concreteA2ATs.remove(anyA2A());
				anys++;
			}
			
			List<PSOA2A> a2as = new Vector<PSOA2A>(c2c.descendents(PSOA2A.class));
			List<PSOA2A> unmatched = new Vector<PSOA2A>(a2as);
			
			for(A2ATemplate a2aT : concreteA2ATs) {
				boolean tpMatch = false;
				for(PSOA2A a2a : a2as) {
					if(a2aT.apply(a2a)) {
						tpMatch = true;
						unmatched.remove(a2a);
						break;
					}	
				}
				if(!tpMatch) return false;
			}
			
			if(unmatched.size() < anys) return false;
			
			return true;
		}
		
		
		private boolean r2rsMatch(PSOC2C c2c) {
			List<R2RTemplate> concreteR2RTs = new Vector<R2RTemplate>(r2rTs);
			// remove Anys and count them
 			int anys = 0;
			while(concreteR2RTs.contains(anyR2R())) {
				concreteR2RTs.remove(anyR2R());
				anys++;
			}
			
			List<PSOR2R> r2rs = new Vector<PSOR2R>(c2c.descendents(PSOR2R.class));
			List<PSOR2R> unmatched = new Vector<PSOR2R>(r2rs);
			
			for(R2RTemplate r2rT : concreteR2RTs) {
				boolean tpMatch = false;
				for(PSOR2R r2r : r2rs) {
					if(r2rT.apply(r2r)) {
						tpMatch = true;
						unmatched.remove(r2r);
						break;
					}	
				}
				if(!tpMatch) return false;
			}
			
			if(unmatched.size() < anys) return false;
			
			return true;
		}
	}
	
	
	public Template cs(final List<? extends Template> ts) {
		Template combined = 
			new Template() {
				@Override
				public boolean apply(Correspondence c) {
					for(Template t : ts) {
						if(t.apply(c))
							return true;
					}
					return false;
				}	
			};
		
		return combined;
	}
	
	
	public C2CTemplate c2c(String lhs, String rhs) {
		return new C2CTemplate(lhs, rhs, Collections.<A2ATemplate>emptyList());
	}
	
	
	public C2CTemplate c2c(String lhs, String rhs, List<A2ATemplate> a2aTs) {
		return new C2CTemplate(lhs, rhs, a2aTs);
	}
	
	
	public C2CTemplate c2c(String lhs, String rhs, List<A2ATemplate> a2aTs, List<R2RTemplate> r2rTs) {
		C2CTemplate c2cT =  new C2CTemplate(lhs, rhs, a2aTs);
		for(R2RTemplate r2rT : r2rTs) {
			c2cT.addR2R(r2rT);
		}
		return c2cT;
	}
	
	
	public C2CTemplate c2c(String name) {
		if(!tBindings.containsKey(name))
			throw new IllegalArgumentException("No binding for: " + name);
		return tBindings.get(name);
	}
	
	
	public A2ATemplate a2a(String lhs, String rhs) {
		A2ATemplate a2aT = new A2ATemplate(lhs, rhs);
		return a2aT;
	}
	
	
	public R2RTemplate r2r(String lhs, String rhs) {
		R2RTemplate r2rT = new R2RTemplate(lhs, rhs);
		return r2rT;
	}
	
	
	public Template r2r(String lhs, String rhs, C2CTemplate c2c1, C2CTemplate c2c2) {
		R2RTemplate r2rT = new R2RTemplate(lhs, rhs);
		c2c1.addR2R(r2rT);
		c2c1.addR2R(r2rT);
		return new Template(){
			@Override
			public boolean apply(Correspondence c) {
				return false;
			}
			
		};
	}
	
	
	public A2ATemplate anyA2A() {
		return anyA2A;
	}
	
	
	public List<A2ATemplate> anyA2A(int n) {
		return Collections.nCopies(n, anyA2A());
	}
	
	
	public R2RTemplate anyR2R() {
		return anyR2R;
	}
	
	
	public <T extends Template> List<T> li(T ...cs) {
		return Arrays.<T>asList(cs);
	}
}
