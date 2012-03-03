package sm_mm.diagram.part;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class Sm_mm_1DiagramUpdater {

	/**
	 * @generated
	 */
	public static List getSemanticChildren(View view) {
		switch (sm_mm.diagram.part.Sm_mm_1VisualIDRegistry.getVisualID(view)) {
		case sm_mm.diagram.edit.parts.ClassAttributesEditPart.VISUAL_ID:
			return getClassAttributes_7001SemanticChildren(view);
		case sm_mm.diagram.edit.parts.MappingModelEditPart.VISUAL_ID:
			return getMappingModel_1000SemanticChildren(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getClassAttributes_7001SemanticChildren(View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.EMPTY_LIST;
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		sm_mm.Class modelElement = (sm_mm.Class) containerView.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getAttributes().iterator(); it
				.hasNext();) {
			sm_mm.Attribute childElement = (sm_mm.Attribute) it.next();
			int visualID = sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
					.getNodeVisualID(view, childElement);
			if (visualID == sm_mm.diagram.edit.parts.AttributeEditPart.VISUAL_ID) {
				result.add(new sm_mm.diagram.part.Sm_mm_1NodeDescriptor(
						childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getMappingModel_1000SemanticChildren(View view) {
		if (!view.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		sm_mm.MappingModel modelElement = (sm_mm.MappingModel) view
				.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getClasses().iterator(); it.hasNext();) {
			sm_mm.Class childElement = (sm_mm.Class) it.next();
			int visualID = sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
					.getNodeVisualID(view, childElement);
			if (visualID == sm_mm.diagram.edit.parts.ClassEditPart.VISUAL_ID) {
				result.add(new sm_mm.diagram.part.Sm_mm_1NodeDescriptor(
						childElement, visualID));
				continue;
			}
		}
		for (Iterator it = modelElement.getOperators().iterator(); it.hasNext();) {
			sm_mm.Operator childElement = (sm_mm.Operator) it.next();
			int visualID = sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
					.getNodeVisualID(view, childElement);
			if (visualID == sm_mm.diagram.edit.parts.C2CEditPart.VISUAL_ID) {
				result.add(new sm_mm.diagram.part.Sm_mm_1NodeDescriptor(
						childElement, visualID));
				continue;
			}
			if (visualID == sm_mm.diagram.edit.parts.A2AEditPart.VISUAL_ID) {
				result.add(new sm_mm.diagram.part.Sm_mm_1NodeDescriptor(
						childElement, visualID));
				continue;
			}
			if (visualID == sm_mm.diagram.edit.parts.R2REditPart.VISUAL_ID) {
				result.add(new sm_mm.diagram.part.Sm_mm_1NodeDescriptor(
						childElement, visualID));
				continue;
			}
			if (visualID == sm_mm.diagram.edit.parts.A2CEditPart.VISUAL_ID) {
				result.add(new sm_mm.diagram.part.Sm_mm_1NodeDescriptor(
						childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getContainedLinks(View view) {
		switch (sm_mm.diagram.part.Sm_mm_1VisualIDRegistry.getVisualID(view)) {
		case sm_mm.diagram.edit.parts.MappingModelEditPart.VISUAL_ID:
			return getMappingModel_1000ContainedLinks(view);
		case sm_mm.diagram.edit.parts.ClassEditPart.VISUAL_ID:
			return getClass_2001ContainedLinks(view);
		case sm_mm.diagram.edit.parts.C2CEditPart.VISUAL_ID:
			return getA2A_2002ContainedLinks(view);
		case sm_mm.diagram.edit.parts.A2AEditPart.VISUAL_ID:
			return getC2C_2003ContainedLinks(view);
		case sm_mm.diagram.edit.parts.R2REditPart.VISUAL_ID:
			return getR2R_2004ContainedLinks(view);
		case sm_mm.diagram.edit.parts.A2CEditPart.VISUAL_ID:
			return getA2C_2005ContainedLinks(view);
		case sm_mm.diagram.edit.parts.AttributeEditPart.VISUAL_ID:
			return getAttribute_3001ContainedLinks(view);
		case sm_mm.diagram.edit.parts.ReferenceEditPart.VISUAL_ID:
			return getReference_4006ContainedLinks(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getIncomingLinks(View view) {
		switch (sm_mm.diagram.part.Sm_mm_1VisualIDRegistry.getVisualID(view)) {
		case sm_mm.diagram.edit.parts.ClassEditPart.VISUAL_ID:
			return getClass_2001IncomingLinks(view);
		case sm_mm.diagram.edit.parts.C2CEditPart.VISUAL_ID:
			return getA2A_2002IncomingLinks(view);
		case sm_mm.diagram.edit.parts.A2AEditPart.VISUAL_ID:
			return getC2C_2003IncomingLinks(view);
		case sm_mm.diagram.edit.parts.R2REditPart.VISUAL_ID:
			return getR2R_2004IncomingLinks(view);
		case sm_mm.diagram.edit.parts.A2CEditPart.VISUAL_ID:
			return getA2C_2005IncomingLinks(view);
		case sm_mm.diagram.edit.parts.AttributeEditPart.VISUAL_ID:
			return getAttribute_3001IncomingLinks(view);
		case sm_mm.diagram.edit.parts.ReferenceEditPart.VISUAL_ID:
			return getReference_4006IncomingLinks(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getOutgoingLinks(View view) {
		switch (sm_mm.diagram.part.Sm_mm_1VisualIDRegistry.getVisualID(view)) {
		case sm_mm.diagram.edit.parts.ClassEditPart.VISUAL_ID:
			return getClass_2001OutgoingLinks(view);
		case sm_mm.diagram.edit.parts.C2CEditPart.VISUAL_ID:
			return getA2A_2002OutgoingLinks(view);
		case sm_mm.diagram.edit.parts.A2AEditPart.VISUAL_ID:
			return getC2C_2003OutgoingLinks(view);
		case sm_mm.diagram.edit.parts.R2REditPart.VISUAL_ID:
			return getR2R_2004OutgoingLinks(view);
		case sm_mm.diagram.edit.parts.A2CEditPart.VISUAL_ID:
			return getA2C_2005OutgoingLinks(view);
		case sm_mm.diagram.edit.parts.AttributeEditPart.VISUAL_ID:
			return getAttribute_3001OutgoingLinks(view);
		case sm_mm.diagram.edit.parts.ReferenceEditPart.VISUAL_ID:
			return getReference_4006OutgoingLinks(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getMappingModel_1000ContainedLinks(View view) {
		sm_mm.MappingModel modelElement = (sm_mm.MappingModel) view
				.getElement();
		List result = new LinkedList();
		result
				.addAll(getContainedTypeModelFacetLinks_Reference_4006(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getClass_2001ContainedLinks(View view) {
		sm_mm.Class modelElement = (sm_mm.Class) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingFeatureModelFacetLinks_Class_Supertypes_4009(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getA2A_2002ContainedLinks(View view) {
		sm_mm.A2A modelElement = (sm_mm.A2A) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingFeatureModelFacetLinks_A2A_Lhs_4003(modelElement));
		result
				.addAll(getOutgoingFeatureModelFacetLinks_A2A_Rhs_4004(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getC2C_2003ContainedLinks(View view) {
		sm_mm.C2C modelElement = (sm_mm.C2C) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingFeatureModelFacetLinks_C2C_Lhs_4001(modelElement));
		result
				.addAll(getOutgoingFeatureModelFacetLinks_C2C_Rhs_4002(modelElement));
		result
				.addAll(getOutgoingFeatureModelFacetLinks_ContextOperator_ContextMappings_4005(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getR2R_2004ContainedLinks(View view) {
		sm_mm.R2R modelElement = (sm_mm.R2R) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingFeatureModelFacetLinks_R2R_Lhs_4007(modelElement));
		result
				.addAll(getOutgoingFeatureModelFacetLinks_R2R_Rhs_4008(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getA2C_2005ContainedLinks(View view) {
		sm_mm.A2C modelElement = (sm_mm.A2C) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingFeatureModelFacetLinks_ContextOperator_ContextMappings_4005(modelElement));
		result
				.addAll(getOutgoingFeatureModelFacetLinks_A2C_LhsAttribute_4010(modelElement));
		result
				.addAll(getOutgoingFeatureModelFacetLinks_A2C_RhsAttribute_4011(modelElement));
		result
				.addAll(getOutgoingFeatureModelFacetLinks_A2C_RhsClass_4012(modelElement));
		result
				.addAll(getOutgoingFeatureModelFacetLinks_A2C_RhsReference_4013(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getAttribute_3001ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getReference_4006ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getClass_2001IncomingLinks(View view) {
		sm_mm.Class modelElement = (sm_mm.Class) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingFeatureModelFacetLinks_C2C_Lhs_4001(
				modelElement, crossReferences));
		result.addAll(getIncomingFeatureModelFacetLinks_C2C_Rhs_4002(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Reference_4006(
				modelElement, crossReferences));
		result.addAll(getIncomingFeatureModelFacetLinks_Class_Supertypes_4009(
				modelElement, crossReferences));
		result.addAll(getIncomingFeatureModelFacetLinks_A2C_RhsClass_4012(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getA2A_2002IncomingLinks(View view) {
		sm_mm.A2A modelElement = (sm_mm.A2A) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result
				.addAll(getIncomingFeatureModelFacetLinks_ContextOperator_ContextMappings_4005(
						modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getC2C_2003IncomingLinks(View view) {
		sm_mm.C2C modelElement = (sm_mm.C2C) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result
				.addAll(getIncomingFeatureModelFacetLinks_ContextOperator_ContextMappings_4005(
						modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getR2R_2004IncomingLinks(View view) {
		sm_mm.R2R modelElement = (sm_mm.R2R) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result
				.addAll(getIncomingFeatureModelFacetLinks_ContextOperator_ContextMappings_4005(
						modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getA2C_2005IncomingLinks(View view) {
		sm_mm.A2C modelElement = (sm_mm.A2C) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result
				.addAll(getIncomingFeatureModelFacetLinks_ContextOperator_ContextMappings_4005(
						modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getAttribute_3001IncomingLinks(View view) {
		sm_mm.Attribute modelElement = (sm_mm.Attribute) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingFeatureModelFacetLinks_A2A_Lhs_4003(
				modelElement, crossReferences));
		result.addAll(getIncomingFeatureModelFacetLinks_A2A_Rhs_4004(
				modelElement, crossReferences));
		result.addAll(getIncomingFeatureModelFacetLinks_A2C_LhsAttribute_4010(
				modelElement, crossReferences));
		result.addAll(getIncomingFeatureModelFacetLinks_A2C_RhsAttribute_4011(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getReference_4006IncomingLinks(View view) {
		sm_mm.Reference modelElement = (sm_mm.Reference) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingFeatureModelFacetLinks_R2R_Lhs_4007(
				modelElement, crossReferences));
		result.addAll(getIncomingFeatureModelFacetLinks_R2R_Rhs_4008(
				modelElement, crossReferences));
		result.addAll(getIncomingFeatureModelFacetLinks_A2C_RhsReference_4013(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getClass_2001OutgoingLinks(View view) {
		sm_mm.Class modelElement = (sm_mm.Class) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingTypeModelFacetLinks_Reference_4006(modelElement));
		result
				.addAll(getOutgoingFeatureModelFacetLinks_Class_Supertypes_4009(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getA2A_2002OutgoingLinks(View view) {
		sm_mm.A2A modelElement = (sm_mm.A2A) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingFeatureModelFacetLinks_A2A_Lhs_4003(modelElement));
		result
				.addAll(getOutgoingFeatureModelFacetLinks_A2A_Rhs_4004(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getC2C_2003OutgoingLinks(View view) {
		sm_mm.C2C modelElement = (sm_mm.C2C) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingFeatureModelFacetLinks_C2C_Lhs_4001(modelElement));
		result
				.addAll(getOutgoingFeatureModelFacetLinks_C2C_Rhs_4002(modelElement));
		result
				.addAll(getOutgoingFeatureModelFacetLinks_ContextOperator_ContextMappings_4005(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getR2R_2004OutgoingLinks(View view) {
		sm_mm.R2R modelElement = (sm_mm.R2R) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingFeatureModelFacetLinks_R2R_Lhs_4007(modelElement));
		result
				.addAll(getOutgoingFeatureModelFacetLinks_R2R_Rhs_4008(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getA2C_2005OutgoingLinks(View view) {
		sm_mm.A2C modelElement = (sm_mm.A2C) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingFeatureModelFacetLinks_ContextOperator_ContextMappings_4005(modelElement));
		result
				.addAll(getOutgoingFeatureModelFacetLinks_A2C_LhsAttribute_4010(modelElement));
		result
				.addAll(getOutgoingFeatureModelFacetLinks_A2C_RhsAttribute_4011(modelElement));
		result
				.addAll(getOutgoingFeatureModelFacetLinks_A2C_RhsClass_4012(modelElement));
		result
				.addAll(getOutgoingFeatureModelFacetLinks_A2C_RhsReference_4013(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getAttribute_3001OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getReference_4006OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	private static Collection getContainedTypeModelFacetLinks_Reference_4006(
			sm_mm.MappingModel container) {
		Collection result = new LinkedList();
		for (Iterator links = container.getReferences().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof sm_mm.Reference) {
				continue;
			}
			sm_mm.Reference link = (sm_mm.Reference) linkObject;
			if (sm_mm.diagram.edit.parts.ReferenceEditPart.VISUAL_ID != sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			sm_mm.Class dst = link.getTarget();
			sm_mm.Class src = link.getSource();
			result.add(new sm_mm.diagram.part.Sm_mm_1LinkDescriptor(src, dst,
					link,
					sm_mm.diagram.providers.Sm_mm_1ElementTypes.Reference_4006,
					sm_mm.diagram.edit.parts.ReferenceEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingFeatureModelFacetLinks_C2C_Lhs_4001(
			sm_mm.Class target, Map crossReferences) {
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it
					.next();
			if (setting.getEStructuralFeature() == sm_mm.Sm_mmPackage.eINSTANCE
					.getC2C_Lhs()) {
				result
						.add(new sm_mm.diagram.part.Sm_mm_1LinkDescriptor(
								setting.getEObject(),
								target,
								sm_mm.diagram.providers.Sm_mm_1ElementTypes.C2CLhs_4001,
								sm_mm.diagram.edit.parts.C2CLhsEditPart.VISUAL_ID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingFeatureModelFacetLinks_C2C_Rhs_4002(
			sm_mm.Class target, Map crossReferences) {
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it
					.next();
			if (setting.getEStructuralFeature() == sm_mm.Sm_mmPackage.eINSTANCE
					.getC2C_Rhs()) {
				result
						.add(new sm_mm.diagram.part.Sm_mm_1LinkDescriptor(
								setting.getEObject(),
								target,
								sm_mm.diagram.providers.Sm_mm_1ElementTypes.C2CRhs_4002,
								sm_mm.diagram.edit.parts.C2CRhsEditPart.VISUAL_ID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingFeatureModelFacetLinks_A2A_Lhs_4003(
			sm_mm.Attribute target, Map crossReferences) {
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it
					.next();
			if (setting.getEStructuralFeature() == sm_mm.Sm_mmPackage.eINSTANCE
					.getA2A_Lhs()) {
				result
						.add(new sm_mm.diagram.part.Sm_mm_1LinkDescriptor(
								setting.getEObject(),
								target,
								sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2ALhs_4003,
								sm_mm.diagram.edit.parts.A2ALhsEditPart.VISUAL_ID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingFeatureModelFacetLinks_A2A_Rhs_4004(
			sm_mm.Attribute target, Map crossReferences) {
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it
					.next();
			if (setting.getEStructuralFeature() == sm_mm.Sm_mmPackage.eINSTANCE
					.getA2A_Rhs()) {
				result
						.add(new sm_mm.diagram.part.Sm_mm_1LinkDescriptor(
								setting.getEObject(),
								target,
								sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2ARhs_4004,
								sm_mm.diagram.edit.parts.A2ARhsEditPart.VISUAL_ID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingFeatureModelFacetLinks_ContextOperator_ContextMappings_4005(
			sm_mm.Operator target, Map crossReferences) {
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it
					.next();
			if (setting.getEStructuralFeature() == sm_mm.Sm_mmPackage.eINSTANCE
					.getContextOperator_ContextMappings()) {
				result
						.add(new sm_mm.diagram.part.Sm_mm_1LinkDescriptor(
								setting.getEObject(),
								target,
								sm_mm.diagram.providers.Sm_mm_1ElementTypes.ContextOperatorContextMappings_4005,
								sm_mm.diagram.edit.parts.ContextOperatorContextMappingsEditPart.VISUAL_ID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingTypeModelFacetLinks_Reference_4006(
			sm_mm.Class target, Map crossReferences) {
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it
					.next();
			if (setting.getEStructuralFeature() != sm_mm.Sm_mmPackage.eINSTANCE
					.getReference_Target()
					|| false == setting.getEObject() instanceof sm_mm.Reference) {
				continue;
			}
			sm_mm.Reference link = (sm_mm.Reference) setting.getEObject();
			if (sm_mm.diagram.edit.parts.ReferenceEditPart.VISUAL_ID != sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			sm_mm.Class src = link.getSource();
			result.add(new sm_mm.diagram.part.Sm_mm_1LinkDescriptor(src,
					target, link,
					sm_mm.diagram.providers.Sm_mm_1ElementTypes.Reference_4006,
					sm_mm.diagram.edit.parts.ReferenceEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingFeatureModelFacetLinks_R2R_Lhs_4007(
			sm_mm.Reference target, Map crossReferences) {
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it
					.next();
			if (setting.getEStructuralFeature() == sm_mm.Sm_mmPackage.eINSTANCE
					.getR2R_Lhs()) {
				result
						.add(new sm_mm.diagram.part.Sm_mm_1LinkDescriptor(
								setting.getEObject(),
								target,
								sm_mm.diagram.providers.Sm_mm_1ElementTypes.R2RLhs_4007,
								sm_mm.diagram.edit.parts.R2RLhsEditPart.VISUAL_ID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingFeatureModelFacetLinks_R2R_Rhs_4008(
			sm_mm.Reference target, Map crossReferences) {
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it
					.next();
			if (setting.getEStructuralFeature() == sm_mm.Sm_mmPackage.eINSTANCE
					.getR2R_Rhs()) {
				result
						.add(new sm_mm.diagram.part.Sm_mm_1LinkDescriptor(
								setting.getEObject(),
								target,
								sm_mm.diagram.providers.Sm_mm_1ElementTypes.R2RRhs_4008,
								sm_mm.diagram.edit.parts.R2RRhsEditPart.VISUAL_ID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingFeatureModelFacetLinks_Class_Supertypes_4009(
			sm_mm.Class target, Map crossReferences) {
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it
					.next();
			if (setting.getEStructuralFeature() == sm_mm.Sm_mmPackage.eINSTANCE
					.getClass_Supertypes()) {
				result
						.add(new sm_mm.diagram.part.Sm_mm_1LinkDescriptor(
								setting.getEObject(),
								target,
								sm_mm.diagram.providers.Sm_mm_1ElementTypes.ClassSupertypes_4009,
								sm_mm.diagram.edit.parts.ClassSupertypesEditPart.VISUAL_ID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingFeatureModelFacetLinks_A2C_LhsAttribute_4010(
			sm_mm.Attribute target, Map crossReferences) {
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it
					.next();
			if (setting.getEStructuralFeature() == sm_mm.Sm_mmPackage.eINSTANCE
					.getA2C_LhsAttribute()) {
				result
						.add(new sm_mm.diagram.part.Sm_mm_1LinkDescriptor(
								setting.getEObject(),
								target,
								sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2CLhsAttribute_4010,
								sm_mm.diagram.edit.parts.A2CLhsAttributeEditPart.VISUAL_ID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingFeatureModelFacetLinks_A2C_RhsAttribute_4011(
			sm_mm.Attribute target, Map crossReferences) {
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it
					.next();
			if (setting.getEStructuralFeature() == sm_mm.Sm_mmPackage.eINSTANCE
					.getA2C_RhsAttribute()) {
				result
						.add(new sm_mm.diagram.part.Sm_mm_1LinkDescriptor(
								setting.getEObject(),
								target,
								sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2CRhsAttribute_4011,
								sm_mm.diagram.edit.parts.A2CRhsAttributeEditPart.VISUAL_ID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingFeatureModelFacetLinks_A2C_RhsClass_4012(
			sm_mm.Class target, Map crossReferences) {
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it
					.next();
			if (setting.getEStructuralFeature() == sm_mm.Sm_mmPackage.eINSTANCE
					.getA2C_RhsClass()) {
				result
						.add(new sm_mm.diagram.part.Sm_mm_1LinkDescriptor(
								setting.getEObject(),
								target,
								sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2CRhsClass_4012,
								sm_mm.diagram.edit.parts.A2CRhsClassEditPart.VISUAL_ID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingFeatureModelFacetLinks_A2C_RhsReference_4013(
			sm_mm.Reference target, Map crossReferences) {
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it
					.next();
			if (setting.getEStructuralFeature() == sm_mm.Sm_mmPackage.eINSTANCE
					.getA2C_RhsReference()) {
				result
						.add(new sm_mm.diagram.part.Sm_mm_1LinkDescriptor(
								setting.getEObject(),
								target,
								sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2CRhsReference_4013,
								sm_mm.diagram.edit.parts.A2CRhsReferenceEditPart.VISUAL_ID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingFeatureModelFacetLinks_C2C_Lhs_4001(
			sm_mm.C2C source) {
		Collection result = new LinkedList();
		sm_mm.Class destination = source.getLhs();
		if (destination == null) {
			return result;
		}
		result.add(new sm_mm.diagram.part.Sm_mm_1LinkDescriptor(source,
				destination,
				sm_mm.diagram.providers.Sm_mm_1ElementTypes.C2CLhs_4001,
				sm_mm.diagram.edit.parts.C2CLhsEditPart.VISUAL_ID));
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingFeatureModelFacetLinks_C2C_Rhs_4002(
			sm_mm.C2C source) {
		Collection result = new LinkedList();
		sm_mm.Class destination = source.getRhs();
		if (destination == null) {
			return result;
		}
		result.add(new sm_mm.diagram.part.Sm_mm_1LinkDescriptor(source,
				destination,
				sm_mm.diagram.providers.Sm_mm_1ElementTypes.C2CRhs_4002,
				sm_mm.diagram.edit.parts.C2CRhsEditPart.VISUAL_ID));
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingFeatureModelFacetLinks_A2A_Lhs_4003(
			sm_mm.A2A source) {
		Collection result = new LinkedList();
		sm_mm.Attribute destination = source.getLhs();
		if (destination == null) {
			return result;
		}
		result.add(new sm_mm.diagram.part.Sm_mm_1LinkDescriptor(source,
				destination,
				sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2ALhs_4003,
				sm_mm.diagram.edit.parts.A2ALhsEditPart.VISUAL_ID));
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingFeatureModelFacetLinks_A2A_Rhs_4004(
			sm_mm.A2A source) {
		Collection result = new LinkedList();
		sm_mm.Attribute destination = source.getRhs();
		if (destination == null) {
			return result;
		}
		result.add(new sm_mm.diagram.part.Sm_mm_1LinkDescriptor(source,
				destination,
				sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2ARhs_4004,
				sm_mm.diagram.edit.parts.A2ARhsEditPart.VISUAL_ID));
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingFeatureModelFacetLinks_ContextOperator_ContextMappings_4005(
			sm_mm.ContextOperator source) {
		Collection result = new LinkedList();
		for (Iterator destinations = source.getContextMappings().iterator(); destinations
				.hasNext();) {
			sm_mm.Operator destination = (sm_mm.Operator) destinations.next();
			result
					.add(new sm_mm.diagram.part.Sm_mm_1LinkDescriptor(
							source,
							destination,
							sm_mm.diagram.providers.Sm_mm_1ElementTypes.ContextOperatorContextMappings_4005,
							sm_mm.diagram.edit.parts.ContextOperatorContextMappingsEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingTypeModelFacetLinks_Reference_4006(
			sm_mm.Class source) {
		sm_mm.MappingModel container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof sm_mm.MappingModel) {
				container = (sm_mm.MappingModel) element;
			}
		}
		if (container == null) {
			return Collections.EMPTY_LIST;
		}
		Collection result = new LinkedList();
		for (Iterator links = container.getReferences().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof sm_mm.Reference) {
				continue;
			}
			sm_mm.Reference link = (sm_mm.Reference) linkObject;
			if (sm_mm.diagram.edit.parts.ReferenceEditPart.VISUAL_ID != sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			sm_mm.Class dst = link.getTarget();
			sm_mm.Class src = link.getSource();
			if (src != source) {
				continue;
			}
			result.add(new sm_mm.diagram.part.Sm_mm_1LinkDescriptor(src, dst,
					link,
					sm_mm.diagram.providers.Sm_mm_1ElementTypes.Reference_4006,
					sm_mm.diagram.edit.parts.ReferenceEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingFeatureModelFacetLinks_R2R_Lhs_4007(
			sm_mm.R2R source) {
		Collection result = new LinkedList();
		sm_mm.Reference destination = source.getLhs();
		if (destination == null) {
			return result;
		}
		result.add(new sm_mm.diagram.part.Sm_mm_1LinkDescriptor(source,
				destination,
				sm_mm.diagram.providers.Sm_mm_1ElementTypes.R2RLhs_4007,
				sm_mm.diagram.edit.parts.R2RLhsEditPart.VISUAL_ID));
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingFeatureModelFacetLinks_R2R_Rhs_4008(
			sm_mm.R2R source) {
		Collection result = new LinkedList();
		sm_mm.Reference destination = source.getRhs();
		if (destination == null) {
			return result;
		}
		result.add(new sm_mm.diagram.part.Sm_mm_1LinkDescriptor(source,
				destination,
				sm_mm.diagram.providers.Sm_mm_1ElementTypes.R2RRhs_4008,
				sm_mm.diagram.edit.parts.R2RRhsEditPart.VISUAL_ID));
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingFeatureModelFacetLinks_Class_Supertypes_4009(
			sm_mm.Class source) {
		Collection result = new LinkedList();
		for (Iterator destinations = source.getSupertypes().iterator(); destinations
				.hasNext();) {
			sm_mm.Class destination = (sm_mm.Class) destinations.next();
			result
					.add(new sm_mm.diagram.part.Sm_mm_1LinkDescriptor(
							source,
							destination,
							sm_mm.diagram.providers.Sm_mm_1ElementTypes.ClassSupertypes_4009,
							sm_mm.diagram.edit.parts.ClassSupertypesEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingFeatureModelFacetLinks_A2C_LhsAttribute_4010(
			sm_mm.A2C source) {
		Collection result = new LinkedList();
		sm_mm.Attribute destination = source.getLhsAttribute();
		if (destination == null) {
			return result;
		}
		result
				.add(new sm_mm.diagram.part.Sm_mm_1LinkDescriptor(
						source,
						destination,
						sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2CLhsAttribute_4010,
						sm_mm.diagram.edit.parts.A2CLhsAttributeEditPart.VISUAL_ID));
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingFeatureModelFacetLinks_A2C_RhsAttribute_4011(
			sm_mm.A2C source) {
		Collection result = new LinkedList();
		sm_mm.Attribute destination = source.getRhsAttribute();
		if (destination == null) {
			return result;
		}
		result
				.add(new sm_mm.diagram.part.Sm_mm_1LinkDescriptor(
						source,
						destination,
						sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2CRhsAttribute_4011,
						sm_mm.diagram.edit.parts.A2CRhsAttributeEditPart.VISUAL_ID));
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingFeatureModelFacetLinks_A2C_RhsClass_4012(
			sm_mm.A2C source) {
		Collection result = new LinkedList();
		sm_mm.Class destination = source.getRhsClass();
		if (destination == null) {
			return result;
		}
		result.add(new sm_mm.diagram.part.Sm_mm_1LinkDescriptor(source,
				destination,
				sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2CRhsClass_4012,
				sm_mm.diagram.edit.parts.A2CRhsClassEditPart.VISUAL_ID));
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingFeatureModelFacetLinks_A2C_RhsReference_4013(
			sm_mm.A2C source) {
		Collection result = new LinkedList();
		sm_mm.Reference destination = source.getRhsReference();
		if (destination == null) {
			return result;
		}
		result
				.add(new sm_mm.diagram.part.Sm_mm_1LinkDescriptor(
						source,
						destination,
						sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2CRhsReference_4013,
						sm_mm.diagram.edit.parts.A2CRhsReferenceEditPart.VISUAL_ID));
		return result;
	}

}
