package com.big.tuwien.SmartMatcher.strategy.pso.generate;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Before;
import org.junit.Test;

import com.big.tuwien.ModelManager.ExampleStore;
import com.big.tuwien.ModelManager.imodel.ModelParsingException;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.mmodel.ResourceMetaModelFactory;
import com.big.tuwien.SmartMatcher.model.ModelManager;
import com.big.tuwien.SmartMatcher.operators.Operator;
import com.big.tuwien.SmartMatcher.operators.homogenic.HomogenicConfiguration;
import com.big.tuwien.SmartMatcher.strategy.pso.Mapping;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble;
import com.big.tuwien.SmartMatcher.views.bubble.BubbleViewException;

public class TRandomMappingGenerator2 {
	protected ModelManager modelManager;
	protected ResourceMetaModelFactory mmFactory;
	
	
	public TRandomMappingGenerator2() {
		DOMConfigurator.configure("junit_log4j.xml");
	}
	
	
	@Before
	public void setUp() throws BubbleViewException, ModelParsingException {
		modelManager = new ModelManager();
		modelManager.setExample(ExampleStore.PSO_SIMPLE);
		modelManager.init();
		
		mmFactory = new ResourceMetaModelFactory();
		mmFactory.setLHSResource(modelManager.getInputMetaModel());
		mmFactory.setRHSResource(modelManager.getOutputMetaModel());
		mmFactory.build();
	}
	
	
	//@Test
	public void testArithmetic() {
		int x = Integer.MAX_VALUE + 5001 + 10000;
		System.out.println(x);
		int y = Integer.MAX_VALUE + 10000 + 5001;
		assertTrue(x == y);
		System.out.println(y); 
	}
	
	
	//@Test
	public void testHashCode() {
		RandomMappingGenerator rmg = new RandomMappingGenerator(mmFactory.getLHSMetaModel(), mmFactory.getRHSMetaModel());
		Mapping m = rmg.generate();
		assertTrue(m.getBubbles().hashCode() == new HashSet(m.getBubbles()).hashCode());
	}
	
	
	//@Test
	public void testHashCode2() {
		RandomMappingGenerator rmg = new RandomMappingGenerator(mmFactory.getLHSMetaModel(), mmFactory.getRHSMetaModel());
		Mapping m = rmg.generate();
		assertTrue(m.getBubbles().hashCode() ==  m.hashCode());
	}
	
