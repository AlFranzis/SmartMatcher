package com.big.tuwien.SmartMatcher.strategy.pso.generate;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Before;
import org.junit.Test;

import com.big.tuwien.ModelManager.ExampleStore;
import com.big.tuwien.ModelManager.imodel.ModelParsingException;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.mmodel.ResourceMetaModelFactory;
import com.big.tuwien.SmartMatcher.model.ModelManager;
import com.big.tuwien.SmartMatcher.operators.Operator;
import com.big.tuwien.SmartMatcher.operators.homogenic.a2a.A2A;
import com.big.tuwien.SmartMatcher.operators.homogenic.a2a.A2ABubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.a2a.A2AConfiguration;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2C;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CBubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration;
import com.big.tuwien.SmartMatcher.operators.homogenic.r2r.R2R;
import com.big.tuwien.SmartMatcher.operators.homogenic.r2r.R2RBubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.r2r.R2RConfiguration;
import com.big.tuwien.SmartMatcher.strategy.pso.Mapping;
import com.big.tuwien.SmartMatcher.strategy.pso.MappingGenerator;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOException;
import com.big.tuwien.SmartMatcher.strategy.pso.generate.RandomMappingGenerator;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble;
import com.big.tuwien.SmartMatcher.views.bubble.BubbleViewException;

public class TRandomMappingGenerator {
	protected ModelManager modelManager;
	protected ResourceMetaModelFactory mmFactory;
	
	
	public TRandomMappingGenerator() {
		DOMConfigurator.configure("junit_log4j.xml");
	}
	
	
	@Before
	public void setUp() throws BubbleViewException, ModelParsingException {
		modelManager = new ModelManager();
		modelManager.setExample(ExampleStore.PERSON_PERSONFAMILY);
		modelManager.init();
		
		mmFactory = new ResourceMetaModelFactory();
		mmFactory.setLHSResource(modelManager.getInputMetaModel());
		mmFactory.setRHSResource(modelManager.getOutputMetaModel());
		mmFactory.build();
	}
	
	
	@Test
	@SuppressWarnings("unchecked")
	public void testRandomGeneratorCreatesAllC2CAndA2ACombinations() throws PSOException {
		RandomMappingGenerator rmg = new RandomMappingGenerator(mmFactory.getLHSMetaModel(), mmFactory.getRHSMetaModel());
		
		Bubble<C2C> person2person = buildC2C(mmFactory.getLHSMetaModel().getClassByName("Person"), 
				mmFactory.getRHSMetaModel().getClassByName("Person"));
		Bubble<A2A> pfn2pfn = buildA2A(person2person, 
							mmFactory.getLHSMetaModel().getAttributeByQName("Person.firstName"), 
							mmFactory.getRHSMetaModel().getAttributeByQName("Person.firstName"));
		int gen = timesGenerated(rmg, 100, person2person, pfn2pfn);
		assertTrue(gen > 0);
		
		Bubble<A2A> pln2pfn = buildA2A(person2person, 
				mmFactory.getLHSMetaModel().getAttributeByQName("Person.lastName"), 
				mmFactory.getRHSMetaModel().getAttributeByQName("Person.firstName"));
		gen = timesGenerated(rmg, 100, person2person, pln2pfn);
		assertTrue(gen > 0);
		
		Bubble<C2C> person2family = buildC2C(mmFactory.getLHSMetaModel().getClassByName("Person"), 
				mmFactory.getRHSMetaModel().getClassByName("Family"));
		Bubble<A2A> pfn2fln = buildA2A(person2family, 
				mmFactory.getLHSMetaModel().getAttributeByQName("Person.firstName"), 
				mmFactory.getRHSMetaModel().getAttributeByQName("Family.lastName"));
		gen = timesGenerated(rmg, 100, person2family, pfn2fln);
		assertTrue(gen > 0);
		
		Bubble<A2A> pln2fln = buildA2A(person2family, 
				mmFactory.getLHSMetaModel().getAttributeByQName("Person.lastName"), 
				mmFactory.getRHSMetaModel().getAttributeByQName("Family.lastName"));
		gen = timesGenerated(rmg, 100, person2family, pln2fln);
		assertTrue(gen > 0);
	}
	
	
	@Test
	@SuppressWarnings("unchecked")
	public void testRandomGeneratorCreatesEquallyDistributedMappings() throws PSOException {
		RandomMappingGenerator rmg = new RandomMappingGenerator(mmFactory.getLHSMetaModel(), mmFactory.getRHSMetaModel());
		
		int EPOCHS = 1000;
		/*
		 * There are 4 possible combination
		 * If the generator creates matchings that are distributed equally every combination
		 * gets created about EPOCHS/4 times (= statistical expectation value (EXP)) 
		 */
		int EXPECTATION_VALUE = 250;
		int expectedLowerBound = EXPECTATION_VALUE / 2;
		int expectedUpperBound = EXPECTATION_VALUE * 2;
		
		Bubble<C2C> person2person = buildC2C(mmFactory.getLHSMetaModel().getClassByName("Person"), 
				mmFactory.getRHSMetaModel().getClassByName("Person"));
		Bubble<A2A> pfn2pfn = buildA2A(person2person, 
							mmFactory.getLHSMetaModel().getAttributeByQName("Person.firstName"), 
							mmFactory.getRHSMetaModel().getAttributeByQName("Person.firstName"));
		int gen = timesGenerated(rmg, EPOCHS, person2person, pfn2pfn);
		assertTrue(gen > expectedLowerBound && gen < expectedUpperBound);
		
		Bubble<A2A> pln2pfn = buildA2A(person2person, 
				mmFactory.getLHSMetaModel().getAttributeByQName("Person.lastName"), 
				mmFactory.getRHSMetaModel().getAttributeByQName("Person.firstName"));
		gen = timesGenerated(rmg, EPOCHS, person2person, pln2pfn);
		assertTrue(gen > expectedLowerBound && gen < expectedUpperBound);
		
		Bubble<C2C> person2family = buildC2C(mmFactory.getLHSMetaModel().getClassByName("Person"), 
				mmFactory.getRHSMetaModel().getClassByName("Family"));
		Bubble<A2A> pfn2fln = buildA2A(person2family, 
				mmFactory.getLHSMetaModel().getAttributeByQName("Person.firstName"), 
				mmFactory.getRHSMetaModel().getAttributeByQName("Family.lastName"));
		gen = timesGenerated(rmg, EPOCHS, person2family, pfn2fln);
		assertTrue(gen > expectedLowerBound && gen < expectedUpperBound);
		
		Bubble<A2A> pln2fln = buildA2A(person2family, 
				mmFactory.getLHSMetaModel().getAttributeByQName("Person.lastName"), 
				mmFactory.getRHSMetaModel().getAttributeByQName("Family.lastName"));
		gen = timesGenerated(rmg, EPOCHS, person2family, pln2fln);
		assertTrue(gen > expectedLowerBound && gen < expectedUpperBound);
	}
	
	
	
