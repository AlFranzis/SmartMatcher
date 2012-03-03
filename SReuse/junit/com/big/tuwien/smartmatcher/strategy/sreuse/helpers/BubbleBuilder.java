package com.big.tuwien.smartmatcher.strategy.sreuse.helpers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.operators.Operator;
import com.big.tuwien.SmartMatcher.operators.heterogenic.a2c.A2CBubble;
import com.big.tuwien.SmartMatcher.operators.heterogenic.a2c.A2CConfiguration;
import com.big.tuwien.SmartMatcher.operators.homogenic.a2a.A2ABubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.a2a.A2AConfiguration;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CBubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration;
import com.big.tuwien.SmartMatcher.operators.homogenic.r2r.R2RBubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.r2r.R2RConfiguration;
import com.big.tuwien.SmartMatcher.views.bubble.AbstractBubble;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble;
import com.big.tuwien.smartmatcher.strategy.sreuse.Helpers;

public class BubbleBuilder extends AbstractBubble<Operator> {
	private String opName;
	
	
	public static BubbleBuilder bubble(String name, RoleLiteral rl) {
		return null;
	}
	
	public static BubbleBuilder bubble(String name, RoleLiteral rl, List<Bubble<? extends Operator>> bs) {
		BubbleBuilder bb = bubble(name, rl);
		Set<Bubble<? extends Operator>> cxt = new HashSet<Bubble<? extends Operator>>();
		cxt.add(bb);
			
		for(Bubble<? extends Operator> b : bs) {
			b.setCxt(cxt);
		}
		return bb;
	}
	
	
	public static BubbleBuilder bubble(String name, RoleLiteral rl, 
			Bubble<? extends Operator> cxt1, Bubble<? extends Operator> cxt2) {
		BubbleBuilder bb = bubble(name, rl);
		
		Set<Bubble<? extends Operator>> cxt = new HashSet<Bubble<? extends Operator>>();
		cxt.add(cxt1);
		cxt.add(cxt2);
		
		return bb;
		
	}
	
	
	
	
	public static class RoleLiteral {
		public static RoleLiteral role(String name, Element e) {
			return null;
		}
		
		
	}


	@Override
	public void apply() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public String getOperatorName() {
		return opName;
	}


	@Override
	public boolean isCorrect() {
		return false;
	}


	@Override
	public void transform() {}
	
	
	public static C2CBubble c2c(Element lhsFocusClass, 	
			Element rhsFocusClass) {
		C2CBubble c2c = new C2CBubble();
		C2CConfiguration c2cConfig = new C2CConfiguration();
		c2cConfig.setRole(
			C2CConfiguration.Roles.lhsFocusClass, lhsFocusClass);
		c2cConfig.setRole(
			C2CConfiguration.Roles.rhsFocusClass, rhsFocusClass);
		
		c2c.setConfiguration(c2cConfig);
		return c2c;
	}
	
	
	public static A2ABubble a2a(C2CBubble cxt, Element lhsFocusAttribute, 	
			Element rhsFocusAttribute) {
		A2ABubble a2a = new A2ABubble();
		A2AConfiguration a2aConfig = new A2AConfiguration();
		a2aConfig.setRole(
			A2AConfiguration.Roles.lhsFocusAttribute, lhsFocusAttribute);
		a2aConfig.setRole(
			A2AConfiguration.Roles.rhsFocusAttribute, rhsFocusAttribute);
		
		a2a.setConfiguration(a2aConfig);
		a2a.setContext(cxt);
		a2a.setCxt(Helpers.<Bubble<? extends Operator>>asSet(cxt));
		return a2a;
	}
	
	
	public static R2RBubble r2r(C2CBubble cxt1, C2CBubble cxt2, Element lhsFocusRef, 	
			Element rhsFocusRef) {
		R2RBubble r2r = new R2RBubble();
		R2RConfiguration r2rConfig = new R2RConfiguration();
		r2rConfig.setRole(
			R2RConfiguration.Roles.lhsFocusReference, lhsFocusRef);
		r2rConfig.setRole(
			R2RConfiguration.Roles.rhsFocusReference, rhsFocusRef);
		
		r2r.setConfiguration(r2rConfig);
		r2r.setContext1(cxt1);
		r2r.setContext2(cxt2);
		r2r.setCxt(Helpers.<Bubble<? extends Operator>>asSet(cxt1, cxt2));
		return r2r;
	}
	
	
	public static A2CBubble a2c(C2CBubble cxt, Element lhsFocusAttribute, 	
			Element rhsFocusClass, Element rhsContextAttribute, 
											Element rhsContextReference) {
		A2CBubble a2c = new A2CBubble();
		A2CConfiguration a2cConfig = new A2CConfiguration();
		a2cConfig.setRole(
			A2CConfiguration.Roles.lhsFocusAttribute, lhsFocusAttribute);
		a2cConfig.setRole(
			A2CConfiguration.Roles.rhsFocusClass, rhsFocusClass);
		a2cConfig.setRole(
			A2CConfiguration.Roles.rhsContextAttribute, rhsContextAttribute);
		a2cConfig.setRole(
			A2CConfiguration.Roles.rhsContextReference, rhsContextReference);
		
		a2c.setConfiguration(a2cConfig);
		a2c.setContext(cxt);
		a2c.setCxt(Helpers.<Bubble<? extends Operator>>asSet(cxt));
		return a2c;
	}
	
}
