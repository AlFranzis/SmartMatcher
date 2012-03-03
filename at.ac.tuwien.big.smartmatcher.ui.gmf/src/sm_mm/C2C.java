/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package sm_mm;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>C2C</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link sm_mm.C2C#getLhs <em>Lhs</em>}</li>
 *   <li>{@link sm_mm.C2C#getRhs <em>Rhs</em>}</li>
 * </ul>
 * </p>
 *
 * @see sm_mm.Sm_mmPackage#getC2C()
 * @model
 * @generated
 */
public interface C2C extends ContextOperator {
	/**
	 * Returns the value of the '<em><b>Lhs</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lhs</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lhs</em>' reference.
	 * @see #setLhs(sm_mm.Class)
	 * @see sm_mm.Sm_mmPackage#getC2C_Lhs()
	 * @model annotation="role rolename='lhsFocusElement'"
	 * @generated
	 */
	sm_mm.Class getLhs();

	/**
	 * Sets the value of the '{@link sm_mm.C2C#getLhs <em>Lhs</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lhs</em>' reference.
	 * @see #getLhs()
	 * @generated
	 */
	void setLhs(sm_mm.Class value);

	/**
	 * Returns the value of the '<em><b>Rhs</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rhs</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rhs</em>' reference.
	 * @see #setRhs(sm_mm.Class)
	 * @see sm_mm.Sm_mmPackage#getC2C_Rhs()
	 * @model annotation="role rolename='rhsFocusElement'"
	 * @generated
	 */
	sm_mm.Class getRhs();

	/**
	 * Sets the value of the '{@link sm_mm.C2C#getRhs <em>Rhs</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rhs</em>' reference.
	 * @see #getRhs()
	 * @generated
	 */
	void setRhs(sm_mm.Class value);

} // C2C
