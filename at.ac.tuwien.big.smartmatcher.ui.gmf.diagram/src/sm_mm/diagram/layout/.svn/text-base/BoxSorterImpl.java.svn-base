package sm_mm.diagram.layout;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class BoxSorterImpl implements BoxSorter {
	private List<Box> unsorted;
	private List<Box> sorted;
	private Map<Box,Integer> sortMap;
	
	
	public BoxSorterImpl() {}
	
	
	/* (non-Javadoc)
	 * @see sm_mm.diagram.layout.BoxSorter#setBoxes(java.util.Collection)
	 */
	public void setBoxes(Collection<Box> boxes) {
		this.unsorted = new Vector<Box>(boxes);
	}
	
	
	protected List<Box> sortToList(List<Box> boxes) {
		List<Box> sorted = new Vector<Box>();
		int currentLevel = 0;
		for(Box rootBox : boxes) {
			sort(rootBox, sorted, currentLevel);
		}
		return sorted;
	}
	
	
	protected Map<Box,Integer> sortToMap(List<Box> sorted) {
		Map<Box,Integer> sortMap = new HashMap<Box,Integer>();
		for(int i = 0; i < sorted.size(); i++) {
			sortMap.put(sorted.get(i), i);
		}
		return sortMap;
	}
	
	protected void doAll() {
		sorted = sortToList(unsorted);
		sortMap = sortToMap(sorted);
	}
	
	
	/* (non-Javadoc)
	 * @see sm_mm.diagram.layout.BoxSorter#getSorted()
	 */
	public List<Box> getSorted() {
		if(sorted == null)
			doAll();
		return sorted;
	}
	
	
	public Map<Box,Integer> getSortMap() {
		if(sortMap == null)
			doAll();
		return sortMap;
	}
	
	
	private void sort(Box b, List<Box> sorted, int currentLevel) {
		if(sorted.contains(b)) throw new IllegalArgumentException("Box " + b + " is already element of the sorted list " + sorted);
		
		sorted.add(currentLevel++, b);
		
		// process children with one parent
		for(Box scb : b.getContextBoxes()) {
			if(scb.getParentBoxes().size() == 1) {
				sort(scb, sorted, currentLevel);
			}
		}
		
		// process children with 2 parents
		for(Box dcb: b.getContextBoxes()) {
			if(dcb.getParentBoxes().size() > 1) {
				if(!sorted.contains(dcb))
					sort(dcb, sorted, currentLevel);
			}
		}
		
	}

}
