package sm_mm.diagram.providers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.modelingassistant.ModelingAssistantProvider;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

/**
 * @generated
 */
public class Sm_mm_1ModelingAssistantProvider extends ModelingAssistantProvider {

	/**
	 * @generated
	 */
	public List getTypesForPopupBar(IAdaptable host) {
		IGraphicalEditPart editPart = (IGraphicalEditPart) host
				.getAdapter(IGraphicalEditPart.class);
		if (editPart instanceof sm_mm.diagram.edit.parts.ClassEditPart) {
			ArrayList types = new ArrayList(1);
			types
					.add(sm_mm.diagram.providers.Sm_mm_1ElementTypes.Attribute_3001);
			return types;
		}
		if (editPart instanceof sm_mm.diagram.edit.parts.MappingModelEditPart) {
			ArrayList types = new ArrayList(5);
			types.add(sm_mm.diagram.providers.Sm_mm_1ElementTypes.Class_2001);
			types.add(sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2A_2002);
			types.add(sm_mm.diagram.providers.Sm_mm_1ElementTypes.C2C_2003);
			types.add(sm_mm.diagram.providers.Sm_mm_1ElementTypes.R2R_2004);
			types.add(sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2C_2005);
			return types;
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public List getRelTypesOnSource(IAdaptable source) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source
				.getAdapter(IGraphicalEditPart.class);
		if (sourceEditPart instanceof sm_mm.diagram.edit.parts.ClassEditPart) {
			return ((sm_mm.diagram.edit.parts.ClassEditPart) sourceEditPart)
					.getMARelTypesOnSource();
		}
		if (sourceEditPart instanceof sm_mm.diagram.edit.parts.C2CEditPart) {
			return ((sm_mm.diagram.edit.parts.C2CEditPart) sourceEditPart)
					.getMARelTypesOnSource();
		}
		if (sourceEditPart instanceof sm_mm.diagram.edit.parts.A2AEditPart) {
			return ((sm_mm.diagram.edit.parts.A2AEditPart) sourceEditPart)
					.getMARelTypesOnSource();
		}
		if (sourceEditPart instanceof sm_mm.diagram.edit.parts.A2CEditPart) {
			return ((sm_mm.diagram.edit.parts.A2CEditPart) sourceEditPart)
					.getMARelTypesOnSource();
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public List getRelTypesOnTarget(IAdaptable target) {
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target
				.getAdapter(IGraphicalEditPart.class);
		if (targetEditPart instanceof sm_mm.diagram.edit.parts.ClassEditPart) {
			return ((sm_mm.diagram.edit.parts.ClassEditPart) targetEditPart)
					.getMARelTypesOnTarget();
		}
		if (targetEditPart instanceof sm_mm.diagram.edit.parts.C2CEditPart) {
			return ((sm_mm.diagram.edit.parts.C2CEditPart) targetEditPart)
					.getMARelTypesOnTarget();
		}
		if (targetEditPart instanceof sm_mm.diagram.edit.parts.A2AEditPart) {
			return ((sm_mm.diagram.edit.parts.A2AEditPart) targetEditPart)
					.getMARelTypesOnTarget();
		}
		if (targetEditPart instanceof sm_mm.diagram.edit.parts.R2REditPart) {
			return ((sm_mm.diagram.edit.parts.R2REditPart) targetEditPart)
					.getMARelTypesOnTarget();
		}
		if (targetEditPart instanceof sm_mm.diagram.edit.parts.A2CEditPart) {
			return ((sm_mm.diagram.edit.parts.A2CEditPart) targetEditPart)
					.getMARelTypesOnTarget();
		}
		if (targetEditPart instanceof sm_mm.diagram.edit.parts.AttributeEditPart) {
			return ((sm_mm.diagram.edit.parts.AttributeEditPart) targetEditPart)
					.getMARelTypesOnTarget();
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public List getRelTypesOnSourceAndTarget(IAdaptable source,
			IAdaptable target) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source
				.getAdapter(IGraphicalEditPart.class);
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target
				.getAdapter(IGraphicalEditPart.class);
		if (sourceEditPart instanceof sm_mm.diagram.edit.parts.ClassEditPart) {
			return ((sm_mm.diagram.edit.parts.ClassEditPart) sourceEditPart)
					.getMARelTypesOnSourceAndTarget(targetEditPart);
		}
		if (sourceEditPart instanceof sm_mm.diagram.edit.parts.C2CEditPart) {
			return ((sm_mm.diagram.edit.parts.C2CEditPart) sourceEditPart)
					.getMARelTypesOnSourceAndTarget(targetEditPart);
		}
		if (sourceEditPart instanceof sm_mm.diagram.edit.parts.A2AEditPart) {
			return ((sm_mm.diagram.edit.parts.A2AEditPart) sourceEditPart)
					.getMARelTypesOnSourceAndTarget(targetEditPart);
		}
		if (sourceEditPart instanceof sm_mm.diagram.edit.parts.A2CEditPart) {
			return ((sm_mm.diagram.edit.parts.A2CEditPart) sourceEditPart)
					.getMARelTypesOnSourceAndTarget(targetEditPart);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public List getTypesForSource(IAdaptable target,
			IElementType relationshipType) {
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target
				.getAdapter(IGraphicalEditPart.class);
		if (targetEditPart instanceof sm_mm.diagram.edit.parts.ClassEditPart) {
			return ((sm_mm.diagram.edit.parts.ClassEditPart) targetEditPart)
					.getMATypesForSource(relationshipType);
		}
		if (targetEditPart instanceof sm_mm.diagram.edit.parts.C2CEditPart) {
			return ((sm_mm.diagram.edit.parts.C2CEditPart) targetEditPart)
					.getMATypesForSource(relationshipType);
		}
		if (targetEditPart instanceof sm_mm.diagram.edit.parts.A2AEditPart) {
			return ((sm_mm.diagram.edit.parts.A2AEditPart) targetEditPart)
					.getMATypesForSource(relationshipType);
		}
		if (targetEditPart instanceof sm_mm.diagram.edit.parts.R2REditPart) {
			return ((sm_mm.diagram.edit.parts.R2REditPart) targetEditPart)
					.getMATypesForSource(relationshipType);
		}
		if (targetEditPart instanceof sm_mm.diagram.edit.parts.A2CEditPart) {
			return ((sm_mm.diagram.edit.parts.A2CEditPart) targetEditPart)
					.getMATypesForSource(relationshipType);
		}
		if (targetEditPart instanceof sm_mm.diagram.edit.parts.AttributeEditPart) {
			return ((sm_mm.diagram.edit.parts.AttributeEditPart) targetEditPart)
					.getMATypesForSource(relationshipType);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public List getTypesForTarget(IAdaptable source,
			IElementType relationshipType) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source
				.getAdapter(IGraphicalEditPart.class);
		if (sourceEditPart instanceof sm_mm.diagram.edit.parts.ClassEditPart) {
			return ((sm_mm.diagram.edit.parts.ClassEditPart) sourceEditPart)
					.getMATypesForTarget(relationshipType);
		}
		if (sourceEditPart instanceof sm_mm.diagram.edit.parts.C2CEditPart) {
			return ((sm_mm.diagram.edit.parts.C2CEditPart) sourceEditPart)
					.getMATypesForTarget(relationshipType);
		}
		if (sourceEditPart instanceof sm_mm.diagram.edit.parts.A2AEditPart) {
			return ((sm_mm.diagram.edit.parts.A2AEditPart) sourceEditPart)
					.getMATypesForTarget(relationshipType);
		}
		if (sourceEditPart instanceof sm_mm.diagram.edit.parts.A2CEditPart) {
			return ((sm_mm.diagram.edit.parts.A2CEditPart) sourceEditPart)
					.getMATypesForTarget(relationshipType);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public EObject selectExistingElementForSource(IAdaptable target,
			IElementType relationshipType) {
		return selectExistingElement(target, getTypesForSource(target,
				relationshipType));
	}

	/**
	 * @generated
	 */
	public EObject selectExistingElementForTarget(IAdaptable source,
			IElementType relationshipType) {
		return selectExistingElement(source, getTypesForTarget(source,
				relationshipType));
	}

	/**
	 * @generated
	 */
	protected EObject selectExistingElement(IAdaptable host, Collection types) {
		if (types.isEmpty()) {
			return null;
		}
		IGraphicalEditPart editPart = (IGraphicalEditPart) host
				.getAdapter(IGraphicalEditPart.class);
		if (editPart == null) {
			return null;
		}
		Diagram diagram = (Diagram) editPart.getRoot().getContents().getModel();
		Collection elements = new HashSet();
		for (Iterator it = diagram.getElement().eAllContents(); it.hasNext();) {
			EObject element = (EObject) it.next();
			if (isApplicableElement(element, types)) {
				elements.add(element);
			}
		}
		if (elements.isEmpty()) {
			return null;
		}
		return selectElement((EObject[]) elements.toArray(new EObject[elements
				.size()]));
	}

	/**
	 * @generated
	 */
	protected boolean isApplicableElement(EObject element, Collection types) {
		IElementType type = ElementTypeRegistry.getInstance().getElementType(
				element);
		return types.contains(type);
	}

	/**
	 * @generated
	 */
	protected EObject selectElement(EObject[] elements) {
		Shell shell = Display.getCurrent().getActiveShell();
		ILabelProvider labelProvider = new AdapterFactoryLabelProvider(
				sm_mm.diagram.part.Sm_mm_1DiagramEditorPlugin.getInstance()
						.getItemProvidersAdapterFactory());
		ElementListSelectionDialog dialog = new ElementListSelectionDialog(
				shell, labelProvider);
		dialog
				.setMessage(sm_mm.diagram.part.Messages.Sm_mm_1ModelingAssistantProviderMessage);
		dialog
				.setTitle(sm_mm.diagram.part.Messages.Sm_mm_1ModelingAssistantProviderTitle);
		dialog.setMultipleSelection(false);
		dialog.setElements(elements);
		EObject selected = null;
		if (dialog.open() == Window.OK) {
			selected = (EObject) dialog.getFirstResult();
		}
		return selected;
	}
}
