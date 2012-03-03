package sm_mm.diagram.layout;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import sm_mm.Attribute;
import sm_mm.Element;
import sm_mm.Operator;
import sm_mm.Reference;

public class Box {
	private List<Operator> operators = new Vector<Operator>();;
	private Set<sm_mm.Class> lhsNodes = new HashSet<sm_mm.Class>();
	private Set<sm_mm.Class> rhsNodes = new HashSet<sm_mm.Class>();;
	private Set<Reference> lhsLinks = new HashSet<Reference>();
	private Set<Reference> rhsLinks = new HashSet<Reference>();
	private List<Box> contextBoxes = new Vector<Box>();
	private List<Box> parentBoxes = new Vector<Box>();
	
	
	public Box() {}
	
	
	public void addOperator(Operator op) {
		operators.add(op);
		LayoutHelpers.addOperatorNodesAndLinksToBox(op, this);
	}
	
	
	
	public boolean addNode(sm_mm.Class c) {
		if(c.isLhs()) 
			return lhsNodes.add(c);
		else 
			return rhsNodes.add(c);
	}
	
	
	public boolean addLink(Reference r) {
		if(r.isLhs()) 
			return lhsLinks.add(r);
		else 
			return rhsLinks.add(r);
	}
	
	
	public boolean isAssociated(Element e) {
		boolean lhs = e.isLhs();
		
		if(e instanceof sm_mm.Class) {
			sm_mm.Class c = (sm_mm.Class) e;
			return lhs ? lhsNodes.contains(c) : rhsNodes.contains(c); 
		} else if(e instanceof Attribute) {
			Attribute a = (Attribute) e;
			sm_mm.Class c = a.getContainer();
			return lhs ? lhsNodes.contains(c) : rhsNodes.contains(c); 
		} else if(e instanceof Reference) {
			Reference r = (Reference) e;
			return lhs ? lhsLinks.contains(r) : rhsLinks.contains(r); 
		} else {
			throw new IllegalArgumentException("Unknown element type: " + e);
		}
	}
	
	
	public boolean addAssociated(Element e) {
		// only add elements that are not already associated by parent boxes 
		if(isAssociatedWithParents(e)) return false;
		
		if(e instanceof sm_mm.Class) {
			sm_mm.Class c = (sm_mm.Class) e;
			return addNode(c);
		} else if(e instanceof Attribute) {
			Attribute a = (Attribute) e;
			sm_mm.Class c = a.getContainer();
			return addNode(c);
		} else if(e instanceof Reference) {
			Reference r = (Reference) e;
			return addLink(r);
		} else {
			throw new IllegalArgumentException("Unknown element type: " + e);
		}
	}
	
	
	private boolean isAssociatedWithParents(Element e) {
		for(Box pb : parentBoxes) {
			if(pb.isAssociated(e)) return true;
		}
		return false;
	}
	
	
	public List<Box> getParentBoxes() {
		return parentBoxes;
	}
	
	
	public int parentBoxes() {
		return parentBoxes.size();
	}


	public List<Operator> getOperators() {
		return operators;
	}


	public Set<sm_mm.Class> getLhsNodes() {
		return lhsNodes;
	}


	public Set<sm_mm.Class> getRhsNodes() {
		return rhsNodes;
	}


	public Set<Reference> getLhsLinks() {
		return lhsLinks;
	}


	public Set<Reference> getRhsLinks() {
		return rhsLinks;
	}


	public List<Box> getContextBoxes() {
		return contextBoxes;
	}
	
	
	public int contextBoxes() {
		return contextBoxes.size();
	}
	
	
}
