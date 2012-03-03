/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package sm_mm.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import sm_mm.Element;
import sm_mm.Operator;
import sm_mm.Sm_mmPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link sm_mm.impl.ElementImpl#isLhs <em>Lhs</em>}</li>
 *   <li>{@link sm_mm.impl.ElementImpl#isRhs <em>Rhs</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ElementImpl extends EObjectImpl implements Element {
	/**
	 * The default value of the '{@link #isLhs() <em>Lhs</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLhs()
	 * @generated
	 * @ordered
	 */
	protected static final boolean LHS_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isLhs() <em>Lhs</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLhs()
	 * @generated
	 * @ordered
	 */
	protected boolean lhs = LHS_EDEFAULT;

	/**
	 * The default value of the '{@link #isRhs() <em>Rhs</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRhs()
	 * @generated
	 * @ordered
	 */
	protected static final boolean RHS_EDEFAULT = false;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Sm_mmPackage.Literals.ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isLhs() {
		return lhs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLhs(boolean newLhs) {
		boolean oldLhs = lhs;
		lhs = newLhs;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Sm_mmPackage.ELEMENT__LHS, oldLhs, lhs));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean isRhs() {
		return !lhs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean isMapped() {
		return getOperator() != null;
	}

	/**
	 * @generated NOT
	 */
	public Operator getOperator() {
		Operator op = null;
		// get all references that point to this element
		Collection<EStructuralFeature.Setting> settings = EcoreUtil.UsageCrossReferencer.find(this, this.eContainer());
		// search the "roles" reference of an operator that points to this element 
		for(EStructuralFeature.Setting setting : settings) {
			if(setting.getEStructuralFeature() == Sm_mmPackage.Literals.OPERATOR__ROLES) {
				op = (Operator) setting.getEObject();
				break;
			}
		}
		
		return op;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Sm_mmPackage.ELEMENT__LHS:
				return isLhs();
			case Sm_mmPackage.ELEMENT__RHS:
				return isRhs();
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
			case Sm_mmPackage.ELEMENT__LHS:
				setLhs((Boolean)newValue);
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
			case Sm_mmPackage.ELEMENT__LHS:
				setLhs(LHS_EDEFAULT);
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
			case Sm_mmPackage.ELEMENT__LHS:
				return lhs != LHS_EDEFAULT;
			case Sm_mmPackage.ELEMENT__RHS:
				return isRhs() != RHS_EDEFAULT;
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
		result.append(" (lhs: ");
		result.append(lhs);
		result.append(')');
		return result.toString();
	}

} //ElementImpl
