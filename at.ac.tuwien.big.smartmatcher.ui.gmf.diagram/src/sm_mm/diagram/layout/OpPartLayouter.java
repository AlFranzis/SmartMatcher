package sm_mm.diagram.layout;

import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeEditPart;

public class OpPartLayouter {
	private final static int OP_TOP_PADDING = 5;
	private final static int OP_BOTTOM_PADDING = 5;
	private final static int OP_LEFT_PADDING = 5;
	private final static int OP_RIGHT_PADDING = 5;
	private final static int OP_MARGIN = 10;
	
	
	public LayoutOpPart layout(ShapeEditPart op, LayoutBoxPart containerBox) {
		List<LayoutOpPart> opsAbove = containerBox.getOperators();
		
		int topY;
		if (opsAbove.isEmpty()) {	// top operator
			topY = containerBox.getBoundary().y;
		} else {
			// op immediately above
			LayoutOpPart opAbove = opsAbove.get(opsAbove.size()-1);
			Rectangle opAboveBd = opAbove.getBoundary();
			
			topY = opAboveBd.y + opAboveBd.height + OP_MARGIN;
		}
		
		Rectangle mw = containerBox.getMidWing();
		int midX = mw.x + mw.width/2;
		int x = midX - op.getSize().width/2;
		int bX = x - OP_LEFT_PADDING;
			
		int y = topY + OP_TOP_PADDING;
		int bY = topY; 

		int bWidth = OP_LEFT_PADDING + op.getSize().width + OP_RIGHT_PADDING;
		int bHeight = OP_TOP_PADDING + op.getSize().height + OP_BOTTOM_PADDING;

		LayoutOpPart opPart = new LayoutOpPart();
		opPart.setNode(op);
		opPart.setBoundary(new Rectangle(bX, bY, bWidth, bHeight));
		opPart.setOldNodeLocation(op.getLocation());
		opPart.setNewNodeLocation(new Point(x,y));
	
		return opPart;
	}
}
