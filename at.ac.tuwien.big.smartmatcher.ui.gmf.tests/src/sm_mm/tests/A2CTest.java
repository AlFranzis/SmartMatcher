/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package sm_mm.tests;

import junit.textui.TestRunner;

import sm_mm.A2C;
import sm_mm.Sm_mmFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>A2C</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class A2CTest extends ContextOperatorTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(A2CTest.class);
	}

	/**
	 * Constructs a new A2C test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public A2CTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this A2C test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected A2C getFixture() {
		return (A2C)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(Sm_mmFactory.eINSTANCE.createA2C());
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

} //A2CTest
