package smartmatcher;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class TBlockConstructSimilarity {
	// Person{firstName}
	private Block b1;
	// Student
	private Block b4;
	// Student{name}
	private Block b5;
	// Student
	private Clazz c1;
	// name
	private Attribute c2;
	
	
	@Before
	public void setUp() {
		BlockFactory bf = new DummyBlockFactory();
		List<Block> bs = bf.createBlocks();
		
		ConstructFactory cf = new DummyConstructFactory();
		List<Construct> cs = cf.createConstructs();
		
		// Person{firstName}
		b1 = bs.get(0);
		// Student
		b4 = bs.get(3);
		// Student{name}
		b5 = bs.get(4);
		// Student
		c1 = (Clazz) cs.get(0);
		// name
		c2 = (Attribute) cs.get(1);
	}
	
	
	@Test
	public void test1() {
		double sim = new BlockConstructSimilarity().sim(b1, c1);
		assertTrue(sim > 0 && sim < 1);
	}
	
	
	@Test 
	public void test2() {
		double sim = new BlockConstructSimilarity().sim(b4, c1);
		assertTrue(sim == 1.0);
	}
	
	
	@Test 
	public void test3() {
		double sim = new BlockConstructSimilarity().sim(b5, c1);
		assertTrue(sim == 1.0);
	}
	
	
	@Test 
	public void test4() {
		double sim1 = new BlockConstructSimilarity().sim(b1, c2);
		double sim2 = new BlockConstructSimilarity().sim(b5, c2);
		
		assertTrue(sim2 == 1);
		assertTrue(sim2 > sim1);
	}
	
	
	@Test 
	public void test5() {
		double sim = new BlockConstructSimilarity().sim(b4, c2);
		
		assertTrue(sim == 0);
	}
}
