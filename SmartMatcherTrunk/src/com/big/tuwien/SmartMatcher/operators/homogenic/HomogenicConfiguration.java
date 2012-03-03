package com.big.tuwien.SmartMatcher.operators.homogenic;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.operators.Configuration;
import com.big.tuwien.SmartMatcher.operators.Operator;

public interface HomogenicConfiguration<R extends Operator> extends Configuration<R> {
	
	public Element getLHSFocusElement();
	
	public Element getRHSFocusElement();
	
	public void setLHSFocusElement(Element e);
	
	public void setRHSFocusElement(Element e);
}
