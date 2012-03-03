package sm_mm.diagram.layout;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;

public class LayoutContainerPart implements LayoutPart {
	private List<LayoutBoxPart> boxes = new ArrayList<LayoutBoxPart>();
	private Rectangle lw;
	private Rectangle mw;
	private Rectangle rw;
	private Rectangle boundary;
	
	
	@Override
	public Rectangle getBoundary() {
		return boundary;
	}
	
	
	public void setBoundary(Rectangle boundary) {
		this.boundary = boundary;
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
	
	
	public List<LayoutBoxPart> getLayoutBoxes() {
		return boxes;
	}
	
	
	public void addBox(LayoutBoxPart lbp) {
		boxes.add(lbp);
		//update boundary
		int topY = boundary.y + boundary.height;
		int newTopY = lbp.getBoundary().y + lbp.getBoundary().height;
		if(newTopY > topY) {
			boundary.height = newTopY - boundary.y;
		}
	}
	
}
