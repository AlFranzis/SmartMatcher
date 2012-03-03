package com.big.tuwien.SmartMatcher.strategy.pso.swarm;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;

import com.big.tuwien.SmartMatcher.mmodel.MetaModel;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOMapping;


public class MappingConsistencyAdvice {
	private static Logger logger = Logger.getLogger(MappingConsistencyAdvice.class);
	
	private MetaModel lhsMM;
	private MetaModel rhsMM;
	
	
	public MappingConsistencyAdvice() {}
	
	
	public MappingConsistencyAdvice(MetaModel lhsMM, MetaModel rhsMM) {
		this.lhsMM = lhsMM;
		this.rhsMM = rhsMM;
	}
	
	
	 public Object check(ProceedingJoinPoint call) throws Throwable {
		 
		 logger.error("MappingConsistencyAdvice called before method invocation");
		 
		 Object result =  call.proceed();
		 
		 logger.error("MappingConsistencyAdvice called after method invocation");
		 
		 return result;
	 }

	
	private void check(PSOMapping m) {
		Helpers.check(m, lhsMM, rhsMM);
	}
}
