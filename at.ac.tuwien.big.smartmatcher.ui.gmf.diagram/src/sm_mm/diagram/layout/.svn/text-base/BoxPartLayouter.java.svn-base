package sm_mm.diagram.layout;

import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeEditPart;

import sm_mm.Operator;

public class BoxPartLayouter {
	private final static int CONTAINER_MARGIN = 30;
	private final static int BOX_MARGIN = 15;
	private OpPartLayouter opPartLayouter;
	private ClassPartLayouter classPartLayouter;
	private ModelManager mmanager;
	
	
	public BoxPartLayouter(ModelManager mmanager) {
		this.mmanager = mmanager;
		opPartLayouter = new OpPartLayouter();
		classPartLayouter = new ClassPartLayouter();
	}
	
	
	public LayoutBoxPart layout(Box box, LayoutContainerPart container) {
		List<LayoutBoxPart> boxesAbove = container.getLayoutBoxes();
		
		int topY;
		if (boxesAbove.isEmpty()) {	// top box
			topY = container.getBoundary().y + CONTAINER_MARGIN;
		} else {
			// box immediately above
			LayoutBoxPart boxAbove = boxesAbove.get(boxesAbove.size()-1);
			Rectangle boxAboveBd = boxAbove.getBoundary();
			
			topY = boxAboveBd.y + boxAboveBd.height + BOX_MARGIN;
		}
		
		int bX = container.getBoundary().x;
		int bY = topY; 

		LayoutBoxPart boxPart = new LayoutBoxPart();
		boxPart.setBoundary(new Rectangle(bX, bY, container.getBoundary().width, /* no content => initial height is 0 */ 0));
		boxPart.setLeftWing(container.getLeftWing());
		boxPart.setMidWing(container.getMidWing());
		boxPart.setRightWing(container.getRightWing());
		
		for(Operator op : box.getOperators()) {
			ShapeEditPart opPart = mmanager.getOpShape(op);
			LayoutOpPart lop = opPartLayouter.layout(opPart, boxPart);
			boxPart.addOperator(lop);
		}
		
		for(sm_mm.Class c : box.getLhsNodes()) {
			ShapeEditPart cPart = mmanager.getClassShape(c);
			LayoutClassPart lcp = classPartLayouter.layout(cPart, boxPart, true);
			boxPart.addLhsClass(lcp);
		}
		
		for(sm_mm.Class c : box.getRhsNodes()) {
			ShapeEditPart cPart = mmanager.getClassShape(c);
			LayoutClassPart lcp = classPartLayouter.layout(cPart, boxPart, false);
			boxPart.addRhsClass(lcp);
		}
		
		return boxPart;
	}
}
