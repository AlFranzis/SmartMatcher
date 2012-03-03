package sm_mm.diagram.layout;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeEditPart;

public class LayoutOpPart implements LayoutPart {
	private ShapeEditPart node;
	private Rectangle boundary;
	private Point oldNodeLocation;
	private Point newNodeLocation;

	
	@Override
	public Rectangle getBoundary() {
		return boundary;
	}
	
	
	public void setNodePart(ShapeEditPart node) {
		this.node = node;
	}
	
	
	public ShapeEditPart getNode() {
		return node;
	}


	public void setNode(ShapeEditPart node) {
		this.node = node;
	}


	public void setBoundary(Rectangle boundary) {
		this.boundary = boundary;
	}


	public void setOldNodeLocation(Point oldNodeLocation) {
		this.oldNodeLocation = oldNodeLocation;
	}


	public void setNewNodeLocation(Point newNodeLocation) {
		this.newNodeLocation = newNodeLocation;
	}


	public Point getOldNodeLocation() {
		return oldNodeLocation;
	}
	
	
	public Point getNewNodeLocation() {
		return newNodeLocation;
	}

	public String toString() {
		return "LayoutOpPart(" + boundary + ", " + oldNodeLocation + ", " 
		+ newNodeLocation + ", " + node.getModel() + ")";
	}
}
