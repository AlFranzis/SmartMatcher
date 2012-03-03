package sm_mm.diagram.edit.parts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.FlowLayoutEditPolicy;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;

/**
 * @generated
 */
public class A2CEditPart extends ShapeNodeEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 2005;

	/**
	 * @generated
	 */
	protected IFigure contentPane;

	/**
	 * @generated
	 */
	protected IFigure primaryShape;

	/**
	 * @generated
	 */
	public A2CEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new sm_mm.diagram.edit.policies.A2CItemSemanticEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
		// XXX need an SCR to runtime to have another abstract superclass that would let children add reasonable editpolicies
		// removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.CONNECTION_HANDLES_ROLE);
	}

	/**
	 * @generated
	 */
	protected LayoutEditPolicy createLayoutEditPolicy() {

		FlowLayoutEditPolicy lep = new FlowLayoutEditPolicy() {

			protected Command createAddCommand(EditPart child, EditPart after) {
				return null;
			}

			protected Command createMoveChildCommand(EditPart child,
					EditPart after) {
				return null;
			}

			protected Command getCreateCommand(CreateRequest request) {
				return null;
			}
		};
		return lep;
	}

	/**
	 * @generated
	 */
	protected IFigure createNodeShape() {
		A2CFigure figure = new A2CFigure();
		return primaryShape = figure;
	}

	/**
	 * @generated
	 */
	public A2CFigure getPrimaryShape() {
		return (A2CFigure) primaryShape;
	}

	/**
	 * @generated
	 */
	protected NodeFigure createNodePlate() {
		DefaultSizeNodeFigure result = new DefaultSizeNodeFigure(40, 40);
		return result;
	}

	/**
	 * Creates figure for this edit part.
	 * 
	 * Body of this method does not depend on settings in generation model
	 * so you may safely remove <i>generated</i> tag and modify it.
	 * 
	 * @generated
	 */
	protected NodeFigure createNodeFigure() {
		NodeFigure figure = createNodePlate();
		figure.setLayoutManager(new StackLayout());
		IFigure shape = createNodeShape();
		figure.add(shape);
		contentPane = setupContentPane(shape);
		return figure;
	}

	/**
	 * Default implementation treats passed figure as content pane.
	 * Respects layout one may have set for generated figure.
	 * @param nodeShape instance of generated figure class
	 * @generated
	 */
	protected IFigure setupContentPane(IFigure nodeShape) {
		return nodeShape; // use nodeShape itself as contentPane
	}

	/**
	 * @generated
	 */
	public IFigure getContentPane() {
		if (contentPane != null) {
			return contentPane;
		}
		return super.getContentPane();
	}

	/**
	 * @generated
	 */
	protected void setForegroundColor(Color color) {
		if (primaryShape != null) {
			primaryShape.setForegroundColor(color);
		}
	}

	/**
	 * @generated
	 */
	protected void setBackgroundColor(Color color) {
		if (primaryShape != null) {
			primaryShape.setBackgroundColor(color);
		}
	}

	/**
	 * @generated
	 */
	protected void setLineWidth(int width) {
		if (primaryShape instanceof Shape) {
			((Shape) primaryShape).setLineWidth(width);
		}
	}

	/**
	 * @generated
	 */
	protected void setLineType(int style) {
		if (primaryShape instanceof Shape) {
			((Shape) primaryShape).setLineStyle(style);
		}
	}

	/**
	 * @generated
	 */
	public List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/getMARelTypesOnSource() {
		List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/types = new ArrayList/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/();
		types
				.add(sm_mm.diagram.providers.Sm_mm_1ElementTypes.ContextOperatorContextMappings_4005);
		types
				.add(sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2CLhsAttribute_4010);
		types
				.add(sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2CRhsAttribute_4011);
		types.add(sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2CRhsClass_4012);
		return types;
	}

	/**
	 * @generated
	 */
	public List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/getMARelTypesOnSourceAndTarget(
			IGraphicalEditPart targetEditPart) {
		List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/types = new ArrayList/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/();
		if (targetEditPart instanceof sm_mm.diagram.edit.parts.C2CEditPart) {
			types
					.add(sm_mm.diagram.providers.Sm_mm_1ElementTypes.ContextOperatorContextMappings_4005);
		}
		if (targetEditPart instanceof sm_mm.diagram.edit.parts.A2AEditPart) {
			types
					.add(sm_mm.diagram.providers.Sm_mm_1ElementTypes.ContextOperatorContextMappings_4005);
		}
		if (targetEditPart instanceof sm_mm.diagram.edit.parts.R2REditPart) {
			types
					.add(sm_mm.diagram.providers.Sm_mm_1ElementTypes.ContextOperatorContextMappings_4005);
		}
		if (targetEditPart instanceof sm_mm.diagram.edit.parts.A2CEditPart) {
			types
					.add(sm_mm.diagram.providers.Sm_mm_1ElementTypes.ContextOperatorContextMappings_4005);
		}
		if (targetEditPart instanceof sm_mm.diagram.edit.parts.AttributeEditPart) {
			types
					.add(sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2CLhsAttribute_4010);
		}
		if (targetEditPart instanceof sm_mm.diagram.edit.parts.AttributeEditPart) {
			types
					.add(sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2CRhsAttribute_4011);
		}
		if (targetEditPart instanceof sm_mm.diagram.edit.parts.ClassEditPart) {
			types
					.add(sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2CRhsClass_4012);
		}
		return types;
	}

	/**
	 * @generated
	 */
	public List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/getMATypesForTarget(
			IElementType relationshipType) {
		List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/types = new ArrayList/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/();
		if (relationshipType == sm_mm.diagram.providers.Sm_mm_1ElementTypes.ContextOperatorContextMappings_4005) {
			types.add(sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2A_2002);
		}
		if (relationshipType == sm_mm.diagram.providers.Sm_mm_1ElementTypes.ContextOperatorContextMappings_4005) {
			types.add(sm_mm.diagram.providers.Sm_mm_1ElementTypes.C2C_2003);
		}
		if (relationshipType == sm_mm.diagram.providers.Sm_mm_1ElementTypes.ContextOperatorContextMappings_4005) {
			types.add(sm_mm.diagram.providers.Sm_mm_1ElementTypes.R2R_2004);
		}
		if (relationshipType == sm_mm.diagram.providers.Sm_mm_1ElementTypes.ContextOperatorContextMappings_4005) {
			types.add(sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2C_2005);
		}
		if (relationshipType == sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2CLhsAttribute_4010) {
			types
					.add(sm_mm.diagram.providers.Sm_mm_1ElementTypes.Attribute_3001);
		}
		if (relationshipType == sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2CRhsAttribute_4011) {
			types
					.add(sm_mm.diagram.providers.Sm_mm_1ElementTypes.Attribute_3001);
		}
		if (relationshipType == sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2CRhsClass_4012) {
			types.add(sm_mm.diagram.providers.Sm_mm_1ElementTypes.Class_2001);
		}
		return types;
	}

	/**
	 * @generated
	 */
	public List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/getMARelTypesOnTarget() {
		List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/types = new ArrayList/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/();
		types
				.add(sm_mm.diagram.providers.Sm_mm_1ElementTypes.ContextOperatorContextMappings_4005);
		return types;
	}

	/**
	 * @generated
	 */
	public List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/getMATypesForSource(
			IElementType relationshipType) {
		List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/types = new ArrayList/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/();
		if (relationshipType == sm_mm.diagram.providers.Sm_mm_1ElementTypes.ContextOperatorContextMappings_4005) {
			types.add(sm_mm.diagram.providers.Sm_mm_1ElementTypes.C2C_2003);
		}
		if (relationshipType == sm_mm.diagram.providers.Sm_mm_1ElementTypes.ContextOperatorContextMappings_4005) {
			types.add(sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2C_2005);
		}
		return types;
	}

	/**
	 * @generated
	 */
	public class A2CFigure extends Ellipse {

		/**
		 * @generated
		 */
		public A2CFigure() {

			FlowLayout layoutThis = new FlowLayout();
			layoutThis.setStretchMinorAxis(false);
			layoutThis.setMinorAlignment(FlowLayout.ALIGN_LEFTTOP);

			layoutThis.setMajorAlignment(FlowLayout.ALIGN_LEFTTOP);
			layoutThis.setMajorSpacing(5);
			layoutThis.setMinorSpacing(5);
			layoutThis.setHorizontal(true);

			this.setLayoutManager(layoutThis);

			this.setLineWidth(1);
			this.setForegroundColor(ColorConstants.yellow);
			this.setBackgroundColor(ColorConstants.yellow);
		}

		/**
		 * @generated
		 */
		private boolean myUseLocalCoordinates = false;

		/**
		 * @generated
		 */
		protected boolean useLocalCoordinates() {
			return myUseLocalCoordinates;
		}

		/**
		 * @generated
		 */
		protected void setUseLocalCoordinates(boolean useLocalCoordinates) {
			myUseLocalCoordinates = useLocalCoordinates;
		}

	}

}
