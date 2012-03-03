package sm_mm.diagram.layout;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;

public class LayoutBoxPart implements LayoutPart {
	private Rectangle boundary;
	private List<LayoutOpPart> operators = new ArrayList<LayoutOpPart>();
	private List<LayoutClassPart> lhsClasses = new ArrayList<LayoutClassPart>();
	private List<LayoutClassPart> rhsClasses = new ArrayList<LayoutClassPart>();
	private Rectangle lw;
	private Rectangle mw;
	private Rectangle rw;
	
	
	@Override
	public Rectangle getBoundary() {
		return boundary;
	}
	
	
	public void setBoundary(Rectangle boundary) {
		this.boundary = boundary;
	}
	
	
	public List<LayoutOpPart> getOperators() {
		return operators;
	}
	
	
	public void addOperator(LayoutOpPart lop) {
		this.operators.add(lop);
		// update boundary
		int topY = boundary.y + boundary.height;
		int newTopY = lop.getBoundary().y + lop.getBoundary().height;
		if(newTopY > topY) {
			boundary.height = newTopY - boundary.y;
		}
	}
	
	
	public List<LayoutClassPart> getLhsClasses() {
		return lhsClasses;
	}
	
	
	public void addLhsClass(LayoutClassPart lcp) {
		lhsClasses.add(lcp);
		// update boundary
		int topY = boundary.y + boundary.height;
		int newTopY = lcp.getBoundary().y + lcp.getBoundary().height;
		if(newTopY > topY) {
			boundary.height = newTopY - boundary.y;
		}
	}
	
	
	public List<LayoutClassPart> getRhsClasses() {
		return rhsClasses;
	}
	
	
	public void addRhsClass(LayoutClassPart rcp) {
		rhsClasses.add(rcp);
		// update boundary
		int topY = boundary.y + boundary.height;
		int newTopY = rcp.getBoundary().y + rcp.getBoundary().height;
		if(newTopY > topY) {
			boundary.height = newTopY - boundary.y;
		}
	}
	
	
	public void setLeftWing(Rectangle lw) {
		this.lw = lw;
	}
	
	public Rectangle getLeftWing() {
		return lw;
	}
	
	
	public void setMidWing(Rectangle mw) {
		this.mw = mw;
	}
	
	
	public Rectangle getMidWing() {
		return mw;
	}
	
	
	public void setRightWing(Rectangle rw) {
		this.rw = rw;
	}
	
	
	public Rectangle getRightWing() {
		return rw;
	}
	
	
	public String toString() {
		return "LayoutBoxPart(" + boundary + ", " + operators + ", " 
		+ lhsClasses + ", " + rhsClasses + ")";
	}
	
}
