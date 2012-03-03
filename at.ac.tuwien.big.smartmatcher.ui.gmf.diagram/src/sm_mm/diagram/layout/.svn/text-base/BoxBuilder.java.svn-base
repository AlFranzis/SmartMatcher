package sm_mm.diagram.layout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import sm_mm.ContextOperator;
import sm_mm.Operator;

public class BoxBuilder {
	private Map<Operator,Box> ops2boxes = new HashMap<Operator,Box>();
	
	/**
	 * Builds boxes out of the given operators.
	 * @param ops List of operators.
	 * @return A list of root boxes. Root boxes are boxes that are not children of other boxes.
	 * Root boxes itself may contain child boxes.
	 */
	public List<Box> build(Set<Operator> ops) {
		List<Box> boxes = new Vector<Box>();
		
		for(Operator op : ops) {
			// root operator that is not in context of another one
			if(op.getParents().isEmpty()) {
				Box b = build(op);
				boxes.add(b);		
			}
		}
		
		return boxes;
	}
	
	
	/**
	 * Builds a box for the given operator.
	 * @param op
	 * @return
	 */
	public Box build(Operator op) {
		return build(op, null);
	}
	
	
	protected Box build(Operator op, Box parent) {
		Box b = new Box();
		if(parent != null) b.getParentBoxes().add(parent);
		b.addOperator(op);
		// check for child operators to add
		if(op instanceof ContextOperator) {
			ContextOperator cop = (ContextOperator) op;
			
			for(Operator o : cop.getContextMappings()) {
				// child operators w/o context that have one parent are added to this box 
				if( !(o instanceof ContextOperator) && (o.getParents().size() == 1) ) {	
					b.addOperator(o);
				// child operators with context or with 2 parents get a box on their own
				} else {
					Box child = ops2boxes.get(o);
					
					if(child != null) 
						child.getParentBoxes().add(b);
					else {
						child = build(o,b);
						ops2boxes.put(o, child);
					}
					
					b.getContextBoxes().add(child);
				}
				
			}
		}
		return b;
	}
}
