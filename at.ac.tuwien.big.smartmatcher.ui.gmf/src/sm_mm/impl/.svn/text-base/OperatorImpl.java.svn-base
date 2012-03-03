/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package sm_mm.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.emf.ecore.util.InternalEList;

import sm_mm.ContextOperator;
import sm_mm.Element;
import sm_mm.Operator;
import sm_mm.Sm_mmPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Operator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link sm_mm.impl.OperatorImpl#getName <em>Name</em>}</li>
 *   <li>{@link sm_mm.impl.OperatorImpl#getParents <em>Parents</em>}</li>
 *   <li>{@link sm_mm.impl.OperatorImpl#getLhsRoles <em>Lhs Roles</em>}</li>
 *   <li>{@link sm_mm.impl.OperatorImpl#getRhsRoles <em>Rhs Roles</em>}</li>
 *   <li>{@link sm_mm.impl.OperatorImpl#getRoles <em>Roles</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class OperatorImpl extends EObjectImpl implements Operator {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getParents() <em>Parents</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParents()
	 * @generated
	 * @ordered
	 */
	protected EList<ContextOperator> parents;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OperatorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Sm_mmPackage.Literals.OPERATOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Sm_mmPackage.OPERATOR__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ContextOperator> getParents() {
		if (parents == null) {
			parents = new EObjectWithInverseResolvingEList.ManyInverse<ContextOperator>(ContextOperator.class, this, Sm_mmPackage.OPERATOR__PARENTS, Sm_mmPackage.CONTEXT_OPERATOR__CONTEXT_MAPPINGS);
		}
		return parents;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 * This method has to be provided by every concrete operator implementation.
	 */
	public abstract EList<Element> getLhsRoles();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 * This method has to be provided by every concrete operator implementation. 
	 */
	public abstract EList<Element> getRhsRoles();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<Element> getRoles() {
		EList<Element> lhs = getLhsRoles();
		EList<Element> rhs = getRhsRoles();
		List<Element> roles = new ArrayList<Element>(lhs);
		roles.addAll(rhs);
		// return new BasicEList.UnmodifiableEList<Element>(roles.size(), roles.toArray());
		return new EcoreEList.UnmodifiableEList<Element>(this, Sm_mmPackage.Literals.OPERATOR__ROLES, roles.size(), roles.toArray());
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
			case Sm_mmPackage.OPERATOR__PARENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getParents()).basicAdd(otherEnd, msgs);
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
			case Sm_mmPackage.OPERATOR__PARENTS:
				return ((InternalEList<?>)getParents()).basicRemove(otherEnd, msgs);
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
			case Sm_mmPackage.OPERATOR__NAME:
				return getName();
			case Sm_mmPackage.OPERATOR__PARENTS:
				return getParents();
			case Sm_mmPackage.OPERATOR__LHS_ROLES:
				return getLhsRoles();
			case Sm_mmPackage.OPERATOR__RHS_ROLES:
				return getRhsRoles();
			case Sm_mmPackage.OPERATOR__ROLES:
				return getRoles();
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
			case Sm_mmPackage.OPERATOR__NAME:
				setName((String)newValue);
				return;
			case Sm_mmPackage.OPERATOR__PARENTS:
				getParents().clear();
				getParents().addAll((Collection<? extends ContextOperator>)newValue);
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
			case Sm_mmPackage.OPERATOR__NAME:
				setName(NAME_EDEFAULT);
				return;
			case Sm_mmPackage.OPERATOR__PARENTS:
				getParents().clear();
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
			case Sm_mmPackage.OPERATOR__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case Sm_mmPackage.OPERATOR__PARENTS:
				return parents != null && !parents.isEmpty();
			case Sm_mmPackage.OPERATOR__LHS_ROLES:
				return !getLhsRoles().isEmpty();
			case Sm_mmPackage.OPERATOR__RHS_ROLES:
				return !getRhsRoles().isEmpty();
			case Sm_mmPackage.OPERATOR__ROLES:
				return !getRoles().isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //OperatorImpl
