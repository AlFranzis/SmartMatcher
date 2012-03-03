package com.big.tuwien.smartmatcher.strategy.sreuse.helpers;

import static com.big.tuwien.smartmatcher.strategy.sreuse.Helpers.asArraySet;
import static com.big.tuwien.smartmatcher.strategy.sreuse.Literals.map;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Vector;

import com.big.tuwien.SmartMatcher.EcoreElement;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.mmodel.MetaModel;
import com.big.tuwien.SmartMatcher.operators.Configuration;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble;
import com.big.tuwien.SmartMatcher.views.iterator.Role;
import com.big.tuwien.smartmatcher.strategy.sreuse.Operator;
import com.big.tuwien.smartmatcher.strategy.sreuse.ReuseRuntimeException;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLElement;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLOperator;
import com.big.tuwien.smartmatcher.strategy.sreuse.helpers.DescBuilder.OpDesc;

public class TestHelpers {

	public static List<Element> sortByName(Collection<Element> es) {
		List<Element> sorted = new Vector<Element>(es);
		Collections.sort(
			sorted, 
			new Comparator<Element>() {
				@Override
				public int compare(Element e1, Element e2) {
					return e1.getName().compareToIgnoreCase(e2.getName());
				}	
			}
		);
		
		return sorted;
	}
	
	
	public static List<EcoreElement> sortByName2(
								Collection<? extends EcoreElement> es) {
		List<EcoreElement> sorted = new Vector<EcoreElement>(es);
		Collections.sort(
			sorted, 
			new Comparator<EcoreElement>() {
				@Override
				public int compare(EcoreElement e1, EcoreElement e2) {
					return e1.getRepresents().getName()
						.compareToIgnoreCase(e2.getRepresents().getName());
				}	
			}
		);

		return sorted;
	}


