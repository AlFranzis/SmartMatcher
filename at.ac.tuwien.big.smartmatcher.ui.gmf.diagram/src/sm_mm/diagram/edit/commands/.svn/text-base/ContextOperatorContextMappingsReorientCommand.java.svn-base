package sm_mm.diagram.edit.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientReferenceRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;

/**
 * @generated
 */
public class ContextOperatorContextMappingsReorientCommand extends
		EditElementCommand {

	/**
	 * @generated
	 */
	private final int reorientDirection;

	/**
	 * @generated
	 */
	private final EObject referenceOwner;

	/**
	 * @generated
	 */
	private final EObject oldEnd;

	/**
	 * @generated
	 */
	private final EObject newEnd;

	/**
	 * @generated
	 */
	public ContextOperatorContextMappingsReorientCommand(
			ReorientReferenceRelationshipRequest request) {
		super(request.getLabel(), null, request);
		reorientDirection = request.getDirection();
		referenceOwner = request.getReferenceOwner();
		oldEnd = request.getOldRelationshipEnd();
		newEnd = request.getNewRelationshipEnd();
	}

	/**
	 * @generated
	 */
	public boolean canExecute() {
		if (false == referenceOwner instanceof sm_mm.ContextOperator) {
			return false;
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) {
			return canReorientSource();
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET) {
			return canReorientTarget();
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean canReorientSource() {
		if (!(oldEnd instanceof sm_mm.Operator && newEnd instanceof sm_mm.ContextOperator)) {
			return false;
		}
		return sm_mm.diagram.edit.policies.Sm_mm_1BaseItemSemanticEditPolicy.LinkConstraints
				.canExistContextOperatorContextMappings_4005(getNewSource(),
						getOldTarget());
	}

	/**
	 * @generated
	 */
	protected boolean canReorientTarget() {
		if (!(oldEnd instanceof sm_mm.Operator && newEnd instanceof sm_mm.Operator)) {
			return false;
		}
		return sm_mm.diagram.edit.policies.Sm_mm_1BaseItemSemanticEditPolicy.LinkConstraints
				.canExistContextOperatorContextMappings_4005(getOldSource(),
						getNewTarget());
	}

	/**
	 * @generated
	 */
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {
		if (!canExecute()) {
			throw new ExecutionException(
					"Invalid arguments in reorient link command"); //$NON-NLS-1$
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) {
			return reorientSource();
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET) {
			return reorientTarget();
		}
		throw new IllegalStateException();
	}

	/**
	 * @generated
	 */
	protected CommandResult reorientSource() throws ExecutionException {
		getOldSource().getContextMappings().remove(getOldTarget());
		getNewSource().getContextMappings().add(getOldTarget());
		return CommandResult.newOKCommandResult(referenceOwner);
	}

	/**
	 * @generated
	 */
	protected CommandResult reorientTarget() throws ExecutionException {
		getOldSource().getContextMappings().remove(getOldTarget());
		getOldSource().getContextMappings().add(getNewTarget());
		return CommandResult.newOKCommandResult(referenceOwner);
	}

	/**
	 * @generated
	 */
	protected sm_mm.ContextOperator getOldSource() {
		return (sm_mm.ContextOperator) referenceOwner;
	}

	/**
	 * @generated
	 */
	protected sm_mm.ContextOperator getNewSource() {
		return (sm_mm.ContextOperator) newEnd;
	}

	/**
	 * @generated
	 */
	protected sm_mm.Operator getOldTarget() {
		return (sm_mm.Operator) oldEnd;
	}

	/**
	 * @generated
	 */
	protected sm_mm.Operator getNewTarget() {
		return (sm_mm.Operator) newEnd;
	}
}
