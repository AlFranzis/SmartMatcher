/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package sm_mm;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>A2A</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link sm_mm.A2A#getLhs <em>Lhs</em>}</li>
 *   <li>{@link sm_mm.A2A#getRhs <em>Rhs</em>}</li>
 * </ul>
 * </p>
 *
 * @see sm_mm.Sm_mmPackage#getA2A()
 * @model
 * @generated
 */
public interface A2A extends Operator {
	/**
	 * Returns the value of the '<em><b>Lhs</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lhs</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lhs</em>' reference.
	 * @see #setLhs(Attribute)
	 * @see sm_mm.Sm_mmPackage#getA2A_Lhs()
	 * @model required="true"
	 * @generated
	 */
	Attribute getLhs();

	/**
	 * Sets the value of the '{@link sm_mm.A2A#getLhs <em>Lhs</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lhs</em>' reference.
	 * @see #getLhs()
	 * @generated
	 */
	void setLhs(Attribute value);

	/**
	 * Returns the value of the '<em><b>Rhs</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rhs</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rhs</em>' reference.
	 * @see #setRhs(Attribute)
	 * @see sm_mm.Sm_mmPackage#getA2A_Rhs()
	 * @model required="true"
	 * @generated
	 */
	Attribute getRhs();

	/**
	 * Sets the value of the '{@link sm_mm.A2A#getRhs <em>Rhs</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rhs</em>' reference.
	 * @see #getRhs()
	 * @generated
	 */
	void setRhs(Attribute value);

} // A2A
