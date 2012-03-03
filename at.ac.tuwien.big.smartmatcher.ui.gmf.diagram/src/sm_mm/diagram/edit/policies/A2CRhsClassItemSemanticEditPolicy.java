package sm_mm.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyReferenceCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyReferenceRequest;

/**
 * @generated
 */
public class A2CRhsClassItemSemanticEditPolicy extends
		sm_mm.diagram.edit.policies.Sm_mm_1BaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public A2CRhsClassItemSemanticEditPolicy() {
		super(sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2CRhsClass_4012);
	}

	/**
	 * @generated
	 */
	protected Command getDestroyReferenceCommand(DestroyReferenceRequest req) {
		return getGEFWrapper(new DestroyReferenceCommand(req));
	}

}
