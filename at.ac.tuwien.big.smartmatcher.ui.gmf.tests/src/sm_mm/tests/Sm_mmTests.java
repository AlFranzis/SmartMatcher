/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package sm_mm.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test suite for the '<em><b>sm_mm</b></em>' package.
 * <!-- end-user-doc -->
 * @generated
 */
public class Sm_mmTests extends TestSuite {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(suite());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Test suite() {
		TestSuite suite = new Sm_mmTests("sm_mm Tests");
		suite.addTestSuite(ClassTest.class);
		suite.addTestSuite(AttributeTest.class);
		suite.addTestSuite(C2CTest.class);
		suite.addTestSuite(A2ATest.class);
		suite.addTestSuite(ReferenceTest.class);
		suite.addTestSuite(R2RTest.class);
		suite.addTestSuite(A2CTest.class);
		suite.addTestSuite(R2ATest.class);
		return suite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Sm_mmTests(String name) {
		super(name);
	}

} //Sm_mmTests
