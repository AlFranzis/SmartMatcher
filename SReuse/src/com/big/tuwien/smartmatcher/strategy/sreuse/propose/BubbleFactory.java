package com.big.tuwien.smartmatcher.strategy.sreuse.propose;

import com.big.tuwien.SmartMatcher.operators.Configuration;
import com.big.tuwien.SmartMatcher.operators.Operator;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble;
import com.big.tuwien.SmartMatcher.views.iterator.Role;

public interface BubbleFactory {
	public <T extends Operator> Bubble<T> getBubbleInstance(Class<T> cop);
	
	public <T extends Operator> Bubble<T> getBubbleInstance(String opName);
	
	public <T extends Operator> Configuration<T> getConfigInstance(Class<T> cop); 
	
	public <T extends Operator> Configuration<T> getConfigInstance(String opName); 
	
	public <T extends Operator> Role<T> getRoleInstance(Class<T> cop, String rolename);
	
	public <T extends Operator> Role<T> getRoleInstance(String opName, String rolename);
}
