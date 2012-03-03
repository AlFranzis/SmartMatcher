package sm_mm.diagram.layout;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import sm_mm.Operator;

public class TBoxSortOptimizer {
	
	
	@Before
	public void setUp() {}
	
	
	@Test
	public void testSortOptimizerOnExtPersonFamilyModel() {
		ExtPersonFamilyModel epfm = new ExtPersonFamilyModel();
		BoxBuilder bbuilder = new BoxBuilder();
		
		Set<Operator> ops = new HashSet<Operator>(
						Arrays.asList(epfm.person2person, epfm.fn2fn, epfm.a2c, epfm.address2address, 
						epfm.street2street, epfm.city2city, epfm.country2country, epfm.r2a, epfm.comp2comp, 
						epfm.cname2cname, epfm.worksAt2worksAt));
		
		List<Box> boxes = bbuilder.build(ops);
		assertThat(boxes.size(),equalTo(3));
		
		Box p2pBox = getBox(boxes, epfm.person2person);
		Box a2cBox = getBox(boxes, epfm.a2c);
		Box address2addressBox = getBox(boxes, epfm.address2address);
		Box r2aBox = getBox(boxes, epfm.r2a);
		Box worksAt2worksAtBox = getBox(boxes, epfm.worksAt2worksAt);
		Box company2companyBox = getBox(boxes, epfm.comp2comp);
		
		BoxSorter bsorter = new BoxSorterImpl();
		bsorter.setBoxes(boxes);
		List<Box> sorted = bsorter.getSorted();
		
		BoxSorter bsopt = new BoxSortOptimizer();
		bsopt.setBoxes(sorted);
		List<Box> optSorted = bsopt.getSorted();
		
		// VERIFY 
		
		// sort optimization does not affect the number of boxes
		assertThat(optSorted.size(),equalTo(sorted.size()));
		
		// A2C has one parent: P2P
		// sort optimizer does not affect the ordering rule that
		// boxes with one parent are placed after parent
		assertTrue(optSorted.indexOf(p2pBox) < optSorted.indexOf(a2cBox));
		
		int posP2P = optSorted.indexOf(p2pBox);
		int posR2A = optSorted.indexOf(r2aBox);
		int posA2A = optSorted.indexOf(address2addressBox);
		int posC2C = optSorted.indexOf(company2companyBox);
		int posW2W = optSorted.indexOf(worksAt2worksAtBox);
		
		// R2A has two parents: P2P and A2A
		// => sort optimizer places R2A between it's parents
		assertTrue("R2A is placed between P2P and A2A",
				(posP2P < posR2A) && (posR2A < posA2A)
				||
				(posA2A < posR2A) && (posR2A < posP2P)
		);
		
		// W2W has two parents: P2P and C2C
		// => sort optimizer places W2W between it's parents
		assertTrue("W2W is placed between P2P and C2C",
				(posP2P < posW2W) && (posW2W < posC2C)
				||
				(posC2C < posW2W) && (posW2W < posP2P)
		);
			
	}
	
	
	/*
	 * Returns the box that contains the given operator.
	 */
	private Box getBox(List<Box> boxes, Operator op) {
		for(Box b : boxes) {
			if(b.getOperators().contains(op)) return b;
			
			Box _b = getBox(b.getContextBoxes(), op);
			if(_b != null) return _b;
		}
		return null;
	}
	
	
	
	
}
