package sm_mm.diagram.providers;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;

/**
 * @generated
 */
public class Sm_mm_1ElementTypes extends ElementInitializers {

	/**
	 * @generated
	 */
	private Sm_mm_1ElementTypes() {
	}

	/**
	 * @generated
	 */
	private static Map elements;

	/**
	 * @generated
	 */
	private static ImageRegistry imageRegistry;

	/**
	 * @generated
	 */
	private static Set KNOWN_ELEMENT_TYPES;

	/**
	 * @generated
	 */
	public static final IElementType MappingModel_1000 = getElementType("at.ac.tuwien.big.smartmatcher.ui.gmf.diagram.MappingModel_1000"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Class_2001 = getElementType("at.ac.tuwien.big.smartmatcher.ui.gmf.diagram.Class_2001"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType A2A_2002 = getElementType("at.ac.tuwien.big.smartmatcher.ui.gmf.diagram.A2A_2002"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType C2C_2003 = getElementType("at.ac.tuwien.big.smartmatcher.ui.gmf.diagram.C2C_2003"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType R2R_2004 = getElementType("at.ac.tuwien.big.smartmatcher.ui.gmf.diagram.R2R_2004"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType A2C_2005 = getElementType("at.ac.tuwien.big.smartmatcher.ui.gmf.diagram.A2C_2005"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Attribute_3001 = getElementType("at.ac.tuwien.big.smartmatcher.ui.gmf.diagram.Attribute_3001"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType C2CLhs_4001 = getElementType("at.ac.tuwien.big.smartmatcher.ui.gmf.diagram.C2CLhs_4001"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType C2CRhs_4002 = getElementType("at.ac.tuwien.big.smartmatcher.ui.gmf.diagram.C2CRhs_4002"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType A2ALhs_4003 = getElementType("at.ac.tuwien.big.smartmatcher.ui.gmf.diagram.A2ALhs_4003"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType A2ARhs_4004 = getElementType("at.ac.tuwien.big.smartmatcher.ui.gmf.diagram.A2ARhs_4004"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType ContextOperatorContextMappings_4005 = getElementType("at.ac.tuwien.big.smartmatcher.ui.gmf.diagram.ContextOperatorContextMappings_4005"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Reference_4006 = getElementType("at.ac.tuwien.big.smartmatcher.ui.gmf.diagram.Reference_4006"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType R2RLhs_4007 = getElementType("at.ac.tuwien.big.smartmatcher.ui.gmf.diagram.R2RLhs_4007"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType R2RRhs_4008 = getElementType("at.ac.tuwien.big.smartmatcher.ui.gmf.diagram.R2RRhs_4008"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType ClassSupertypes_4009 = getElementType("at.ac.tuwien.big.smartmatcher.ui.gmf.diagram.ClassSupertypes_4009"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType A2CLhsAttribute_4010 = getElementType("at.ac.tuwien.big.smartmatcher.ui.gmf.diagram.A2CLhsAttribute_4010"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType A2CRhsAttribute_4011 = getElementType("at.ac.tuwien.big.smartmatcher.ui.gmf.diagram.A2CRhsAttribute_4011"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType A2CRhsClass_4012 = getElementType("at.ac.tuwien.big.smartmatcher.ui.gmf.diagram.A2CRhsClass_4012"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType A2CRhsReference_4013 = getElementType("at.ac.tuwien.big.smartmatcher.ui.gmf.diagram.A2CRhsReference_4013"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	private static ImageRegistry getImageRegistry() {
		if (imageRegistry == null) {
			imageRegistry = new ImageRegistry();
		}
		return imageRegistry;
	}

	/**
	 * @generated
	 */
	private static String getImageRegistryKey(ENamedElement element) {
		return element.getName();
	}

	/**
	 * @generated
	 */
	private static ImageDescriptor getProvidedImageDescriptor(
			ENamedElement element) {
		if (element instanceof EStructuralFeature) {
			EStructuralFeature feature = ((EStructuralFeature) element);
			EClass eContainingClass = feature.getEContainingClass();
			EClassifier eType = feature.getEType();
			if (eContainingClass != null && !eContainingClass.isAbstract()) {
				element = eContainingClass;
			} else if (eType instanceof EClass
					&& !((EClass) eType).isAbstract()) {
				element = eType;
			}
		}
		if (element instanceof EClass) {
			EClass eClass = (EClass) element;
			if (!eClass.isAbstract()) {
				return sm_mm.diagram.part.Sm_mm_1DiagramEditorPlugin
						.getInstance().getItemImageDescriptor(
								eClass.getEPackage().getEFactoryInstance()
										.create(eClass));
			}
		}
		// TODO : support structural features
		return null;
	}

	/**
	 * @generated
	 */
	public static ImageDescriptor getImageDescriptor(ENamedElement element) {
		String key = getImageRegistryKey(element);
		ImageDescriptor imageDescriptor = getImageRegistry().getDescriptor(key);
		if (imageDescriptor == null) {
			imageDescriptor = getProvidedImageDescriptor(element);
			if (imageDescriptor == null) {
				imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
			}
			getImageRegistry().put(key, imageDescriptor);
		}
		return imageDescriptor;
	}

	/**
	 * @generated
	 */
	public static Image getImage(ENamedElement element) {
		String key = getImageRegistryKey(element);
		Image image = getImageRegistry().get(key);
		if (image == null) {
			ImageDescriptor imageDescriptor = getProvidedImageDescriptor(element);
			if (imageDescriptor == null) {
				imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
			}
			getImageRegistry().put(key, imageDescriptor);
			image = getImageRegistry().get(key);
		}
		return image;
	}

	/**
	 * @generated
	 */
	public static ImageDescriptor getImageDescriptor(IAdaptable hint) {
		ENamedElement element = getElement(hint);
		if (element == null) {
			return null;
		}
		return getImageDescriptor(element);
	}

	/**
	 * @generated
	 */
	public static Image getImage(IAdaptable hint) {
		ENamedElement element = getElement(hint);
		if (element == null) {
			return null;
		}
		return getImage(element);
	}

	/**
	 * Returns 'type' of the ecore object associated with the hint.
	 * 
	 * @generated
	 */
	public static ENamedElement getElement(IAdaptable hint) {
		Object type = hint.getAdapter(IElementType.class);
		if (elements == null) {
			elements = new IdentityHashMap();

			elements.put(MappingModel_1000, sm_mm.Sm_mmPackage.eINSTANCE
					.getMappingModel());

			elements.put(Class_2001, sm_mm.Sm_mmPackage.eINSTANCE.getClass_());

			elements.put(A2A_2002, sm_mm.Sm_mmPackage.eINSTANCE.getA2A());

			elements.put(C2C_2003, sm_mm.Sm_mmPackage.eINSTANCE.getC2C());

			elements.put(R2R_2004, sm_mm.Sm_mmPackage.eINSTANCE.getR2R());

			elements.put(A2C_2005, sm_mm.Sm_mmPackage.eINSTANCE.getA2C());

			elements.put(Attribute_3001, sm_mm.Sm_mmPackage.eINSTANCE
					.getAttribute());

			elements
					.put(C2CLhs_4001, sm_mm.Sm_mmPackage.eINSTANCE.getC2C_Lhs());

			elements
					.put(C2CRhs_4002, sm_mm.Sm_mmPackage.eINSTANCE.getC2C_Rhs());

			elements
					.put(A2ALhs_4003, sm_mm.Sm_mmPackage.eINSTANCE.getA2A_Lhs());

			elements
					.put(A2ARhs_4004, sm_mm.Sm_mmPackage.eINSTANCE.getA2A_Rhs());

			elements.put(ContextOperatorContextMappings_4005,
					sm_mm.Sm_mmPackage.eINSTANCE
							.getContextOperator_ContextMappings());

			elements.put(Reference_4006, sm_mm.Sm_mmPackage.eINSTANCE
					.getReference());

			elements
					.put(R2RLhs_4007, sm_mm.Sm_mmPackage.eINSTANCE.getR2R_Lhs());

			elements
					.put(R2RRhs_4008, sm_mm.Sm_mmPackage.eINSTANCE.getR2R_Rhs());

			elements.put(ClassSupertypes_4009, sm_mm.Sm_mmPackage.eINSTANCE
					.getClass_Supertypes());

			elements.put(A2CLhsAttribute_4010, sm_mm.Sm_mmPackage.eINSTANCE
					.getA2C_LhsAttribute());

			elements.put(A2CRhsAttribute_4011, sm_mm.Sm_mmPackage.eINSTANCE
					.getA2C_RhsAttribute());

			elements.put(A2CRhsClass_4012, sm_mm.Sm_mmPackage.eINSTANCE
					.getA2C_RhsClass());

			elements.put(A2CRhsReference_4013, sm_mm.Sm_mmPackage.eINSTANCE
					.getA2C_RhsReference());
		}
		return (ENamedElement) elements.get(type);
	}

	/**
	 * @generated
	 */
	private static IElementType getElementType(String id) {
		return ElementTypeRegistry.getInstance().getType(id);
	}

	/**
	 * @generated
	 */
	public static boolean isKnownElementType(IElementType elementType) {
		if (KNOWN_ELEMENT_TYPES == null) {
			KNOWN_ELEMENT_TYPES = new HashSet();
			KNOWN_ELEMENT_TYPES.add(MappingModel_1000);
			KNOWN_ELEMENT_TYPES.add(Class_2001);
			KNOWN_ELEMENT_TYPES.add(A2A_2002);
			KNOWN_ELEMENT_TYPES.add(C2C_2003);
			KNOWN_ELEMENT_TYPES.add(R2R_2004);
			KNOWN_ELEMENT_TYPES.add(A2C_2005);
			KNOWN_ELEMENT_TYPES.add(Attribute_3001);
			KNOWN_ELEMENT_TYPES.add(C2CLhs_4001);
			KNOWN_ELEMENT_TYPES.add(C2CRhs_4002);
			KNOWN_ELEMENT_TYPES.add(A2ALhs_4003);
			KNOWN_ELEMENT_TYPES.add(A2ARhs_4004);
			KNOWN_ELEMENT_TYPES.add(ContextOperatorContextMappings_4005);
			KNOWN_ELEMENT_TYPES.add(Reference_4006);
			KNOWN_ELEMENT_TYPES.add(R2RLhs_4007);
			KNOWN_ELEMENT_TYPES.add(R2RRhs_4008);
			KNOWN_ELEMENT_TYPES.add(ClassSupertypes_4009);
			KNOWN_ELEMENT_TYPES.add(A2CLhsAttribute_4010);
			KNOWN_ELEMENT_TYPES.add(A2CRhsAttribute_4011);
			KNOWN_ELEMENT_TYPES.add(A2CRhsClass_4012);
			KNOWN_ELEMENT_TYPES.add(A2CRhsReference_4013);
		}
		return KNOWN_ELEMENT_TYPES.contains(elementType);
	}

	/**
	 * @generated
	 */
	public static IElementType getElementType(int visualID) {
		switch (visualID) {
		case sm_mm.diagram.edit.parts.MappingModelEditPart.VISUAL_ID:
			return MappingModel_1000;
		case sm_mm.diagram.edit.parts.ClassEditPart.VISUAL_ID:
			return Class_2001;
		case sm_mm.diagram.edit.parts.C2CEditPart.VISUAL_ID:
			return A2A_2002;
		case sm_mm.diagram.edit.parts.A2AEditPart.VISUAL_ID:
			return C2C_2003;
		case sm_mm.diagram.edit.parts.R2REditPart.VISUAL_ID:
			return R2R_2004;
		case sm_mm.diagram.edit.parts.A2CEditPart.VISUAL_ID:
			return A2C_2005;
		case sm_mm.diagram.edit.parts.AttributeEditPart.VISUAL_ID:
			return Attribute_3001;
		case sm_mm.diagram.edit.parts.C2CLhsEditPart.VISUAL_ID:
			return C2CLhs_4001;
		case sm_mm.diagram.edit.parts.C2CRhsEditPart.VISUAL_ID:
			return C2CRhs_4002;
		case sm_mm.diagram.edit.parts.A2ALhsEditPart.VISUAL_ID:
			return A2ALhs_4003;
		case sm_mm.diagram.edit.parts.A2ARhsEditPart.VISUAL_ID:
			return A2ARhs_4004;
		case sm_mm.diagram.edit.parts.ContextOperatorContextMappingsEditPart.VISUAL_ID:
			return ContextOperatorContextMappings_4005;
		case sm_mm.diagram.edit.parts.ReferenceEditPart.VISUAL_ID:
			return Reference_4006;
		case sm_mm.diagram.edit.parts.R2RLhsEditPart.VISUAL_ID:
			return R2RLhs_4007;
		case sm_mm.diagram.edit.parts.R2RRhsEditPart.VISUAL_ID:
			return R2RRhs_4008;
		case sm_mm.diagram.edit.parts.ClassSupertypesEditPart.VISUAL_ID:
			return ClassSupertypes_4009;
		case sm_mm.diagram.edit.parts.A2CLhsAttributeEditPart.VISUAL_ID:
			return A2CLhsAttribute_4010;
		case sm_mm.diagram.edit.parts.A2CRhsAttributeEditPart.VISUAL_ID:
			return A2CRhsAttribute_4011;
		case sm_mm.diagram.edit.parts.A2CRhsClassEditPart.VISUAL_ID:
			return A2CRhsClass_4012;
		case sm_mm.diagram.edit.parts.A2CRhsReferenceEditPart.VISUAL_ID:
			return A2CRhsReference_4013;
		}
		return null;
	}

}
