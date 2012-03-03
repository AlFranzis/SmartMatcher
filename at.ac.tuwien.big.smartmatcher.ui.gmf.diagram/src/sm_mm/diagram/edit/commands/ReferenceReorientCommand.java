package sm_mm.diagram.edit.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;

/**
 * @generated
 */
public class ReferenceReorientCommand extends EditElementCommand {

	/**
	 * @generated
	 */
	private final int reorientDirection;

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
	public ReferenceReorientCommand(ReorientRelationshipRequest request) {
		super(request.getLabel(), request.getRelationship(), request);
		reorientDirection = request.getDirection();
		oldEnd = request.getOldRelationshipEnd();
		newEnd = request.getNewRelationshipEnd();
	}

	/**
	 * @generated
	 */
	public boolean canExecute() {
		if (false == getElementToEdit() instanceof sm_mm.Reference) {
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
		if (!(oldEnd instanceof sm_mm.Class && newEnd instanceof sm_mm.Class)) {
			return false;
		}
		sm_mm.Class target = getLink().getTarget();
		if (!(getLink().eContainer() instanceof sm_mm.MappingModel)) {
			return false;
		}
		sm_mm.MappingModel container = (sm_mm.MappingModel) getLink()
				.eContainer();
		return sm_mm.diagram.edit.policies.Sm_mm_1BaseItemSemanticEditPolicy.LinkConstraints
				.canExistReference_4006(container, getNewSource(), target);
	}

	/**
	 * @generated
	 */
	protected boolean canReorientTarget() {
		if (!(oldEnd instanceof sm_mm.Class && newEnd instanceof sm_mm.Class)) {
			return false;
		}
		sm_mm.Class source = getLink().getSource();
		if (!(getLink().eContainer() instanceof sm_mm.MappingModel)) {
			return false;
		}
		sm_mm.MappingModel container = (sm_mm.MappingModel) getLink()
				.eContainer();
		return sm_mm.diagram.edit.policies.Sm_mm_1BaseItemSemanticEditPolicy.LinkConstraints
				.canExistReference_4006(container, source, getNewTarget());
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
		getLink().setSource(getNewSource());
		return CommandResult.newOKCommandResult(getLink());
	}

	/**
	 * @generated
	 */
	protected CommandResult reorientTarget() throws ExecutionException {
		getLink().setTarget(getNewTarget());
		return CommandResult.newOKCommandResult(getLink());
	}

	/**
	 * @generated
	 */
	protected sm_mm.Reference getLink() {
		return (sm_mm.Reference) getElementToEdit();
	}

	/**
	 * @generated
	 */
	protected sm_mm.Class getOldSource() {
		return (sm_mm.Class) oldEnd;
	}

	/**
	 * @generated
	 */
	protected sm_mm.Class getNewSource() {
		return (sm_mm.Class) newEnd;
	}

	/**
	 * @generated
	 */
	protected sm_mm.Class getOldTarget() {
		return (sm_mm.Class) oldEnd;
	}

	/**
	 * @generated
	 */
	protected sm_mm.Class getNewTarget() {
		return (sm_mm.Class) newEnd;
	}
}
