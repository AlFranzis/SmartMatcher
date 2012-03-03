/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package sm_mm;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>R2R</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link sm_mm.R2R#getLhs <em>Lhs</em>}</li>
 *   <li>{@link sm_mm.R2R#getRhs <em>Rhs</em>}</li>
 * </ul>
 * </p>
 *
 * @see sm_mm.Sm_mmPackage#getR2R()
 * @model
 * @generated
 */
public interface R2R extends Operator {
	/**
	 * Returns the value of the '<em><b>Lhs</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lhs</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lhs</em>' reference.
	 * @see #setLhs(Reference)
	 * @see sm_mm.Sm_mmPackage#getR2R_Lhs()
	 * @model required="true"
	 * @generated
	 */
	Reference getLhs();

	/**
	 * Sets the value of the '{@link sm_mm.R2R#getLhs <em>Lhs</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lhs</em>' reference.
	 * @see #getLhs()
	 * @generated
	 */
	void setLhs(Reference value);

	/**
	 * Returns the value of the '<em><b>Rhs</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rhs</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rhs</em>' reference.
	 * @see #setRhs(Reference)
	 * @see sm_mm.Sm_mmPackage#getR2R_Rhs()
	 * @model required="true"
	 * @generated
	 */
	Reference getRhs();

	/**
	 * Sets the value of the '{@link sm_mm.R2R#getRhs <em>Rhs</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rhs</em>' reference.
	 * @see #getRhs()
	 * @generated
	 */
	void setRhs(Reference value);

} // R2R
