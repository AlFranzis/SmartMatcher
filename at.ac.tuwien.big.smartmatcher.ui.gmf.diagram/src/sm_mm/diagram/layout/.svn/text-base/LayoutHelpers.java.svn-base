package sm_mm.diagram.layout;

import sm_mm.Element;
import sm_mm.Operator;

public class LayoutHelpers {

	public static void addOperatorNodesAndLinksToBox(Operator op, Box b) {
		for(Element e : op.getLhsRoles()) {
			b.addAssociated(e);
		}
		
		for(Element e : op.getRhsRoles()) {
			b.addAssociated(e);
		}	
	}
}