	public static <T extends EcoreElement> List<T> sortByName3(
			Collection<T> es) {	
		List<T> sorted = new Vector<T>(es);
		Collections.sort(
			sorted, 
			new Comparator<EcoreElement>() {
				@Override
				public int compare(EcoreElement e1, EcoreElement e2) {
					return e1.getRepresents().getName()
						.compareToIgnoreCase(e2.getRepresents().getName());
				}	
			}
		);

		return sorted;
	}
	
	
	public static List<Operator> sortOpsByName(Collection<Operator> es) {
		List<Operator> sorted = new Vector<Operator>(es);
		Collections.sort(
			sorted, 
			new Comparator<Operator>() {
				@Override
				public int compare(Operator op1, Operator op2) {
					// compare operator names
					int comp = op1.getName().compareToIgnoreCase(op2.getName());
					
					if(comp != 0)
						return comp;
					
					// if operator names are equal compare role maps
					Comparator<Map<String,Element>> roleMapComparator = 
						new Comparator<Map<String,Element>>() {
							@Override
							public int compare(Map<String, Element> m1, 
												Map<String, Element> m2) {
								// sort maps by rolename
								SortedMap<String, Element> tMap1 = 
											new TreeMap<String, Element>(m1);
								SortedMap<String, Element> tMap2 = 
											new TreeMap<String, Element>(m2);
							
								if(!m1.keySet().equals(m2.keySet()))
									throw new ReuseRuntimeException(
										"Role maps must have equal rolenames");
							
								// iterate over sorted maps and
								// compare roles
								for(String role : tMap1.keySet()) {
									Element e1 = tMap1.get(role);
									Element e2 = tMap2.get(role);
									int comp = e1.getName()
											.compareToIgnoreCase(e2.getName());
									if(comp != 0) return comp;
								}
							
								// all roles are equal
								return 0;
							}
						
						};
					
					return roleMapComparator
						.compare(op1.getRoles(), op2.getRoles());
				}	
			}
		);
		
		return sorted;
	}
	
	
	public static <T extends OpDesc> List<T> sortOpDescsByName(Collection<T> es) {
		List<T> sorted = new Vector<T>(es);
		Collections.sort(
			sorted, 
			new Comparator<OpDesc>() {
				@Override
				public int compare(OpDesc op1, OpDesc op2) {
					// compare OpDesc names
					int comp = op1.getName().compareToIgnoreCase(op2.getName());
					
					if(comp != 0)
						return comp;
					
					// if OpDesc names are equal compare role maps
					Comparator<Map<String,String>> roleMapComparator = 
						new Comparator<Map<String,String>>() {
							@Override
							public int compare(Map<String, String> m1, 
												Map<String, String> m2) {
								// sort maps by rolename
								SortedMap<String, String> tMap1 = 
											new TreeMap<String, String>(m1);
								SortedMap<String, String> tMap2 = 
											new TreeMap<String, String>(m2);
							
								if(!m1.keySet().equals(m2.keySet()))
									throw new ReuseRuntimeException(
										"Role maps must have equal rolenames");
							
								// iterate over sorted maps and
								// compare roles
								for(String role : tMap1.keySet()) {
									String eName1 = tMap1.get(role);
									String eName2 = tMap2.get(role);
									int comp = eName1
											.compareToIgnoreCase(eName2);
									if(comp != 0) return comp;
								}
							
								// all roles are equal
								return 0;
							}
						
						};
					
					return roleMapComparator
						.compare(op1.getRoles(), op2.getRoles());
				}	
			}
		);
		
		return sorted;
	}
	
	
	public static List<Bubble<?>> sortBubblesByName(Collection<Bubble<?>> es) {
		List<Bubble<?>> sorted = new Vector<Bubble<?>>(es);
		Collections.sort(
			sorted, 
			new Comparator<Bubble<?>>() {
				@Override
				public int compare(Bubble<?> op1, Bubble<?> op2) {
					// compare operator names
					int comp = op1.getOperatorName()
								.compareToIgnoreCase(op2.getOperatorName());
					
					if(comp != 0)
						return comp;
					
					// if operator names are equal compare role maps
					Comparator<Map<String,Element>> roleMapComparator = 
						new Comparator<Map<String,Element>>() {
							@Override
							public int compare(Map<String, Element> m1, 
												Map<String, Element> m2) {
								// sort maps by rolename
								SortedMap<String, Element> tMap1 = 
											new TreeMap<String, Element>(m1);
								SortedMap<String, Element> tMap2 = 
											new TreeMap<String, Element>(m2);
							
								if(!m1.keySet().equals(m2.keySet()))
									throw new ReuseRuntimeException(
										"Role maps must have equal rolenames");
							
								// iterate over sorted maps and
								// compare roles
								for(String role : tMap1.keySet()) {
									Element e1 = tMap1.get(role);
									Element e2 = tMap2.get(role);
									int comp = e1.getName()
											.compareToIgnoreCase(e2.getName());
									if(comp != 0) return comp;
								}
							
								// all roles are equal
								return 0;
							}
						
						};
						
					Configuration<?> c1 = op1.getConfiguration();
					Configuration<?> c2 = op2.getConfiguration();
					
					Map<String,Element> roles1 = new HashMap<String, Element>();
					for(Role<?> r : c1.getRoles().keySet()) {
						roles1.put(r.getName(), c1.getRoles().get(r));
					}
					
					Map<String,Element> roles2 = new HashMap<String, Element>();
					for(Role<?> r : c2.getRoles().keySet()) {
						roles2.put(r.getName(), c2.getRoles().get(r));
					}
					
					return roleMapComparator
						.compare(roles1, roles2);
				}	
			}
		);
		
		return sorted;
	}
	
	
	public static List<XMLOperator> sortXmlOpsByName(Collection<XMLOperator> es) {
		List<XMLOperator> sorted = new Vector<XMLOperator>(es);
		Collections.sort(
			sorted, 
			new Comparator<XMLOperator>() {
				@Override
				public int compare(XMLOperator op1, XMLOperator op2) {
					// compare operator names
					int comp = op1.getName().compareToIgnoreCase(op2.getName());
					
					if(comp != 0)
						return comp;
					
					// if operator names are equal compare role maps
					Comparator<Map<String,XMLElement>> roleMapComparator = 
						new Comparator<Map<String,XMLElement>>() {
							@Override
							public int compare(Map<String, XMLElement> m1, 
												Map<String, XMLElement> m2) {
								// sort maps by rolename
								SortedMap<String, XMLElement> tMap1 = 
											new TreeMap<String, XMLElement>(m1);
								SortedMap<String, XMLElement> tMap2 = 
											new TreeMap<String, XMLElement>(m2);
							
								if(!m1.keySet().equals(m2.keySet()))
									throw new ReuseRuntimeException(
										"Role maps must have equal rolenames");
							
								// iterate over sorted maps and
								// compare roles
								for(String role : tMap1.keySet()) {
									XMLElement e1 = tMap1.get(role);
									XMLElement e2 = tMap2.get(role);
									int comp = e1.getName()
											.compareToIgnoreCase(e2.getName());
									if(comp != 0) return comp;
								}
							
								// all roles are equal
								return 0;
							}
						
						};
					
					return roleMapComparator
						.compare(op1.getRoles(), op2.getRoles());
				}	
			}
		);
		
		return sorted;
	}
	
	
	public static <T extends XMLElement> List<T> sortXmlElementsByName(Collection<T> es) {
		List<T> sorted = new Vector<T>(es);
		Collections.sort(
			sorted, 
			new Comparator<XMLElement>() {
				@Override
				public int compare(XMLElement e1, XMLElement e2) {
					return e1.getName()
						.compareToIgnoreCase(e2.getName());
				}	
			}
		);

		return sorted;
	}
	
	
	/**
	 * Decorated for {@link MetaModel} class with easier
	 * element-access methods.
	 * @author alex
	 *
	 */
	public static class EasyMM extends MetaModel {
		private MetaModel mm;
		
