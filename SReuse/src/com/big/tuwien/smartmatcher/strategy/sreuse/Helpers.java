package com.big.tuwien.smartmatcher.strategy.sreuse;

import static com.google.common.collect.Lists.transform;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArraySet;

import com.big.tuwien.SmartMatcher.AttributeElement;
import com.big.tuwien.SmartMatcher.ClassElement;
import com.big.tuwien.SmartMatcher.EcoreElement;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.ReferenceElement;
import com.google.common.base.Function;
import com.google.common.base.Predicate;

public class Helpers {
	
	
	public static List<Element> cast(List<? extends EcoreElement> elements) {
		return transform(
				elements, 
				new Function<EcoreElement,Element>() {
					@Override
					public Element apply(EcoreElement ee) {
						return ee.getRepresents();
					}
				});
	}
	
	
	public static <T extends EcoreElement> List<T> cast2(List<Element> elements, Class<T> etype) {
		return transform(
				elements, 
				new Function<Element,T>() {
					@SuppressWarnings("unchecked")
					@Override
					public T apply(Element e) {
						return (T) e.getRepresentedBy();
					}
				});
	}
	
	
	public static <T> Set<T> asSet(T... ts) {
		return new HashSet<T>(Arrays.asList(ts));
	}
	
	
	public static <E,T extends E> Set<E> asSet2(T... ts) {
		return new HashSet<E>(Arrays.asList(ts));
	}
	
	
	public static <T> Set<T> asArraySet(T... ts) {
		return new CopyOnWriteArraySet<T>(Arrays.asList(ts));
	}
	
	
	public static boolean isClass(Element e) {
		return e.getRepresentedBy().getContainedIn() == null;
	}
	
	public static boolean isAttribute(Element e) {
		return (e.getRepresentedBy() instanceof AttributeElement);
	}
	
	
	public static boolean isRef(Element e) {
		return (e.getRepresentedBy() instanceof ReferenceElement);
	}
	
	
	public static boolean isContainedIn(Element clazz, Element e) {
		ClassElement ce = (ClassElement) clazz.getRepresentedBy();
		EcoreElement ee = e.getRepresentedBy();
		return ce.equals(ee.getContainedIn());
	}
	
	
	/**
	 * Returns the attributes of a class.
	 * @param clazz
	 * @return
	 */
	public static List<Element> getAttributes(Element clazz) {
		return new Vector<Element>(
				cast(
					((ClassElement)clazz.getRepresentedBy()).getAttributes()
				));
	}
	
	
	/**
	 * Returns the references of a class.
	 * @param clazz
	 * @return
	 */
	public static List<Element> getReferences(Element clazz) {
		return new Vector<Element>(
				cast(
					((ClassElement)clazz.getRepresentedBy()).getReferences()
				));
	}
	
	
	public static Set<Operator> flattenOps(Set<Operator> rootOps) {
		Set<Operator> ops = new HashSet<Operator>();
		for(Operator rootOp : rootOps) {
			Set<Operator> _ops = flatten(rootOp);
			ops.addAll(_ops);
		}
		
		return ops;
	}
	
	
	/*
	 * Flattens an operator: Returns the operator and it's
	 * children in a set.
	 */
	private static Set<Operator> flatten(Operator op) {
		Set<Operator> ops = new HashSet<Operator>();
		ops.add(op);
		for(Operator child : op.getChildren()) {
			Set<Operator> _ops = flatten(child);
			ops.addAll(_ops);
		}
		return ops;
	}
	
	
	@SuppressWarnings("unchecked")
	public static <E> Set<E> cast(Set<?> set, Class<E> ctype) {
		return (Set<E>) set;
	}
	
	
	public static Predicate<XMLFragment> isRoot() {
		return new Predicate<XMLFragment>() {
			@Override
			public boolean apply(XMLFragment f) {
				return f.isRoot();
			}
		
		};
	}
}
