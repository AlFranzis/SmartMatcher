package sm_mm.diagram.layout;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import sm_mm.Operator;

public class TBoxSorter {
	
	
	@Before
	public void setUp() {}
	
	
	@Test
	public void testSortingOnPersonFamilyModel() {
		PersonFamilyModel pfm = new PersonFamilyModel();
		
		BoxBuilder bbuilder = new BoxBuilder();
		
		Set<Operator> ops = new HashSet<Operator>(
				Arrays.asList(pfm.person2person, pfm.fn2fn, pfm.a2c));

		List<Box> boxes = bbuilder.build(ops);
		// 1 root box
		assertThat(boxes.size(),equalTo(1));
		
		Box p2pBox = getBox(boxes, pfm.person2person);
		Box a2cBox = getBox(boxes, pfm.a2c);
		
		assertNotNull(p2pBox);
		assertNotNull(a2cBox);
		

		BoxSorter bsorter = new BoxSorterImpl();
		bsorter.setBoxes(boxes);

		List<Box> sorted = bsorter.getSorted();

		// sorting flattens the box hierarchy
		assertThat(sorted.size(),equalTo(2));
		
		assertTrue(sorted.indexOf(p2pBox) < sorted.indexOf(a2cBox));	
	}
	
	
	
	@Test
	public void testSorterOnExtPersonFamilyModel() {
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
		
		assertThat(sorted.size(),equalTo(6));
		
		assertTrue(sorted.indexOf(p2pBox) < sorted.indexOf(a2cBox));
		assertTrue(sorted.indexOf(p2pBox) < sorted.indexOf(r2aBox));
		assertTrue(sorted.indexOf(p2pBox) < sorted.indexOf(worksAt2worksAtBox));
		
		assertTrue(sorted.indexOf(company2companyBox) < sorted.indexOf(worksAt2worksAtBox));
		
		assertTrue(sorted.indexOf(address2addressBox) < sorted.indexOf(r2aBox));
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
