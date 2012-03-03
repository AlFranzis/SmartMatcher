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
 * A representation of the model object '<em><b>Class</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link sm_mm.Class#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link sm_mm.Class#getName <em>Name</em>}</li>
 *   <li>{@link sm_mm.Class#getSupertypes <em>Supertypes</em>}</li>
 * </ul>
 * </p>
 *
 * @see sm_mm.Sm_mmPackage#getClass_()
 * @model
 * @generated
 */
public interface Class extends Element {
	/**
	 * Returns the value of the '<em><b>Attributes</b></em>' containment reference list.
	 * The list contents are of type {@link sm_mm.Attribute}.
	 * It is bidirectional and its opposite is '{@link sm_mm.Attribute#getContainer <em>Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attributes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attributes</em>' containment reference list.
	 * @see sm_mm.Sm_mmPackage#getClass_Attributes()
	 * @see sm_mm.Attribute#getContainer
	 * @model opposite="container" containment="true"
	 * @generated
	 */
	EList<Attribute> getAttributes();

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
	 * @see sm_mm.Sm_mmPackage#getClass_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link sm_mm.Class#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Supertypes</b></em>' reference list.
	 * The list contents are of type {@link sm_mm.Class}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Supertypes</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Supertypes</em>' reference list.
	 * @see sm_mm.Sm_mmPackage#getClass_Supertypes()
	 * @model
	 * @generated
	 */
	EList<Class> getSupertypes();

} // Class
