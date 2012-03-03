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
 * A representation of the model object '<em><b>LHS Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link sm_mm.LHSModel#getLhsClasses <em>Lhs Classes</em>}</li>
 * </ul>
 * </p>
 *
 * @see sm_mm.Sm_mmPackage#getLHSModel()
 * @model
 * @generated
 */
public interface LHSModel extends EObject {
	/**
	 * Returns the value of the '<em><b>Lhs Classes</b></em>' containment reference list.
	 * The list contents are of type {@link sm_mm.Class}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lhs Classes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lhs Classes</em>' containment reference list.
	 * @see sm_mm.Sm_mmPackage#getLHSModel_LhsClasses()
	 * @model containment="true"
	 * @generated
	 */
	EList<sm_mm.Class> getLhsClasses();

} // LHSModel
