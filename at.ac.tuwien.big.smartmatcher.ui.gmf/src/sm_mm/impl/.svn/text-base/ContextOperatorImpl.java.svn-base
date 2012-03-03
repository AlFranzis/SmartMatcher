/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package sm_mm.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import sm_mm.ContextOperator;
import sm_mm.Operator;
import sm_mm.Sm_mmPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Context Operator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link sm_mm.impl.ContextOperatorImpl#getContextMappings <em>Context Mappings</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ContextOperatorImpl extends OperatorImpl implements ContextOperator {
	/**
	 * The cached value of the '{@link #getContextMappings() <em>Context Mappings</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContextMappings()
	 * @generated
	 * @ordered
	 */
	protected EList<Operator> contextMappings;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ContextOperatorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Sm_mmPackage.Literals.CONTEXT_OPERATOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Operator> getContextMappings() {
		if (contextMappings == null) {
			contextMappings = new EObjectWithInverseResolvingEList.ManyInverse<Operator>(Operator.class, this, Sm_mmPackage.CONTEXT_OPERATOR__CONTEXT_MAPPINGS, Sm_mmPackage.OPERATOR__PARENTS);
		}
		return contextMappings;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Sm_mmPackage.CONTEXT_OPERATOR__CONTEXT_MAPPINGS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getContextMappings()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Sm_mmPackage.CONTEXT_OPERATOR__CONTEXT_MAPPINGS:
				return ((InternalEList<?>)getContextMappings()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Sm_mmPackage.CONTEXT_OPERATOR__CONTEXT_MAPPINGS:
				return getContextMappings();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case Sm_mmPackage.CONTEXT_OPERATOR__CONTEXT_MAPPINGS:
				getContextMappings().clear();
				getContextMappings().addAll((Collection<? extends Operator>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case Sm_mmPackage.CONTEXT_OPERATOR__CONTEXT_MAPPINGS:
				getContextMappings().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case Sm_mmPackage.CONTEXT_OPERATOR__CONTEXT_MAPPINGS:
				return contextMappings != null && !contextMappings.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ContextOperatorImpl