	@Test
	public void testCountUniqueCombinations() throws IOException {
		RandomMappingGenerator rmg = new RandomMappingGenerator(mmFactory.getLHSMetaModel(), mmFactory.getRHSMetaModel());
		
		System.out.println("LHS elements and IDs");
		for(Element e: mmFactory.getLHSMetaModel().getElements()) {
			System.out.println(e.getName() + " " + e.getId());
		}
		
		
		System.out.println("RHS elements and IDs");
		for(Element e: mmFactory.getRHSMetaModel().getElements()) {
			System.out.println(e.getName() + " " + e.getId());
		}
		
		
		int epochs = 100000;
		HashSet<Mapping> ms = new HashSet<Mapping>(2000);
		HashSet<String> sms = new HashSet<String>(2000);
		for(int i = 0; i < epochs; i++) {
			Mapping m = rmg.generate();
			ms.add(m);
			String s = mappingToString(m);
			sms.add(s);
		}
		
		
//		for(Mapping m1 : ms) {
//			for(Mapping m2 : ms) {
//				if(!(m1 == m2)) {
//					if(m1.equals(m2))
//						throw new IllegalArgumentException("Ouch equals() = true for m1: " + m1 + " m2: " + m2);
//					if(m1.hashCode() == m2.hashCode()) {
//						String ms1 = mappingToString2(m1);
//						System.out.println(ms1);
//						String ms2 = mappingToString2(m2);
//						System.out.println(ms2);
//						//throw new IllegalArgumentException("Ouch hashCode() the same for m1: " + m1 + " m2: " + m2);
//					}
//				}
//			}
//		}
		
		// print(ms);
		
		System.out.println("Different mappings generated: " + ms.size());
		System.out.println("Different text mappings: " + sms.size());
	}
	
	
	private void print(Collection<Mapping> ms) {
		for(Mapping m : ms) {
			String s = mappingToString(m);
			System.out.println(s);
		}
	}
	
	
	private String mappingToString(Mapping m) {
		ArrayList<Bubble<? extends Operator>> bs = new ArrayList<Bubble<? extends Operator>>(m.getBubbles());
		Collections.sort(bs, new Comparator<Bubble<? extends Operator>>() {

			@Override
			public int compare(Bubble<? extends Operator> b1,
					Bubble<? extends Operator> b2) {
				Element lhsE1 = ((HomogenicConfiguration<? extends Operator>) b1.getConfiguration()).getLHSFocusElement();
				Element lhsE2 = ((HomogenicConfiguration<? extends Operator>) b2.getConfiguration()).getLHSFocusElement();
				
				String op1 = b1.getOperatorName();
				String op2 = b2.getOperatorName();
				int opRank1 = opRank(op1);
				int opRank2 = opRank(op2);
				
				if(opRank1 == opRank2) {
					int eRank1 = elemRank(lhsE1, opRank1);
					int eRank2 = elemRank(lhsE2, opRank2);
					return eRank1 - eRank2;
				}
				
				return opRank1 - opRank2;
			}
			
			
			private int opRank(String op) {
				if(op.equalsIgnoreCase("C2C")) return 1;
				if(op.equalsIgnoreCase("A2A")) return 2;
				if(op.equalsIgnoreCase("R2R")) return 3;
				throw new IllegalArgumentException();
			}
			
			
			private int elemRank(Element e, int opRank) {
				String n = e.getName();
				//class
				Pattern p = Pattern.compile("\\d");
				if(opRank == 1) {
					Matcher m = p.matcher(n);
					if(!m.find()) throw new IllegalArgumentException();
					String num = m.group();
					return Integer.parseInt(num);
				//attribute
				} else if(opRank == 2) {
					Matcher m = p.matcher(n);
					if(!m.find()) throw new IllegalArgumentException();
					String num = m.group();
					return Integer.parseInt(num);
				//relation
				} else if(opRank == 3) {
					Matcher m = p.matcher(n);
					if(!m.find()) throw new IllegalArgumentException();
					String num = m.group();
					return Integer.parseInt(num);
				} else {
					throw new IllegalArgumentException();
				}
			}
		});
		
		StringBuffer buf = new StringBuffer();
		boolean first = true;
		for(Bubble<? extends Operator> b : bs) {
			if(!first) buf.append(" ");
			HomogenicConfiguration<?> c = (HomogenicConfiguration<?>) b.getConfiguration();
			Element lhs = c.getLHSFocusElement();
			Element rhs = c.getRHSFocusElement();
			buf.append(lhs + "-" + rhs);
			first = false;
		}
		buf.append(" " + m.hashCode());
		return buf.toString();
	}
	
	
	private String mappingToString2(Mapping m) {
		ArrayList<Bubble<? extends Operator>> bs = new ArrayList<Bubble<? extends Operator>>(m.getBubbles());
		Collections.sort(bs, new Comparator<Bubble<? extends Operator>>() {

			@Override
			public int compare(Bubble<? extends Operator> b1,
					Bubble<? extends Operator> b2) {
				Element lhsE1 = ((HomogenicConfiguration<? extends Operator>) b1.getConfiguration()).getLHSFocusElement();
				Element lhsE2 = ((HomogenicConfiguration<? extends Operator>) b2.getConfiguration()).getLHSFocusElement();
				
				String op1 = b1.getOperatorName();
				String op2 = b2.getOperatorName();
				int opRank1 = opRank(op1);
				int opRank2 = opRank(op2);
				
				if(opRank1 == opRank2) {
					int eRank1 = elemRank(lhsE1, opRank1);
					int eRank2 = elemRank(lhsE2, opRank2);
					return eRank1 - eRank2;
				}
				
				return opRank1 - opRank2;
			}
			
			
			private int opRank(String op) {
				if(op.equalsIgnoreCase("C2C")) return 1;
				if(op.equalsIgnoreCase("A2A")) return 2;
				if(op.equalsIgnoreCase("R2R")) return 3;
				throw new IllegalArgumentException();
			}
			
			
			private int elemRank(Element e, int opRank) {
				String n = e.getName();
				//class
				Pattern p = Pattern.compile("\\d");
				if(opRank == 1) {
					Matcher m = p.matcher(n);
					if(!m.find()) throw new IllegalArgumentException();
					String num = m.group();
					return Integer.parseInt(num);
				//attribute
				} else if(opRank == 2) {
					Matcher m = p.matcher(n);
					if(!m.find()) throw new IllegalArgumentException();
					String num = m.group();
					return Integer.parseInt(num);
				//relation
				} else if(opRank == 3) {
					Matcher m = p.matcher(n);
					if(!m.find()) throw new IllegalArgumentException();
					String num = m.group();
					return Integer.parseInt(num);
				} else {
					throw new IllegalArgumentException();
				}
			}
		});
		
		StringBuffer buf = new StringBuffer();
		boolean first = true;
		for(Bubble<? extends Operator> b : bs) {
			if(!first) buf.append(" ");
			HomogenicConfiguration<?> c = (HomogenicConfiguration<?>) b.getConfiguration();
			Element lhs = c.getLHSFocusElement();
			Element rhs = c.getRHSFocusElement();
			buf.append(lhs + "-" + rhs + " " + b.hashCode());
			first = false;
		}
		buf.append(" " + m.hashCode());
		return buf.toString();
	}
	
	
	private void writeToFile(Set<String> sms) throws IOException {
		File f = new File("/home/alex/ms7.txt");
		FileWriter fw = new FileWriter(f);
		for(String s : sms) 
			fw.append(s+"\n");
	}
	
	
}
