/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package sm_mm.tests;

import junit.textui.TestRunner;

import sm_mm.R2A;
import sm_mm.Sm_mmFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>R2A</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class R2ATest extends OperatorTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(R2ATest.class);
	}

	/**
	 * Constructs a new R2A test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public R2ATest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this R2A test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected R2A getFixture() {
		return (R2A)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(Sm_mmFactory.eINSTANCE.createR2A());
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

} //R2ATest
