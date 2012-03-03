package sm_mm.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyReferenceCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyReferenceRequest;

/**
 * @generated
 */
public class C2CLhsItemSemanticEditPolicy extends
		sm_mm.diagram.edit.policies.Sm_mm_1BaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public C2CLhsItemSemanticEditPolicy() {
		super(sm_mm.diagram.providers.Sm_mm_1ElementTypes.C2CLhs_4001);
	}

	/**
	 * @generated
	 */
	protected Command getDestroyReferenceCommand(DestroyReferenceRequest req) {
		return getGEFWrapper(new DestroyReferenceCommand(req));
	}

}
