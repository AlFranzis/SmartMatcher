package sm_mm.diagram.layout;

import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeEditPart;

public class ClassPartLayouter {
	private final static int CLASS_TOP_PADDING = 5;
	private final static int CLASS_BOTTOM_PADDING = 5;
	private final static int CLASS_LEFT_PADDING = 5;
	private final static int CLASS_RIGHT_PADDING = 5;
	private final static int CLASS_MARGIN = 10;
	private final static int OP_CLASS_SPACING = 5;
	private final static int CLASS_LEFT_INDENT = 20;
	private final static int CLASS_RIGHT_INDENT = 20;
	
	
	public LayoutClassPart layout(ShapeEditPart c, LayoutBoxPart containerBox, boolean lhs) {
		int x;
		List<LayoutClassPart> classesAbove;
		if(lhs) {
			classesAbove= containerBox.getLhsClasses();
			Rectangle mw = containerBox.getMidWing();
			x = mw.x - CLASS_LEFT_INDENT - c.getSize().width;
		} else {
			classesAbove= containerBox.getRhsClasses();
			Rectangle rw = containerBox.getRightWing();
			x = rw.x + CLASS_RIGHT_INDENT;
		}
		
		int topY;
		if (classesAbove.isEmpty()) {	// top class
			topY = containerBox.getBoundary().y + OP_CLASS_SPACING;
		} else {
			// class immediately above
			LayoutClassPart classAbove = classesAbove.get(classesAbove.size()-1);
			Rectangle opAboveBd = classAbove.getBoundary();
			
			topY = opAboveBd.y + opAboveBd.height + CLASS_MARGIN;
		}
		
		int bX = x - CLASS_LEFT_PADDING;
			
		int y = topY + CLASS_TOP_PADDING;
		int bY = topY; 

		int bWidth = CLASS_LEFT_PADDING + c.getSize().width + CLASS_RIGHT_PADDING;
		int bHeight = CLASS_TOP_PADDING + c.getSize().height + CLASS_BOTTOM_PADDING;

		LayoutClassPart classPart = new LayoutClassPart();
		classPart.setNode(c);
		classPart.setBoundary(new Rectangle(bX, bY, bWidth, bHeight));
		classPart.setOldNodeLocation(c.getLocation());
		classPart.setNewNodeLocation(new Point(x,y));
	
		return classPart;
	}
}