	@Test
	@SuppressWarnings("unchecked")
	public void testRandomGeneratorDoesNotCreateInvalidCombinations() throws PSOException {
		RandomMappingGenerator rmg = new RandomMappingGenerator(mmFactory.getLHSMetaModel(), mmFactory.getRHSMetaModel());
		
		Bubble<C2C> person2person = buildC2C(mmFactory.getLHSMetaModel().getClassByName("Person"), 
				mmFactory.getRHSMetaModel().getClassByName("Person"));
		Bubble<A2A> pfn2fln = buildA2A(person2person, 
							mmFactory.getLHSMetaModel().getAttributeByQName("Person.firstName"), 
							mmFactory.getRHSMetaModel().getAttributeByQName("Family.lastName"));
		// invalid mapping should not be created
		int gen = timesGenerated(rmg, 100, person2person, pfn2fln);
		assertTrue(gen == 0);
		
		Bubble<A2A> pln2fln = buildA2A(person2person, 
				mmFactory.getLHSMetaModel().getAttributeByQName("Person.lastName"), 
				mmFactory.getRHSMetaModel().getAttributeByQName("Family.lastName"));
		gen = timesGenerated(rmg, 100, person2person, pln2fln);
		assertTrue(gen == 0);
		
		Bubble<C2C> person2family = buildC2C(mmFactory.getLHSMetaModel().getClassByName("Person"), 
				mmFactory.getRHSMetaModel().getClassByName("Family"));
		Bubble<A2A> pfn2pfn = buildA2A(person2family, 
				mmFactory.getLHSMetaModel().getAttributeByQName("Person.firstName"), 
				mmFactory.getRHSMetaModel().getAttributeByQName("Person.firstName"));
		gen = timesGenerated(rmg, 100, person2family, pfn2pfn);
		assertTrue(gen == 0);
		
		Bubble<A2A> pln2pfn = buildA2A(person2family, 
				mmFactory.getLHSMetaModel().getAttributeByQName("Person.lastName"), 
				mmFactory.getRHSMetaModel().getAttributeByQName("Person.firstName"));
		gen = timesGenerated(rmg, 100, person2family, pln2pfn);
		assertTrue(gen == 0);
	}
	
	
	/*
	 * Runs the generator the specified amount of epochs and returns how often the given
	 * mapping was generated.
	 */
	protected int timesGenerated(MappingGenerator mg, int epochs, Bubble<? extends Operator>... bs) throws PSOException {
		int found = 0;
		for(int i = 0; i < epochs; i++) {
			Mapping m = mg.generate();
			if(m.getBubbles().containsAll(Arrays.asList(bs))) {
				found++;
			}
		}
		return found;
	}
	
	
	protected Bubble<C2C> buildC2C(Element lhs, Element rhs) {
		C2CBubble b = new C2CBubble();
		C2CConfiguration c = new C2CConfiguration();
		c.setLHSFocusElement(lhs);
		c.setRHSFocusElement(rhs);
		b.setConfiguration(c);
		return b;
	}
	
	
	protected Bubble<A2A> buildA2A(Bubble<C2C> contextC2C, Element lhs, Element rhs) {
		A2ABubble b = new A2ABubble();
		A2AConfiguration c = new A2AConfiguration();
		c.setLHSFocusElement(lhs);
		c.setRHSFocusElement(rhs);
		b.setConfiguration(c);
		b.setContext((C2CBubble)contextC2C);
		return b;
	}
	
	
	protected Bubble<R2R> buildR2R(Bubble<C2C> contextC2C1, Bubble<C2C> contextC2C2, Element lhs, Element rhs) {
		R2RBubble b = new R2RBubble();
		R2RConfiguration c = new R2RConfiguration();
		c.setLHSFocusElement(lhs);
		c.setRHSFocusElement(rhs);
		b.setConfiguration(c);
		b.setContext1((C2CBubble)contextC2C1);
		b.setContext2((C2CBubble)contextC2C2);
		return b;
	}
}
