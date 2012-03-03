package sm_mm.diagram.layout;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import sm_mm.Operator;
import sm_mm.Sm_mmFactory;

public class TBoxBuilder {
	protected Sm_mmFactory factory;
	protected BuildingHelper bh;
	
	
	@Before
	public void setUp() {
		factory = Sm_mmFactory.eINSTANCE;
		bh = new BuildingHelper(factory);
	}
	
	
	@Test
	public void testBuilderOnPersonFamilyModel() {
		PersonFamilyModel pfm = new PersonFamilyModel();
		
		BoxBuilder bbuilder = new BoxBuilder();
		
		// build box for C2C
		Box c2cBox = bbuilder.build(pfm.person2person);
		
		// check correct associated lhs elements
		assertThat(c2cBox.getLhsNodes(), hasItem(pfm.lPerson));
		assertThat(c2cBox.getLhsNodes(), not(hasItem(pfm.rPerson)));
		assertThat(c2cBox.getLhsNodes(), not(hasItem(pfm.rFamily)));
		
		// check correct associated rhs elements
		assertThat(c2cBox.getRhsNodes(), hasItem(pfm.rPerson));
		assertThat(c2cBox.getRhsNodes(), not(hasItem(pfm.lPerson)));
		assertThat(c2cBox.getRhsNodes(), not(hasItem(pfm.rFamily)));
		
		// check contained operators
		assertThat(c2cBox.getOperators(), hasItem((Operator) pfm.person2person));
		assertThat(c2cBox.getOperators(), hasItem((Operator) pfm.fn2fn));
		assertThat(c2cBox.getOperators().size(), equalTo(2));
		
		assertThat(c2cBox.getContextBoxes().size(), equalTo(1));
		
		Box a2cBox = c2cBox.getContextBoxes().get(0);
		assertThat(a2cBox.getLhsNodes().size(), equalTo(0));
		
		assertThat(a2cBox.getRhsNodes().size(), equalTo(1));
		assertThat(a2cBox.getRhsNodes(), hasItem(pfm.rFamily));
		
		assertThat(a2cBox.getOperators(), hasItem((Operator) pfm.a2c));
		assertThat(a2cBox.getOperators().size(), equalTo(1));
		
	}
	
	
	@Test
	public void testBuilderOnExtPersonFamilyModel() {
		ExtPersonFamilyModel epfm = new ExtPersonFamilyModel();
		
		BoxBuilder bbuilder = new BoxBuilder();
		
		// build box for C2C
		Box person2personBox = bbuilder.build(epfm.person2person);
		
		// check correct associated lhs elements
		assertThat(person2personBox.getLhsNodes(), hasItem(epfm.lPerson));
		assertThat(person2personBox.getLhsNodes(), not(hasItem(epfm.rPerson)));
		assertThat(person2personBox.getLhsNodes(), not(hasItem(epfm.rFamily)));
		
		// check correct associated rhs elements
		assertThat(person2personBox.getRhsNodes(), hasItem(epfm.rPerson));
		assertThat(person2personBox.getRhsNodes(), not(hasItem(epfm.lPerson)));
		assertThat(person2personBox.getRhsNodes(), not(hasItem(epfm.rFamily)));
		
		// check contained operators
		assertThat(person2personBox.getOperators(), hasItem((Operator) epfm.person2person));
		assertThat(person2personBox.getOperators(), hasItem((Operator) epfm.fn2fn));
		assertThat(person2personBox.getOperators().size(), equalTo(2));
		
		assertThat(person2personBox.getContextBoxes().size(), equalTo(3));
		
		
		Set<Operator> ops = new HashSet<Operator>();
		ops.addAll(Arrays.asList(epfm.person2person, epfm.fn2fn, epfm.a2c, epfm.address2address, 
				epfm.street2street, epfm.city2city, epfm.country2country, epfm.r2a, epfm.comp2comp, 
				epfm.cname2cname, epfm.worksAt2worksAt));
		
		List<Box> boxes = bbuilder.build(ops);
		assertThat(boxes.size(), equalTo(3));
		
	}
	
	
}
