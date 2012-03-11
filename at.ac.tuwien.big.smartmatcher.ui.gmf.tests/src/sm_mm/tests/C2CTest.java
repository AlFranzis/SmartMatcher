/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package sm_mm.tests;

import junit.textui.TestRunner;

import sm_mm.C2C;
import sm_mm.Sm_mmFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>C2C</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class C2CTest extends ContextOperatorTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(C2CTest.class);
	}

	/**
	 * Constructs a new C2C test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public C2CTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this C2C test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected C2C getFixture() {
		return (C2C)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(Sm_mmFactory.eINSTANCE.createC2C());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#tearDown()
	 * @generated
	 */
	@Override
	protected void tearDown() throws Exception {
		setFixture(null);
	}

} //C2CTest
