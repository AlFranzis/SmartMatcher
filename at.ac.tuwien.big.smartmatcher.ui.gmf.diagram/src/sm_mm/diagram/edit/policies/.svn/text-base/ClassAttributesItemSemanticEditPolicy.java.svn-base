package sm_mm.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

/**
 * @generated
 */
public class ClassAttributesItemSemanticEditPolicy extends
		sm_mm.diagram.edit.policies.Sm_mm_1BaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public ClassAttributesItemSemanticEditPolicy() {
		super(sm_mm.diagram.providers.Sm_mm_1ElementTypes.Class_2001);
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (sm_mm.diagram.providers.Sm_mm_1ElementTypes.Attribute_3001 == req
				.getElementType()) {
			return getGEFWrapper(new sm_mm.diagram.edit.commands.AttributeCreateCommand(
					req));
		}
		return super.getCreateCommand(req);
	}

}
