/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package sm_mm;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link sm_mm.Operator#getName <em>Name</em>}</li>
 *   <li>{@link sm_mm.Operator#getParents <em>Parents</em>}</li>
 *   <li>{@link sm_mm.Operator#getLhsRoles <em>Lhs Roles</em>}</li>
 *   <li>{@link sm_mm.Operator#getRhsRoles <em>Rhs Roles</em>}</li>
 *   <li>{@link sm_mm.Operator#getRoles <em>Roles</em>}</li>
 * </ul>
 * </p>
 *
 * @see sm_mm.Sm_mmPackage#getOperator()
 * @model abstract="true"
 * @generated
 */
public interface Operator extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see sm_mm.Sm_mmPackage#getOperator_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link sm_mm.Operator#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Parents</b></em>' reference list.
	 * The list contents are of type {@link sm_mm.ContextOperator}.
	 * It is bidirectional and its opposite is '{@link sm_mm.ContextOperator#getContextMappings <em>Context Mappings</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parents</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parents</em>' reference list.
	 * @see sm_mm.Sm_mmPackage#getOperator_Parents()
	 * @see sm_mm.ContextOperator#getContextMappings
	 * @model opposite="contextMappings"
	 * @generated
	 */
	EList<ContextOperator> getParents();

	/**
	 * Returns the value of the '<em><b>Lhs Roles</b></em>' reference list.
	 * The list contents are of type {@link sm_mm.Element}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lhs Roles</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lhs Roles</em>' reference list.
	 * @see sm_mm.Sm_mmPackage#getOperator_LhsRoles()
	 * @model required="true" transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	EList<Element> getLhsRoles();

	/**
	 * Returns the value of the '<em><b>Rhs Roles</b></em>' reference list.
	 * The list contents are of type {@link sm_mm.Element}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rhs Roles</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rhs Roles</em>' reference list.
	 * @see sm_mm.Sm_mmPackage#getOperator_RhsRoles()
	 * @model required="true" transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	EList<Element> getRhsRoles();

	/**
	 * Returns the value of the '<em><b>Roles</b></em>' reference list.
	 * The list contents are of type {@link sm_mm.Element}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Roles</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Roles</em>' reference list.
	 * @see sm_mm.Sm_mmPackage#getOperator_Roles()
	 * @model lower="2" transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	EList<Element> getRoles();

} // Operator
