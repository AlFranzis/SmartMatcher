package sm_mm.diagram.edit.policies;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.commands.DuplicateEObjectsCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest;

/**
 * @generated
 */
public class MappingModelItemSemanticEditPolicy extends
		sm_mm.diagram.edit.policies.Sm_mm_1BaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public MappingModelItemSemanticEditPolicy() {
		super(sm_mm.diagram.providers.Sm_mm_1ElementTypes.MappingModel_1000);
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (sm_mm.diagram.providers.Sm_mm_1ElementTypes.Class_2001 == req
				.getElementType()) {
			return getGEFWrapper(new sm_mm.diagram.edit.commands.ClassCreateCommand(
					req));
		}
		if (sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2A_2002 == req
				.getElementType()) {
			return getGEFWrapper(new sm_mm.diagram.edit.commands.C2CCreateCommand(
					req));
		}
		if (sm_mm.diagram.providers.Sm_mm_1ElementTypes.C2C_2003 == req
				.getElementType()) {
			return getGEFWrapper(new sm_mm.diagram.edit.commands.A2ACreateCommand(
					req));
		}
		if (sm_mm.diagram.providers.Sm_mm_1ElementTypes.R2R_2004 == req
				.getElementType()) {
			return getGEFWrapper(new sm_mm.diagram.edit.commands.R2RCreateCommand(
					req));
		}
		if (sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2C_2005 == req
				.getElementType()) {
			return getGEFWrapper(new sm_mm.diagram.edit.commands.A2CCreateCommand(
					req));
		}
		return super.getCreateCommand(req);
	}

	/**
	 * @generated
	 */
	protected Command getDuplicateCommand(DuplicateElementsRequest req) {
		TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost())
				.getEditingDomain();
		return getGEFWrapper(new DuplicateAnythingCommand(editingDomain, req));
	}

	/**
	 * @generated
	 */
	private static class DuplicateAnythingCommand extends
			DuplicateEObjectsCommand {

		/**
		 * @generated
		 */
		public DuplicateAnythingCommand(
				TransactionalEditingDomain editingDomain,
				DuplicateElementsRequest req) {
			super(editingDomain, req.getLabel(), req
					.getElementsToBeDuplicated(), req
					.getAllDuplicatedElementsMap());
		}

	}

}
