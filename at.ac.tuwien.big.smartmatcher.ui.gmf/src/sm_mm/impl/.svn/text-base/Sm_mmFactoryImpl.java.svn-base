/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package sm_mm.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import sm_mm.A2A;
import sm_mm.A2C;
import sm_mm.Attribute;
import sm_mm.C2C;
import sm_mm.MappingModel;
import sm_mm.R2A;
import sm_mm.R2R;
import sm_mm.Reference;
import sm_mm.Sm_mmFactory;
import sm_mm.Sm_mmPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Sm_mmFactoryImpl extends EFactoryImpl implements Sm_mmFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Sm_mmFactory init() {
		try {
			Sm_mmFactory theSm_mmFactory = (Sm_mmFactory)EPackage.Registry.INSTANCE.getEFactory("http://big.tuwien.ac.at/smartmatcher/ui/gmf/sm_mm1"); 
			if (theSm_mmFactory != null) {
				return theSm_mmFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new Sm_mmFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Sm_mmFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case Sm_mmPackage.MAPPING_MODEL: return createMappingModel();
			case Sm_mmPackage.CLASS: return createClass();
			case Sm_mmPackage.ATTRIBUTE: return createAttribute();
			case Sm_mmPackage.C2C: return createC2C();
			case Sm_mmPackage.A2A: return createA2A();
			case Sm_mmPackage.REFERENCE: return createReference();
			case Sm_mmPackage.R2R: return createR2R();
			case Sm_mmPackage.A2C: return createA2C();
			case Sm_mmPackage.R2A: return createR2A();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MappingModel createMappingModel() {
		MappingModelImpl mappingModel = new MappingModelImpl();
		return mappingModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public sm_mm.Class createClass() {
		ClassImpl class_ = new ClassImpl();
		return class_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Attribute createAttribute() {
		AttributeImpl attribute = new AttributeImpl();
		return attribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public C2C createC2C() {
		C2CImpl c2C = new C2CImpl();
		return c2C;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public A2A createA2A() {
		A2AImpl a2A = new A2AImpl();
		return a2A;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Reference createReference() {
		ReferenceImpl reference = new ReferenceImpl();
		return reference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public R2R createR2R() {
		R2RImpl r2R = new R2RImpl();
		return r2R;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public A2C createA2C() {
		A2CImpl a2C = new A2CImpl();
		return a2C;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public R2A createR2A() {
		R2AImpl r2A = new R2AImpl();
		return r2A;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Sm_mmPackage getSm_mmPackage() {
		return (Sm_mmPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static Sm_mmPackage getPackage() {
		return Sm_mmPackage.eINSTANCE;
	}

} //Sm_mmFactoryImpl
