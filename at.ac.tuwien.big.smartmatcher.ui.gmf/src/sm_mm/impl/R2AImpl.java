/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package sm_mm.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreEList;

import sm_mm.Attribute;
import sm_mm.Element;
import sm_mm.R2A;
import sm_mm.Reference;
import sm_mm.Sm_mmPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>R2A</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link sm_mm.impl.R2AImpl#getLhsReference <em>Lhs Reference</em>}</li>
 *   <li>{@link sm_mm.impl.R2AImpl#getRhsAttribute1 <em>Rhs Attribute1</em>}</li>
 *   <li>{@link sm_mm.impl.R2AImpl#getRhsAttribute2 <em>Rhs Attribute2</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class R2AImpl extends OperatorImpl implements R2A {
	/**
	 * The cached value of the '{@link #getLhsReference() <em>Lhs Reference</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLhsReference()
	 * @generated
	 * @ordered
	 */
	protected Reference lhsReference;

	/**
	 * The cached value of the '{@link #getRhsAttribute1() <em>Rhs Attribute1</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRhsAttribute1()
	 * @generated
	 * @ordered
	 */
	protected Attribute rhsAttribute1;

	/**
	 * The cached value of the '{@link #getRhsAttribute2() <em>Rhs Attribute2</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRhsAttribute2()
	 * @generated
	 * @ordered
	 */
	protected Attribute rhsAttribute2;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected R2AImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Sm_mmPackage.Literals.R2A;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Reference getLhsReference() {
		if (lhsReference != null && lhsReference.eIsProxy()) {
			InternalEObject oldLhsReference = (InternalEObject)lhsReference;
			lhsReference = (Reference)eResolveProxy(oldLhsReference);
			if (lhsReference != oldLhsReference) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Sm_mmPackage.R2A__LHS_REFERENCE, oldLhsReference, lhsReference));
			}
		}
		return lhsReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Reference basicGetLhsReference() {
		return lhsReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLhsReference(Reference newLhsReference) {
		Reference oldLhsReference = lhsReference;
		lhsReference = newLhsReference;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Sm_mmPackage.R2A__LHS_REFERENCE, oldLhsReference, lhsReference));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Attribute getRhsAttribute1() {
		if (rhsAttribute1 != null && rhsAttribute1.eIsProxy()) {
			InternalEObject oldRhsAttribute1 = (InternalEObject)rhsAttribute1;
			rhsAttribute1 = (Attribute)eResolveProxy(oldRhsAttribute1);
			if (rhsAttribute1 != oldRhsAttribute1) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Sm_mmPackage.R2A__RHS_ATTRIBUTE1, oldRhsAttribute1, rhsAttribute1));
			}
		}
		return rhsAttribute1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Attribute basicGetRhsAttribute1() {
		return rhsAttribute1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRhsAttribute1(Attribute newRhsAttribute1) {
		Attribute oldRhsAttribute1 = rhsAttribute1;
		rhsAttribute1 = newRhsAttribute1;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Sm_mmPackage.R2A__RHS_ATTRIBUTE1, oldRhsAttribute1, rhsAttribute1));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Attribute getRhsAttribute2() {
		if (rhsAttribute2 != null && rhsAttribute2.eIsProxy()) {
			InternalEObject oldRhsAttribute2 = (InternalEObject)rhsAttribute2;
			rhsAttribute2 = (Attribute)eResolveProxy(oldRhsAttribute2);
			if (rhsAttribute2 != oldRhsAttribute2) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Sm_mmPackage.R2A__RHS_ATTRIBUTE2, oldRhsAttribute2, rhsAttribute2));
			}
		}
		return rhsAttribute2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Attribute basicGetRhsAttribute2() {
		return rhsAttribute2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRhsAttribute2(Attribute newRhsAttribute2) {
		Attribute oldRhsAttribute2 = rhsAttribute2;
		rhsAttribute2 = newRhsAttribute2;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Sm_mmPackage.R2A__RHS_ATTRIBUTE2, oldRhsAttribute2, rhsAttribute2));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Sm_mmPackage.R2A__LHS_REFERENCE:
				if (resolve) return getLhsReference();
				return basicGetLhsReference();
			case Sm_mmPackage.R2A__RHS_ATTRIBUTE1:
				if (resolve) return getRhsAttribute1();
				return basicGetRhsAttribute1();
			case Sm_mmPackage.R2A__RHS_ATTRIBUTE2:
				if (resolve) return getRhsAttribute2();
				return basicGetRhsAttribute2();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case Sm_mmPackage.R2A__LHS_REFERENCE:
				setLhsReference((Reference)newValue);
				return;
			case Sm_mmPackage.R2A__RHS_ATTRIBUTE1:
				setRhsAttribute1((Attribute)newValue);
				return;
			case Sm_mmPackage.R2A__RHS_ATTRIBUTE2:
				setRhsAttribute2((Attribute)newValue);
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
			case Sm_mmPackage.R2A__LHS_REFERENCE:
				setLhsReference((Reference)null);
				return;
			case Sm_mmPackage.R2A__RHS_ATTRIBUTE1:
				setRhsAttribute1((Attribute)null);
				return;
			case Sm_mmPackage.R2A__RHS_ATTRIBUTE2:
				setRhsAttribute2((Attribute)null);
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
			case Sm_mmPackage.R2A__LHS_REFERENCE:
				return lhsReference != null;
			case Sm_mmPackage.R2A__RHS_ATTRIBUTE1:
				return rhsAttribute1 != null;
			case Sm_mmPackage.R2A__RHS_ATTRIBUTE2:
				return rhsAttribute2 != null;
		}
		return super.eIsSet(featureID);
	}
	
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<Element> getLhsRoles() {
		Element[] roles = {lhsReference};
		//return new BasicEList.UnmodifiableEList<Element>(roles.length, roles);
		return new EcoreEList.UnmodifiableEList<Element>(this, Sm_mmPackage.Literals.OPERATOR__LHS_ROLES, roles.length, roles);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<Element> getRhsRoles() {
		Element[] roles = {rhsAttribute1, rhsAttribute2};
		//return new BasicEList.UnmodifiableEList<Element>(roles.length, roles);
		return new EcoreEList.UnmodifiableEList<Element>(this, Sm_mmPackage.Literals.OPERATOR__RHS_ROLES, roles.length, roles);
	}

} //R2AImpl
