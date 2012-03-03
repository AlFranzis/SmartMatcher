package com.big.tuwien.SmartMatcher.views.emf;

import com.big.tuwien.SmartMatcher.operators.Operator;
import com.big.tuwien.SmartMatcher.operators.heterogenic.a2c.A2C;
import com.big.tuwien.SmartMatcher.operators.heterogenic.a2c.A2CBubble;
import com.big.tuwien.SmartMatcher.operators.heterogenic.a2r.A2R;
import com.big.tuwien.SmartMatcher.operators.heterogenic.a2r.A2RBubble;
import com.big.tuwien.SmartMatcher.operators.heterogenic.c2r.C2R;
import com.big.tuwien.SmartMatcher.operators.heterogenic.c2r.C2RBubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.a2a.A2A;
import com.big.tuwien.SmartMatcher.operators.homogenic.a2a.A2ABubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2C;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CBubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.r2r.R2R;
import com.big.tuwien.SmartMatcher.operators.homogenic.r2r.R2RBubble;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble;
import com.big.tuwien.SmartMatcher.views.bubble.BubbleFactory;

public class GenericBubbleFactory implements BubbleFactory {
	protected int idGenerator = 0;

	public GenericBubbleFactory() {}
	
	
	public Bubble<? extends Operator> createBubbleInstance(Operator op) {
		Class<? extends Operator> clazz = op.getClass();
		return createBubbleInstance(clazz);
	}


	public Bubble<? extends Operator> createBubbleInstance(Class<? extends Operator> op) {
		Bubble<? extends Operator> b;
		if(op.equals(C2C.class)) {
			b = new C2CBubble();
		} else if(op.equals(A2A.class)) {
			b = new A2ABubble();
		} else if(op.equals(R2R.class)) {
			b = new R2RBubble();
		} else if(op.equals(A2C.class)) {
			b = new A2CBubble();
		} else if(op.equals(A2R.class)) { 
			b = new A2RBubble();
		} else if(op.equals(C2R.class)) { 
			b = new C2RBubble();
		} else {
			throw new IllegalArgumentException("Unknown operator: " + op);
		}
			
		b.setId(idGenerator++);
		return b;
	}

}
