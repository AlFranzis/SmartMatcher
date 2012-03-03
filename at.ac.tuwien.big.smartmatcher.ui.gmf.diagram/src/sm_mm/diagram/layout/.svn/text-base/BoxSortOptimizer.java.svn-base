package sm_mm.diagram.layout;

import java.util.Collection;
import java.util.List;
import java.util.Vector;


/**
 * This box sorter ensures that boxes with 2 parents are NOT positioned after both parents.
 */
public class BoxSortOptimizer implements BoxSorter {
	private List<Box> boxes;
	
	
	@Override
	public List<Box> getSorted() {
		Vector<Box> nSorted = new Vector<Box>(boxes);

		// no iterator because we modify nSorted during traversal
		for(int i = 0; i < nSorted.size(); i++) {
			Box b = nSorted.get(i);
			// only consider boxes with 2 parents
			if(b.getParentBoxes().size() == 2) {
				// position of the parents
				int p1Pos = nSorted.indexOf(b.getParentBoxes().get(0));
				int p2Pos = nSorted.indexOf(b.getParentBoxes().get(1));
				
				if(p1Pos == -1 || p2Pos == -1) 
					throw new IllegalArgumentException("Sructural error in box structure");
				
				int lastPPos = p1Pos > p2Pos ? p1Pos : p2Pos;
				// if both parents are positioned before box 
				// then move box to position before last parent
				if(lastPPos < i) {
					nSorted.remove(i);
					nSorted.add(lastPPos, b);
				}

			}
		}
		return nSorted;
	}

	
	@Override
	public void setBoxes(Collection<Box> boxes) {
		this.boxes = new Vector<Box>(boxes);
	}

}
