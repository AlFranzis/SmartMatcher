package sm_mm.diagram.edit.policies;

import java.util.Iterator;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyReferenceCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyReferenceRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientReferenceRelationshipRequest;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class A2CItemSemanticEditPolicy extends
		sm_mm.diagram.edit.policies.Sm_mm_1BaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public A2CItemSemanticEditPolicy() {
		super(sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2C_2005);
	}

	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		View view = (View) getHost().getModel();
		CompositeTransactionalCommand cmd = new CompositeTransactionalCommand(
				getEditingDomain(), null);
		cmd.setTransactionNestingEnabled(false);
		for (Iterator it = view.getTargetEdges().iterator(); it.hasNext();) {
			Edge incomingLink = (Edge) it.next();
			if (sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
					.getVisualID(incomingLink) == sm_mm.diagram.edit.parts.ContextOperatorContextMappingsEditPart.VISUAL_ID) {
				DestroyReferenceRequest r = new DestroyReferenceRequest(
						incomingLink.getSource().getElement(), null,
						incomingLink.getTarget().getElement(), false);
				cmd.add(new DestroyReferenceCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
				continue;
			}
		}
		for (Iterator it = view.getSourceEdges().iterator(); it.hasNext();) {
			Edge outgoingLink = (Edge) it.next();
			if (sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
					.getVisualID(outgoingLink) == sm_mm.diagram.edit.parts.ContextOperatorContextMappingsEditPart.VISUAL_ID) {
				DestroyReferenceRequest r = new DestroyReferenceRequest(
						outgoingLink.getSource().getElement(), null,
						outgoingLink.getTarget().getElement(), false);
				cmd.add(new DestroyReferenceCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
				continue;
			}
			if (sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
					.getVisualID(outgoingLink) == sm_mm.diagram.edit.parts.A2CLhsAttributeEditPart.VISUAL_ID) {
				DestroyReferenceRequest r = new DestroyReferenceRequest(
						outgoingLink.getSource().getElement(), null,
						outgoingLink.getTarget().getElement(), false);
				cmd.add(new DestroyReferenceCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
				continue;
			}
			if (sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
					.getVisualID(outgoingLink) == sm_mm.diagram.edit.parts.A2CRhsAttributeEditPart.VISUAL_ID) {
				DestroyReferenceRequest r = new DestroyReferenceRequest(
						outgoingLink.getSource().getElement(), null,
						outgoingLink.getTarget().getElement(), false);
				cmd.add(new DestroyReferenceCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
				continue;
			}
			if (sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
					.getVisualID(outgoingLink) == sm_mm.diagram.edit.parts.A2CRhsClassEditPart.VISUAL_ID) {
				DestroyReferenceRequest r = new DestroyReferenceRequest(
						outgoingLink.getSource().getElement(), null,
						outgoingLink.getTarget().getElement(), false);
				cmd.add(new DestroyReferenceCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
				continue;
			}
			if (sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
					.getVisualID(outgoingLink) == sm_mm.diagram.edit.parts.A2CRhsReferenceEditPart.VISUAL_ID) {
				DestroyReferenceRequest r = new DestroyReferenceRequest(
						outgoingLink.getSource().getElement(), null,
						outgoingLink.getTarget().getElement(), false);
				cmd.add(new DestroyReferenceCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
				continue;
			}
		}
		EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
		if (annotation == null) {
			// there are indirectly referenced children, need extra commands: false
			addDestroyShortcutsCommand(cmd, view);
			// delete host element
			cmd.add(new DestroyElementCommand(req));
		} else {
			cmd.add(new DeleteCommand(getEditingDomain(), view));
		}
		return getGEFWrapper(cmd.reduce());
	}

	/**
	 * @generated
	 */
	protected Command getCreateRelationshipCommand(CreateRelationshipRequest req) {
		Command command = req.getTarget() == null ? getStartCreateRelationshipCommand(req)
				: getCompleteCreateRelationshipCommand(req);
		return command != null ? command : super
				.getCreateRelationshipCommand(req);
	}

	/**
	 * @generated
	 */
	protected Command getStartCreateRelationshipCommand(
			CreateRelationshipRequest req) {
		if (sm_mm.diagram.providers.Sm_mm_1ElementTypes.ContextOperatorContextMappings_4005 == req
				.getElementType()) {
			return getGEFWrapper(new sm_mm.diagram.edit.commands.ContextOperatorContextMappingsCreateCommand(
					req, req.getSource(), req.getTarget()));
		}
		if (sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2CLhsAttribute_4010 == req
				.getElementType()) {
			return getGEFWrapper(new sm_mm.diagram.edit.commands.A2CLhsAttributeCreateCommand(
					req, req.getSource(), req.getTarget()));
		}
		if (sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2CRhsAttribute_4011 == req
				.getElementType()) {
			return getGEFWrapper(new sm_mm.diagram.edit.commands.A2CRhsAttributeCreateCommand(
					req, req.getSource(), req.getTarget()));
		}
		if (sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2CRhsClass_4012 == req
				.getElementType()) {
			return getGEFWrapper(new sm_mm.diagram.edit.commands.A2CRhsClassCreateCommand(
					req, req.getSource(), req.getTarget()));
		}
		if (sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2CRhsReference_4013 == req
				.getElementType()) {
			return getGEFWrapper(new sm_mm.diagram.edit.commands.A2CRhsReferenceCreateCommand(
					req, req.getSource(), req.getTarget()));
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getCompleteCreateRelationshipCommand(
			CreateRelationshipRequest req) {
		if (sm_mm.diagram.providers.Sm_mm_1ElementTypes.ContextOperatorContextMappings_4005 == req
				.getElementType()) {
			return getGEFWrapper(new sm_mm.diagram.edit.commands.ContextOperatorContextMappingsCreateCommand(
					req, req.getSource(), req.getTarget()));
		}
		if (sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2CLhsAttribute_4010 == req
				.getElementType()) {
			return null;
		}
		if (sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2CRhsAttribute_4011 == req
				.getElementType()) {
			return null;
		}
		if (sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2CRhsClass_4012 == req
				.getElementType()) {
			return null;
		}
		if (sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2CRhsReference_4013 == req
				.getElementType()) {
			return null;
		}
		return null;
	}

	/**
	 * Returns command to reorient EReference based link. New link target or source
	 * should be the domain model element associated with this node.
	 * 
	 * @generated
	 */
	protected Command getReorientReferenceRelationshipCommand(
			ReorientReferenceRelationshipRequest req) {
		switch (getVisualID(req)) {
		case sm_mm.diagram.edit.parts.ContextOperatorContextMappingsEditPart.VISUAL_ID:
			return getGEFWrapper(new sm_mm.diagram.edit.commands.ContextOperatorContextMappingsReorientCommand(
					req));
		case sm_mm.diagram.edit.parts.A2CLhsAttributeEditPart.VISUAL_ID:
			return getGEFWrapper(new sm_mm.diagram.edit.commands.A2CLhsAttributeReorientCommand(
					req));
		case sm_mm.diagram.edit.parts.A2CRhsAttributeEditPart.VISUAL_ID:
			return getGEFWrapper(new sm_mm.diagram.edit.commands.A2CRhsAttributeReorientCommand(
					req));
		case sm_mm.diagram.edit.parts.A2CRhsClassEditPart.VISUAL_ID:
			return getGEFWrapper(new sm_mm.diagram.edit.commands.A2CRhsClassReorientCommand(
					req));
		case sm_mm.diagram.edit.parts.A2CRhsReferenceEditPart.VISUAL_ID:
			return getGEFWrapper(new sm_mm.diagram.edit.commands.A2CRhsReferenceReorientCommand(
					req));
		}
		return super.getReorientReferenceRelationshipCommand(req);
	}

}
