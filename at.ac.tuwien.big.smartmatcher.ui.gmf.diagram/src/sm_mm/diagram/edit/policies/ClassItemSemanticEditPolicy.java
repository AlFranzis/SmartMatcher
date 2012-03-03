package sm_mm.diagram.edit.policies;

import java.util.Iterator;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.common.core.command.ICompositeCommand;
import org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyReferenceCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyReferenceRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientReferenceRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class ClassItemSemanticEditPolicy extends
		sm_mm.diagram.edit.policies.Sm_mm_1BaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public ClassItemSemanticEditPolicy() {
		super(sm_mm.diagram.providers.Sm_mm_1ElementTypes.Class_2001);
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
					.getVisualID(incomingLink) == sm_mm.diagram.edit.parts.C2CLhsEditPart.VISUAL_ID) {
				DestroyReferenceRequest r = new DestroyReferenceRequest(
						incomingLink.getSource().getElement(), null,
						incomingLink.getTarget().getElement(), false);
				cmd.add(new DestroyReferenceCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
				continue;
			}
			if (sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
					.getVisualID(incomingLink) == sm_mm.diagram.edit.parts.C2CRhsEditPart.VISUAL_ID) {
				DestroyReferenceRequest r = new DestroyReferenceRequest(
						incomingLink.getSource().getElement(), null,
						incomingLink.getTarget().getElement(), false);
				cmd.add(new DestroyReferenceCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
				continue;
			}
			if (sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
					.getVisualID(incomingLink) == sm_mm.diagram.edit.parts.ReferenceEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(
						incomingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
				continue;
			}
			if (sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
					.getVisualID(incomingLink) == sm_mm.diagram.edit.parts.ClassSupertypesEditPart.VISUAL_ID) {
				DestroyReferenceRequest r = new DestroyReferenceRequest(
						incomingLink.getSource().getElement(), null,
						incomingLink.getTarget().getElement(), false);
				cmd.add(new DestroyReferenceCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
				continue;
			}
			if (sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
					.getVisualID(incomingLink) == sm_mm.diagram.edit.parts.A2CRhsClassEditPart.VISUAL_ID) {
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
					.getVisualID(outgoingLink) == sm_mm.diagram.edit.parts.ReferenceEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(
						outgoingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
				continue;
			}
			if (sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
					.getVisualID(outgoingLink) == sm_mm.diagram.edit.parts.ClassSupertypesEditPart.VISUAL_ID) {
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
			addDestroyChildNodesCommand(cmd);
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
	private void addDestroyChildNodesCommand(ICompositeCommand cmd) {
		View view = (View) getHost().getModel();
		for (Iterator nit = view.getChildren().iterator(); nit.hasNext();) {
			Node node = (Node) nit.next();
			switch (sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
					.getVisualID(node)) {
			case sm_mm.diagram.edit.parts.ClassAttributesEditPart.VISUAL_ID:
				for (Iterator cit = node.getChildren().iterator(); cit
						.hasNext();) {
					Node cnode = (Node) cit.next();
					switch (sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getVisualID(cnode)) {
					case sm_mm.diagram.edit.parts.AttributeEditPart.VISUAL_ID:
						for (Iterator it = cnode.getTargetEdges().iterator(); it
								.hasNext();) {
							Edge incomingLink = (Edge) it.next();
							if (sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
									.getVisualID(incomingLink) == sm_mm.diagram.edit.parts.A2ALhsEditPart.VISUAL_ID) {
								DestroyReferenceRequest r = new DestroyReferenceRequest(
										incomingLink.getSource().getElement(),
										null, incomingLink.getTarget()
												.getElement(), false);
								cmd.add(new DestroyReferenceCommand(r));
								cmd.add(new DeleteCommand(getEditingDomain(),
										incomingLink));
								continue;
							}
							if (sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
									.getVisualID(incomingLink) == sm_mm.diagram.edit.parts.A2ARhsEditPart.VISUAL_ID) {
								DestroyReferenceRequest r = new DestroyReferenceRequest(
										incomingLink.getSource().getElement(),
										null, incomingLink.getTarget()
												.getElement(), false);
								cmd.add(new DestroyReferenceCommand(r));
								cmd.add(new DeleteCommand(getEditingDomain(),
										incomingLink));
								continue;
							}
							if (sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
									.getVisualID(incomingLink) == sm_mm.diagram.edit.parts.A2CLhsAttributeEditPart.VISUAL_ID) {
								DestroyReferenceRequest r = new DestroyReferenceRequest(
										incomingLink.getSource().getElement(),
										null, incomingLink.getTarget()
												.getElement(), false);
								cmd.add(new DestroyReferenceCommand(r));
								cmd.add(new DeleteCommand(getEditingDomain(),
										incomingLink));
								continue;
							}
							if (sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
									.getVisualID(incomingLink) == sm_mm.diagram.edit.parts.A2CRhsAttributeEditPart.VISUAL_ID) {
								DestroyReferenceRequest r = new DestroyReferenceRequest(
										incomingLink.getSource().getElement(),
										null, incomingLink.getTarget()
												.getElement(), false);
								cmd.add(new DestroyReferenceCommand(r));
								cmd.add(new DeleteCommand(getEditingDomain(),
										incomingLink));
								continue;
							}
						}
						cmd.add(new DestroyElementCommand(
								new DestroyElementRequest(getEditingDomain(),
										cnode.getElement(), false))); // directlyOwned: true
						// don't need explicit deletion of cnode as parent's view deletion would clean child views as well 
						// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), cnode));
						break;
					}
				}
				break;
			}
		}
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
		if (sm_mm.diagram.providers.Sm_mm_1ElementTypes.C2CLhs_4001 == req
				.getElementType()) {
			return null;
		}
		if (sm_mm.diagram.providers.Sm_mm_1ElementTypes.C2CRhs_4002 == req
				.getElementType()) {
			return null;
		}
		if (sm_mm.diagram.providers.Sm_mm_1ElementTypes.Reference_4006 == req
				.getElementType()) {
			return getGEFWrapper(new sm_mm.diagram.edit.commands.ReferenceCreateCommand(
					req, req.getSource(), req.getTarget()));
		}
		if (sm_mm.diagram.providers.Sm_mm_1ElementTypes.ClassSupertypes_4009 == req
				.getElementType()) {
			return getGEFWrapper(new sm_mm.diagram.edit.commands.ClassSupertypesCreateCommand(
					req, req.getSource(), req.getTarget()));
		}
		if (sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2CRhsClass_4012 == req
				.getElementType()) {
			return null;
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getCompleteCreateRelationshipCommand(
			CreateRelationshipRequest req) {
		if (sm_mm.diagram.providers.Sm_mm_1ElementTypes.C2CLhs_4001 == req
				.getElementType()) {
			return getGEFWrapper(new sm_mm.diagram.edit.commands.C2CLhsCreateCommand(
					req, req.getSource(), req.getTarget()));
		}
		if (sm_mm.diagram.providers.Sm_mm_1ElementTypes.C2CRhs_4002 == req
				.getElementType()) {
			return getGEFWrapper(new sm_mm.diagram.edit.commands.C2CRhsCreateCommand(
					req, req.getSource(), req.getTarget()));
		}
		if (sm_mm.diagram.providers.Sm_mm_1ElementTypes.Reference_4006 == req
				.getElementType()) {
			return getGEFWrapper(new sm_mm.diagram.edit.commands.ReferenceCreateCommand(
					req, req.getSource(), req.getTarget()));
		}
		if (sm_mm.diagram.providers.Sm_mm_1ElementTypes.ClassSupertypes_4009 == req
				.getElementType()) {
			return getGEFWrapper(new sm_mm.diagram.edit.commands.ClassSupertypesCreateCommand(
					req, req.getSource(), req.getTarget()));
		}
		if (sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2CRhsClass_4012 == req
				.getElementType()) {
			return getGEFWrapper(new sm_mm.diagram.edit.commands.A2CRhsClassCreateCommand(
					req, req.getSource(), req.getTarget()));
		}
		return null;
	}

	/**
	 * Returns command to reorient EClass based link. New link target or source
	 * should be the domain model element associated with this node.
	 * 
	 * @generated
	 */
	protected Command getReorientRelationshipCommand(
			ReorientRelationshipRequest req) {
		switch (getVisualID(req)) {
		case sm_mm.diagram.edit.parts.ReferenceEditPart.VISUAL_ID:
			return getGEFWrapper(new sm_mm.diagram.edit.commands.ReferenceReorientCommand(
					req));
		}
		return super.getReorientRelationshipCommand(req);
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
		case sm_mm.diagram.edit.parts.C2CLhsEditPart.VISUAL_ID:
			return getGEFWrapper(new sm_mm.diagram.edit.commands.C2CLhsReorientCommand(
					req));
		case sm_mm.diagram.edit.parts.C2CRhsEditPart.VISUAL_ID:
			return getGEFWrapper(new sm_mm.diagram.edit.commands.C2CRhsReorientCommand(
					req));
		case sm_mm.diagram.edit.parts.ClassSupertypesEditPart.VISUAL_ID:
			return getGEFWrapper(new sm_mm.diagram.edit.commands.ClassSupertypesReorientCommand(
					req));
		case sm_mm.diagram.edit.parts.A2CRhsClassEditPart.VISUAL_ID:
			return getGEFWrapper(new sm_mm.diagram.edit.commands.A2CRhsClassReorientCommand(
					req));
		}
		return super.getReorientReferenceRelationshipCommand(req);
	}

}
