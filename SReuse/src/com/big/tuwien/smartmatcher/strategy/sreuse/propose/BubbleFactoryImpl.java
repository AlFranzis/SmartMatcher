package com.big.tuwien.smartmatcher.strategy.sreuse.propose;

import com.big.tuwien.SmartMatcher.operators.Configuration;
import com.big.tuwien.SmartMatcher.operators.Operator;
import com.big.tuwien.SmartMatcher.operators.heterogenic.a2c.A2C;
import com.big.tuwien.SmartMatcher.operators.heterogenic.a2c.A2CBubble;
import com.big.tuwien.SmartMatcher.operators.heterogenic.a2c.A2CConfiguration;
import com.big.tuwien.SmartMatcher.operators.heterogenic.a2c.A2CRole;
import com.big.tuwien.SmartMatcher.operators.heterogenic.a2r.A2R;
import com.big.tuwien.SmartMatcher.operators.heterogenic.a2r.A2RBubble;
import com.big.tuwien.SmartMatcher.operators.heterogenic.a2r.A2RConfiguration;
import com.big.tuwien.SmartMatcher.operators.heterogenic.a2r.A2RRole;
import com.big.tuwien.SmartMatcher.operators.heterogenic.c2r.C2R;
import com.big.tuwien.SmartMatcher.operators.heterogenic.c2r.C2RBubble;
import com.big.tuwien.SmartMatcher.operators.heterogenic.c2r.C2RConfiguration;
import com.big.tuwien.SmartMatcher.operators.heterogenic.c2r.C2RRole;
import com.big.tuwien.SmartMatcher.operators.homogenic.a2a.A2A;
import com.big.tuwien.SmartMatcher.operators.homogenic.a2a.A2ABubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.a2a.A2AConfiguration;
import com.big.tuwien.SmartMatcher.operators.homogenic.a2a.A2ARole;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2C;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CBubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CRole;
import com.big.tuwien.SmartMatcher.operators.homogenic.r2r.R2R;
import com.big.tuwien.SmartMatcher.operators.homogenic.r2r.R2RBubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.r2r.R2RConfiguration;
import com.big.tuwien.SmartMatcher.operators.homogenic.r2r.R2RRole;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble;
import com.big.tuwien.SmartMatcher.views.iterator.Role;

public class BubbleFactoryImpl implements BubbleFactory {
	protected int idGenerator = 0;


	@Override
	public <T extends Operator> Bubble<T> getBubbleInstance(Class<T> cop) {
		Bubble<T> b;
		if(cop.equals(C2C.class)) {
			b = (Bubble<T>) new C2CBubble();
		} else if(cop.equals(A2A.class)) {
			b = (Bubble<T>) new A2ABubble();
		} else if(cop.equals(R2R.class)) {
			b = (Bubble<T>) new R2RBubble();
		} else if(cop.equals(A2C.class)) {
			b = (Bubble<T>) new A2CBubble();
		} else if(cop.equals(A2R.class)) { 
			b = (Bubble<T>) new A2RBubble();
		} else if(cop.equals(C2R.class)) { 
			b = (Bubble<T>) new C2RBubble();
		} else {
			throw new IllegalArgumentException("Unknown operator: " + cop);
		}
			
		b.setId(idGenerator++);
		
		return b;
	}


	@Override
	public <T extends Operator> Bubble<T> getBubbleInstance(String opName) {
		Bubble<T> b;
		if(opName.equals(C2C.NAME)) {
			b = (Bubble<T>) new C2CBubble();
		} else if(opName.equals(A2A.NAME)) {
			b = (Bubble<T>) new A2ABubble();
		} else if(opName.equals(R2R.NAME)) {
			b = (Bubble<T>) new R2RBubble();
		} else if(opName.equals(A2C.NAME)) {
			b = (Bubble<T>) new A2CBubble();
		} else if(opName.equals(A2R.NAME)) { 
			b = (Bubble<T>) new A2RBubble();
		} else if(opName.equals(C2R.NAME)) { 
			b = (Bubble<T>) new C2RBubble();
		} else {
			throw new IllegalArgumentException("Unknown operator: " + opName);
		}
			
		b.setId(idGenerator++);
		
		return b;
	}


