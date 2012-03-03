package sm_mm.diagram.edit.parts;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.Graphics;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITreeBranchEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class ContextOperatorContextMappingsEditPart extends
		ConnectionNodeEditPart implements ITreeBranchEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 4005;

	/**
	 * @generated
	 */
	public ContextOperatorContextMappingsEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(
				EditPolicyRoles.SEMANTIC_ROLE,
				new sm_mm.diagram.edit.policies.ContextOperatorContextMappingsItemSemanticEditPolicy());
	}

	/**
	 * Creates figure for this edit part.
	 * 
	 * Body of this method does not depend on settings in generation model
	 * so you may safely remove <i>generated</i> tag and modify it.
	 * 
	 * @generated
	 */

	protected Connection createConnectionFigure() {
		return new C2CContextMappingsFigure();
	}

	/**
	 * @generated
	 */
	public C2CContextMappingsFigure getPrimaryShape() {
		return (C2CContextMappingsFigure) getFigure();
	}

	/**
	 * @generated
	 */
	public class C2CContextMappingsFigure extends PolylineConnectionEx {

		/**
		 * @generated
		 */
		public C2CContextMappingsFigure() {
			this.setLineWidth(1);
			this.setLineStyle(Graphics.LINE_DASH);

		}

	}

}
