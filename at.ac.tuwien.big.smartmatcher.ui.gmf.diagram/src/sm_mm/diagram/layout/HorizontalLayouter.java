package sm_mm.diagram.layout;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;


public class HorizontalLayouter {
	private final static int SLOTS = 2; 
	private ZigZag lSlot;
	private ZigZag mSlot;
	private ZigZag rSlot;
	
	public LayoutContainerPart layout(LayoutContainerPart lc) {
		LayoutContainerPart newLc = new LayoutContainerPart();
		newLc.setBoundary(lc.getBoundary());
		newLc.setLeftWing(lc.getLeftWing());
		newLc.setMidWing(lc.getMidWing());
		newLc.setRightWing(lc.getRightWing());
		
		lSlot = new ZigZag(SLOTS);
		mSlot = new ZigZag(SLOTS);
		rSlot = new ZigZag(SLOTS);
		
		Slot[] lSlots = createSlots(lc.getLeftWing(), SLOTS);
		Slot[] rSlots = createSlots(lc.getRightWing(), SLOTS);
		Slot[] mSlots = createSlots(lc.getMidWing(), SLOTS);
		
		for (LayoutBoxPart lbp : lc.getLayoutBoxes()) {
			LayoutBoxPart newLbp = new LayoutBoxPart();
			newLbp.setBoundary(lbp.getBoundary());
			newLbp.setLeftWing(lbp.getLeftWing());
			newLbp.setMidWing(lbp.getMidWing());
			newLbp.setRightWing(lbp.getRightWing());
				
			// layout lhs classes
			for(LayoutClassPart lcp : lbp.getLhsClasses()) {
				LayoutClassPart newLcp = new LayoutClassPart();
				newLcp.setNode(lcp.getNode());
				newLcp.setBoundary(lcp.getBoundary());
				newLcp.setOldNodeLocation(lcp.getOldNodeLocation());
				int slot = lSlot.slot();
				Slot s = lSlots[slot];
				newLcp.setNewNodeLocation(new Point(s.startX, lcp.getNewNodeLocation().y));
				
				newLbp.addLhsClass(newLcp);
			}
	
			// layout rhs classes
			for(LayoutClassPart lcp : lbp.getRhsClasses()) {
				LayoutClassPart newLcp = new LayoutClassPart();
				newLcp.setNode(lcp.getNode());
				newLcp.setBoundary(lcp.getBoundary());
				newLcp.setOldNodeLocation(lcp.getOldNodeLocation());
				
				int slot = rSlot.slot();
				Slot s = rSlots[slot];
				newLcp.setNewNodeLocation(new Point(s.startX, lcp.getNewNodeLocation().y));
				
				newLbp.addRhsClass(newLcp);
			}
			
			// layout operators
			for(LayoutOpPart lop : lbp.getOperators()) {
				LayoutOpPart newLop = new LayoutOpPart();
				newLop.setNode(lop.getNode());
				newLop.setBoundary(lop.getBoundary());
				newLop.setOldNodeLocation(lop.getOldNodeLocation());
				
				int slot = mSlot.slot();
				Slot s = mSlots[slot];
				newLop.setNewNodeLocation(new Point(s.startX, lop.getNewNodeLocation().y));
				
				newLbp.addOperator(newLop);
			}
			
			newLc.addBox(newLbp);
		}
		
		return newLc;
	}
	
	
	private Slot[] createSlots(Rectangle boundary, int slots) {
		Slot[] _slots = new Slot[slots];
		
		int slotWidth = boundary.width / slots;
		int startX = boundary.x;
		
		for(int i = 0; i < slots; i++) {
			int endX = startX + slotWidth;
			_slots[i] = new Slot(startX, endX);
			startX = endX;
		}
		
		return _slots;
	}
	
	
	protected class Slot {
		protected int startX;
		protected int endX;
		
		protected Slot(int startX, int endX) {
			this.startX = startX;
			this.endX = endX;
		}
	}
	
	
	protected class ZigZag {
		protected int slots;
		protected int current = 0;
		protected boolean up = true;
		
		protected ZigZag(int slots) {
			this.slots = slots;
		}
		
		
		protected int slot() {
			int val = current;
			if(up) {
				current++;
				if(current == slots - 1) up = false; 
			} else {
				current--;
				if(current == 0) up = true;
			}
			return val;
		}
		
		
		protected void reset() {
			current = 0;
			up = true;
		}
	}
}