	@Override
	public <T extends Operator> Configuration<T> getConfigInstance(Class<T> cop) {
		Configuration<T> c;
		if(cop.equals(C2C.class)) {
			c = (Configuration<T>) new C2CConfiguration();
		} else if(cop.equals(A2A.class)) {
			c = (Configuration<T>) new A2AConfiguration();
		} else if(cop.equals(R2R.class)) {
			c = (Configuration<T>) new R2RConfiguration();
		} else if(cop.equals(A2C.class)) {
			c = (Configuration<T>) new A2CConfiguration();
		} else if(cop.equals(A2R.class)) { 
			c = (Configuration<T>) new A2RConfiguration();
		} else if(cop.equals(C2R.class)) { 
			c = (Configuration<T>) new C2RConfiguration();
		} else {
			throw new IllegalArgumentException("Unknown operator: " + cop);
		}
		
		return c;
	}


	@Override
	public <T extends Operator> Configuration<T> getConfigInstance(String opName) {
		Configuration<T> c;
		if(opName.equals(C2C.NAME)) {
			c = (Configuration<T>) new C2CConfiguration();
		} else if(opName.equals(A2A.NAME)) {
			c = (Configuration<T>) new A2AConfiguration();
		} else if(opName.equals(R2R.NAME)) {
			c = (Configuration<T>) new R2RConfiguration();
		} else if(opName.equals(A2C.NAME)) {
			c = (Configuration<T>) new A2CConfiguration();
		} else if(opName.equals(A2R.NAME)) { 
			c = (Configuration<T>) new A2RConfiguration();
		} else if(opName.equals(C2R.NAME)) { 
			c = (Configuration<T>) new C2RConfiguration();
		} else {
			throw new IllegalArgumentException("Unknown operator: " + opName);
		}
		
		return c;
	}


	@Override
	public <T extends Operator> Role<T> getRoleInstance(Class<T> cop,
			String rolename) {
		Role<T> r;
		if(cop.equals(C2C.class)) {
			r = (Role<T>) new C2CRole(rolename);
		} else if(cop.equals(A2A.class)) {
			r = (Role<T>) new A2ARole(rolename);
		} else if(cop.equals(R2R.class)) {
			r = (Role<T>) new R2RRole(rolename);
		} else if(cop.equals(A2C.class)) {
			r = (Role<T>) new A2CRole(rolename);
		} else if(cop.equals(A2R.class)) { 
			r = (Role<T>) new A2RRole(rolename);
		} else if(cop.equals(C2R.class)) { 
			r = (Role<T>) new C2RRole(rolename);
		} else {
			throw new IllegalArgumentException("Unknown operator: " + cop);
		}
		
		return r;
	}


	@Override
	public <T extends Operator> Role<T> getRoleInstance(String opName,
			String rolename) {
		Role<T> r;
		if(opName.equals(C2C.NAME)) {
			r = (Role<T>) new C2CRole(rolename);
		} else if(opName.equals(A2A.NAME)) {
			r = (Role<T>) new A2ARole(rolename);
		} else if(opName.equals(R2R.NAME)) {
			r = (Role<T>) new R2RRole(rolename);
		} else if(opName.equals(A2C.NAME)) {
			r = (Role<T>) new A2CRole(rolename);
		} else if(opName.equals(A2R.NAME)) { 
			r = (Role<T>) new A2RRole(rolename);
		} else if(opName.equals(C2R.NAME)) { 
			r = (Role<T>) new C2RRole(rolename);
		} else {
			throw new IllegalArgumentException("Unknown operator: " + opName);
		}
		
		return r;
	}

}
