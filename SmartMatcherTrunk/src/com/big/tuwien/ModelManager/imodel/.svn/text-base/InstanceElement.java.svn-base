package com.big.tuwien.ModelManager.imodel;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import com.big.tuwien.SmartMatcher.Element;

/**
 * Represents an instance of a meta-model element (class, attribute or reference).
 * @author alex
 *
 * @param <T> The type of meta-model element ({@link EClass}, {@link EAttribute}, 
 * {@link EReference}).
 * @param <S> The type of the stored instance.
 */
public interface InstanceElement<T extends EObject,S extends Object> {
	public String getQName();
	public T getMetaModelElement();
	public EObject getContainer();
	public ClassInstance getContainerInstance();
	public Element getElement();
	public S getInstance();
}
