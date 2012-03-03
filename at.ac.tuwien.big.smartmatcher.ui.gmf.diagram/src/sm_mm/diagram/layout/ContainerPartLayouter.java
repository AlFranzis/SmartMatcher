package sm_mm.diagram.layout;



import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;

public class ContainerPartLayouter {
	private BoxPartLayouter boxPartLayouter;
	
	
	public ContainerPartLayouter(ModelManager mmanager) {
		boxPartLayouter = new BoxPartLayouter(mmanager);
	}
	
	
	public LayoutContainerPart layout(GraphicalEditPart containerEditPart, List<Box> boxes) {
		Rectangle boundary = containerEditPart.getContentPane().getBounds();
		// calculate boundaries of left, mid and right wing
		Rectangle[] wings = tripleSplit(boundary, 1, 1, 1);
		
		LayoutContainerPart lcp = new LayoutContainerPart();
		lcp.setBoundary(new Rectangle(boundary.x, boundary.y, boundary.width, /* no content => initial height is 0 */ 0));
		lcp.setLeftWing(wings[0]);
		lcp.setMidWing(wings[1]);
		lcp.setRightWing(wings[2]);
		
		for(Box b : boxes) {
			LayoutBoxPart lbp = boxPartLayouter.layout(b, lcp);
			lcp.addBox(lbp);
		}
	
		return lcp;	
	}
	
	
	private Rectangle[] tripleSplit(Rectangle r, int lProp, int mProp, int rProp) {
		int parts = lProp + mProp + rProp;
		double unit = r.preciseWidth() / parts;
		
		int lWingWidth = new Double(unit * lProp).intValue();
		Rectangle lWing = new Rectangle(r.x, r.y, lWingWidth, r.height);
		
		int mWingWidth = new Double(unit * mProp).intValue();
		Rectangle mWing = new Rectangle(r.x + lWingWidth, r.y, mWingWidth, r.height);
		
		int rWingWidth = new Double(unit * rProp).intValue();
		Rectangle rWing = new Rectangle(r.x + lWingWidth + mWingWidth, r.y, rWingWidth, r.height);
		
		Rectangle[] rect = {lWing, mWing, rWing};
		return rect;
	}

}