		public EasyMM(MetaModel mm) {
			this.mm = mm;
		}
		
		public Element qref(String qname) {
			return getReferenceByQName(qname);
		}
		
		public Element ref(String name) {
			return getReferenceByName(name);
		}

		public Element qatt(String qname) {
			return getAttributeByQName(qname);
		}
		
		public Element att(String name) {
			return getAttributeByName(name);
		}
		
		public Element clazz(String name) {
			return getClassByName(name);
		}

		/**
		 * Delegated to standard implementation.
		 */
		@Override
		public boolean containsElement(Element e) {
			return mm.containsElement(e);
		}

		/**
		 * Delegated to standard implementation.
		 */
		@Override
		public boolean containsElementWithId(int id) {
			return mm.containsElementWithId(id);
		}

		/**
		 * Delegated to standard implementation.
		 */
		@Override
		public MetaModel copy() {
			return mm.copy();
		}

		/**
		 * Delegated to standard implementation.
		 */
		@Override
		public boolean equals(Object obj) {
			return mm.equals(obj);
		}

		/**
		 * Delegated to standard implementation.
		 */
		@Override
		public Element getAttributeByName(String name) {
			return mm.getAttributeByName(name);
		}

		/**
		 * Delegated to standard implementation.
		 */
		@Override
		public Element getAttributeByQName(String qname) {
			return mm.getAttributeByQName(qname);
		}

		/**
		 * Delegated to standard implementation.
		 */
		@Override
		public List<Element> getAttributes() {
			return mm.getAttributes();
		}

		/**
		 * Delegated to standard implementation.
		 */
		@Override
		public Element getClassByName(String name) {
			return mm.getClassByName(name);
		}

		/**
		 * Delegated to standard implementation.
		 */
		@Override
		public Element getClassByQName(String qname) {
			return mm.getClassByQName(qname);
		}

		/**
		 * Delegated to standard implementation.
		 */
		@Override
		public List<Element> getClasses() {
			return mm.getClasses();
		}

		/**
		 * Delegated to standard implementation.
		 */
		@Override
		public Element getElementById(int id) {
			return mm.getElementById(id);
		}

		/**
		 * Delegated to standard implementation.
		 */
		@Override
		public Element getElementByName(String fullName) {
			return mm.getElementByName(fullName);
		}

		/**
		 * Delegated to standard implementation.
		 */
		@Override
		public List<Element> getElements() {
			return mm.getElements();
		}

		/**
		 * Delegated to standard implementation.
		 */
		@Override
		public Element getReferenceByName(String name) {
			return mm.getReferenceByName(name);
		}

		/**
		 * Delegated to standard implementation.
		 */
		@Override
		public Element getReferenceByQName(String qname) {
			return mm.getReferenceByQName(qname);
		}

		/**
		 * Delegated to standard implementation.
		 */
		@Override
		public List<Element> getReferences() {
			return mm.getReferences();
		}

		/**
		 * Delegated to standard implementation.
		 */
		@Override
		public int hashCode() {
			return mm.hashCode();
		}

		/**
		 * Delegated to standard implementation.
		 */
		@Override
		public void setElements(List<Element> elements) {
			mm.setElements(elements);
		}

		/**
		 * Delegated to standard implementation.
		 */
		@Override
		public String toString() {
			return mm.toString();
		}
	}
	
	
	public static class OperatorBuilder {
		
		public static Operator c2c(Element lhsFocusClass, Element rhsFocusClass) {
			 return new Operator("C2C", 
						map("lhsFocusElement", lhsFocusClass)
						,map("rhsFocusElement", rhsFocusClass)
						);
		}
		
		
		public static Operator a2a(Operator c2c, Element lhsFocusAtt, 
													Element rhsFocusAtt) {
			Operator a2a = new Operator("A2A", 
						map("lhsFocusAttribute", lhsFocusAtt)
						,map("rhsFocusAttribute", rhsFocusAtt)
						);
			a2a.setParents(asArraySet(c2c));
			return a2a;
		}
		
		
		public static Operator r2r(Operator c2c1, Operator c2c2, 
							Element lhsFocusRef, Element rhsFocusRef) {
			Operator r2r = new Operator("R2R", 
						map("lhsFocusAttribute", lhsFocusRef)
						,map("rhsFocusAttribute", rhsFocusRef)
						);
			r2r.setParents(asArraySet(c2c1, c2c2));
			return r2r;
		}
		
		
		public static Operator a2c(Operator c2c, Element lhsFocusAtt, 
				Element rhsCxtRef, Element rhsFocusClass, Element rhsCxtAtt) {
			Operator a2c = new Operator("A2C", 
						map("lhsFocusAttribute", lhsFocusAtt)
						,map("rhsFocusClass", rhsFocusClass)
						.map("rhsContextReference", rhsCxtRef)
						.map("rhsContextAttribute", rhsCxtAtt)
						);
			a2c.setParents(asArraySet(c2c));
			return a2c;
		}
	}

}
