package sm_mm.diagram.part;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;

/**
 * This registry is used to determine which type of visual object should be
 * created for the corresponding Diagram, Node, ChildNode or Link represented
 * by a domain model object.
 * 
 * @generated
 */
public class Sm_mm_1VisualIDRegistry {

	/**
	 * @generated
	 */
	private static final String DEBUG_KEY = "at.ac.tuwien.big.smartmatcher.ui.gmf.diagram/debug/visualID"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static int getVisualID(View view) {
		if (view instanceof Diagram) {
			if (sm_mm.diagram.edit.parts.MappingModelEditPart.MODEL_ID
					.equals(view.getType())) {
				return sm_mm.diagram.edit.parts.MappingModelEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		return sm_mm.diagram.part.Sm_mm_1VisualIDRegistry.getVisualID(view
				.getType());
	}

	/**
	 * @generated
	 */
	public static String getModelID(View view) {
		View diagram = view.getDiagram();
		while (view != diagram) {
			EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
			if (annotation != null) {
				return (String) annotation.getDetails().get("modelID"); //$NON-NLS-1$
			}
			view = (View) view.eContainer();
		}
		return diagram != null ? diagram.getType() : null;
	}

	/**
	 * @generated
	 */
	public static int getVisualID(String type) {
		try {
			return Integer.parseInt(type);
		} catch (NumberFormatException e) {
			if (Boolean.TRUE.toString().equalsIgnoreCase(
					Platform.getDebugOption(DEBUG_KEY))) {
				sm_mm.diagram.part.Sm_mm_1DiagramEditorPlugin.getInstance()
						.logError(
								"Unable to parse view type as a visualID number: "
										+ type);
			}
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static String getType(int visualID) {
		return String.valueOf(visualID);
	}

	/**
	 * @generated
	 */
	public static int getDiagramVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		if (sm_mm.Sm_mmPackage.eINSTANCE.getMappingModel().isSuperTypeOf(
				domainElement.eClass())
				&& isDiagram((sm_mm.MappingModel) domainElement)) {
			return sm_mm.diagram.edit.parts.MappingModelEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static int getNodeVisualID(View containerView, EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		String containerModelID = sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
				.getModelID(containerView);
		if (!sm_mm.diagram.edit.parts.MappingModelEditPart.MODEL_ID
				.equals(containerModelID)) {
			return -1;
		}
		int containerVisualID;
		if (sm_mm.diagram.edit.parts.MappingModelEditPart.MODEL_ID
				.equals(containerModelID)) {
			containerVisualID = sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = sm_mm.diagram.edit.parts.MappingModelEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		switch (containerVisualID) {
		case sm_mm.diagram.edit.parts.ClassAttributesEditPart.VISUAL_ID:
			if (sm_mm.Sm_mmPackage.eINSTANCE.getAttribute().isSuperTypeOf(
					domainElement.eClass())) {
				return sm_mm.diagram.edit.parts.AttributeEditPart.VISUAL_ID;
			}
			break;
		case sm_mm.diagram.edit.parts.MappingModelEditPart.VISUAL_ID:
			if (sm_mm.Sm_mmPackage.eINSTANCE.getClass_().isSuperTypeOf(
					domainElement.eClass())) {
				return sm_mm.diagram.edit.parts.ClassEditPart.VISUAL_ID;
			}
			if (sm_mm.Sm_mmPackage.eINSTANCE.getA2A().isSuperTypeOf(
					domainElement.eClass())) {
				return sm_mm.diagram.edit.parts.C2CEditPart.VISUAL_ID;
			}
			if (sm_mm.Sm_mmPackage.eINSTANCE.getC2C().isSuperTypeOf(
					domainElement.eClass())) {
				return sm_mm.diagram.edit.parts.A2AEditPart.VISUAL_ID;
			}
			if (sm_mm.Sm_mmPackage.eINSTANCE.getR2R().isSuperTypeOf(
					domainElement.eClass())) {
				return sm_mm.diagram.edit.parts.R2REditPart.VISUAL_ID;
			}
			if (sm_mm.Sm_mmPackage.eINSTANCE.getA2C().isSuperTypeOf(
					domainElement.eClass())) {
				return sm_mm.diagram.edit.parts.A2CEditPart.VISUAL_ID;
			}
			break;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static boolean canCreateNode(View containerView, int nodeVisualID) {
		String containerModelID = sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
				.getModelID(containerView);
		if (!sm_mm.diagram.edit.parts.MappingModelEditPart.MODEL_ID
				.equals(containerModelID)) {
			return false;
		}
		int containerVisualID;
		if (sm_mm.diagram.edit.parts.MappingModelEditPart.MODEL_ID
				.equals(containerModelID)) {
			containerVisualID = sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = sm_mm.diagram.edit.parts.MappingModelEditPart.VISUAL_ID;
			} else {
				return false;
			}
		}
		switch (containerVisualID) {
		case sm_mm.diagram.edit.parts.ClassEditPart.VISUAL_ID:
			if (sm_mm.diagram.edit.parts.ClassNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (sm_mm.diagram.edit.parts.ClassAttributesEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case sm_mm.diagram.edit.parts.AttributeEditPart.VISUAL_ID:
			if (sm_mm.diagram.edit.parts.AttributeNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case sm_mm.diagram.edit.parts.ClassAttributesEditPart.VISUAL_ID:
			if (sm_mm.diagram.edit.parts.AttributeEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case sm_mm.diagram.edit.parts.MappingModelEditPart.VISUAL_ID:
			if (sm_mm.diagram.edit.parts.ClassEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (sm_mm.diagram.edit.parts.C2CEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (sm_mm.diagram.edit.parts.A2AEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (sm_mm.diagram.edit.parts.R2REditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (sm_mm.diagram.edit.parts.A2CEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case sm_mm.diagram.edit.parts.ReferenceEditPart.VISUAL_ID:
			if (sm_mm.diagram.edit.parts.ReferenceNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		}
		return false;
	}

	/**
	 * @generated
	 */
	public static int getLinkWithClassVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		if (sm_mm.Sm_mmPackage.eINSTANCE.getReference().isSuperTypeOf(
				domainElement.eClass())) {
			return sm_mm.diagram.edit.parts.ReferenceEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static boolean isDiagram(sm_mm.MappingModel element) {
		return true;
	}

}
