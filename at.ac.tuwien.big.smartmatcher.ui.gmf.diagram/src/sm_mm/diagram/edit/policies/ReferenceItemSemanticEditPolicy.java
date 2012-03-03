package sm_mm.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientReferenceRelationshipRequest;

/**
 * @generated
 */
public class ReferenceItemSemanticEditPolicy extends
		sm_mm.diagram.edit.policies.Sm_mm_1BaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public ReferenceItemSemanticEditPolicy() {
		super(sm_mm.diagram.providers.Sm_mm_1ElementTypes.Reference_4006);
	}

	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		return getGEFWrapper(new DestroyElementCommand(req));
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
		if (sm_mm.diagram.providers.Sm_mm_1ElementTypes.R2RLhs_4007 == req
				.getElementType()) {
			return null;
		}
		if (sm_mm.diagram.providers.Sm_mm_1ElementTypes.R2RRhs_4008 == req
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
	 * @generated
	 */
	protected Command getCompleteCreateRelationshipCommand(
			CreateRelationshipRequest req) {
		if (sm_mm.diagram.providers.Sm_mm_1ElementTypes.R2RLhs_4007 == req
				.getElementType()) {
			return getGEFWrapper(new sm_mm.diagram.edit.commands.R2RLhsCreateCommand(
					req, req.getSource(), req.getTarget()));
		}
		if (sm_mm.diagram.providers.Sm_mm_1ElementTypes.R2RRhs_4008 == req
				.getElementType()) {
			return getGEFWrapper(new sm_mm.diagram.edit.commands.R2RRhsCreateCommand(
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
	 * Returns command to reorient EReference based link. New link target or source
	 * should be the domain model element associated with this node.
	 * 
	 * @generated
	 */
	protected Command getReorientReferenceRelationshipCommand(
			ReorientReferenceRelationshipRequest req) {
		switch (getVisualID(req)) {
		case sm_mm.diagram.edit.parts.R2RLhsEditPart.VISUAL_ID:
			return getGEFWrapper(new sm_mm.diagram.edit.commands.R2RLhsReorientCommand(
					req));
		case sm_mm.diagram.edit.parts.R2RRhsEditPart.VISUAL_ID:
			return getGEFWrapper(new sm_mm.diagram.edit.commands.R2RRhsReorientCommand(
					req));
		case sm_mm.diagram.edit.parts.A2CRhsReferenceEditPart.VISUAL_ID:
			return getGEFWrapper(new sm_mm.diagram.edit.commands.A2CRhsReferenceReorientCommand(
					req));
		}
		return super.getReorientReferenceRelationshipCommand(req);
	}

}
