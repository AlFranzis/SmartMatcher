package smartmatcher;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import smartmatcher.Type.TYPE;

public class TAttributeSimilarity {
	
	@Test
	public void test1() {
		Attribute a1 =  new Attribute(1, Arrays.<Predicate<?>>asList(new Type(TYPE.INTEGER), new Name("zipCode")));
		Attribute a2 =  new Attribute(2, Arrays.<Predicate<?>>asList(new Type(TYPE.INTEGER), new Name("zip")));
		
		AttributeSimilarity as = new AttributeSimilarity();
		double asim = as.sim(a1, a2);
		
		assertTrue(asim < 1);
		assertTrue(asim > 0);
	}
	
	
	@Test
	public void test2() {
		Attribute a1 =  new Attribute(1, Arrays.<Predicate<?>>asList(new Type(TYPE.STRING), new Name("firstName")));
		Attribute a2 =  new Attribute(1, Arrays.<Predicate<?>>asList(new Type(TYPE.STRING), new Name("lastName")));
		
		Attribute a3 =  new Attribute(2, Arrays.<Predicate<?>>asList(new Type(TYPE.STRING), new Name("name")));
		
		AttributeSimilarity as = new AttributeSimilarity();
		
		double asim1 = as.sim(a1, a3);
		double asim2 = as.sim(a2, a3);
		assertTrue(asim1 != asim2);
	}
	
}
