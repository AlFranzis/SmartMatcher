/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package sm_mm;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>R2A</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link sm_mm.R2A#getLhsReference <em>Lhs Reference</em>}</li>
 *   <li>{@link sm_mm.R2A#getRhsAttribute1 <em>Rhs Attribute1</em>}</li>
 *   <li>{@link sm_mm.R2A#getRhsAttribute2 <em>Rhs Attribute2</em>}</li>
 * </ul>
 * </p>
 *
 * @see sm_mm.Sm_mmPackage#getR2A()
 * @model
 * @generated
 */
public interface R2A extends Operator {
	/**
	 * Returns the value of the '<em><b>Lhs Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lhs Reference</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lhs Reference</em>' reference.
	 * @see #setLhsReference(Reference)
	 * @see sm_mm.Sm_mmPackage#getR2A_LhsReference()
	 * @model required="true"
	 * @generated
	 */
	Reference getLhsReference();

	/**
	 * Sets the value of the '{@link sm_mm.R2A#getLhsReference <em>Lhs Reference</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lhs Reference</em>' reference.
	 * @see #getLhsReference()
	 * @generated
	 */
	void setLhsReference(Reference value);

	/**
	 * Returns the value of the '<em><b>Rhs Attribute1</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rhs Attribute1</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rhs Attribute1</em>' reference.
	 * @see #setRhsAttribute1(Attribute)
	 * @see sm_mm.Sm_mmPackage#getR2A_RhsAttribute1()
	 * @model required="true"
	 * @generated
	 */
	Attribute getRhsAttribute1();

	/**
	 * Sets the value of the '{@link sm_mm.R2A#getRhsAttribute1 <em>Rhs Attribute1</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rhs Attribute1</em>' reference.
	 * @see #getRhsAttribute1()
	 * @generated
	 */
	void setRhsAttribute1(Attribute value);

	/**
	 * Returns the value of the '<em><b>Rhs Attribute2</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rhs Attribute2</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rhs Attribute2</em>' reference.
	 * @see #setRhsAttribute2(Attribute)
	 * @see sm_mm.Sm_mmPackage#getR2A_RhsAttribute2()
	 * @model required="true"
	 * @generated
	 */
	Attribute getRhsAttribute2();

	/**
	 * Sets the value of the '{@link sm_mm.R2A#getRhsAttribute2 <em>Rhs Attribute2</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rhs Attribute2</em>' reference.
	 * @see #getRhsAttribute2()
	 * @generated
	 */
	void setRhsAttribute2(Attribute value);

} // R2A
