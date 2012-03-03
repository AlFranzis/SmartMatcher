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
 * A representation of the model object '<em><b>RHS Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link sm_mm.RHSModel#getRhsClasses <em>Rhs Classes</em>}</li>
 * </ul>
 * </p>
 *
 * @see sm_mm.Sm_mmPackage#getRHSModel()
 * @model
 * @generated
 */
public interface RHSModel extends EObject {
	/**
	 * Returns the value of the '<em><b>Rhs Classes</b></em>' containment reference list.
	 * The list contents are of type {@link sm_mm.Class}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rhs Classes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rhs Classes</em>' containment reference list.
	 * @see sm_mm.Sm_mmPackage#getRHSModel_RhsClasses()
	 * @model containment="true"
	 * @generated
	 */
	EList<sm_mm.Class> getRhsClasses();

} // RHSModel
