package sm_mm.diagram.edit.parts;

import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

/**
 * @generated
 */
public class Sm_mm_1EditPartFactory implements EditPartFactory {

	/**
	 * @generated
	 */
	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof View) {
			View view = (View) model;
			switch (sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
					.getVisualID(view)) {

			case sm_mm.diagram.edit.parts.MappingModelEditPart.VISUAL_ID:
				return new sm_mm.diagram.edit.parts.MappingModelEditPart(view);

			case sm_mm.diagram.edit.parts.ClassEditPart.VISUAL_ID:
				return new sm_mm.diagram.edit.parts.ClassEditPart(view);

			case sm_mm.diagram.edit.parts.ClassNameEditPart.VISUAL_ID:
				return new sm_mm.diagram.edit.parts.ClassNameEditPart(view);

			case sm_mm.diagram.edit.parts.C2CEditPart.VISUAL_ID:
				return new sm_mm.diagram.edit.parts.C2CEditPart(view);

			case sm_mm.diagram.edit.parts.A2AEditPart.VISUAL_ID:
				return new sm_mm.diagram.edit.parts.A2AEditPart(view);

			case sm_mm.diagram.edit.parts.R2REditPart.VISUAL_ID:
				return new sm_mm.diagram.edit.parts.R2REditPart(view);

			case sm_mm.diagram.edit.parts.A2CEditPart.VISUAL_ID:
				return new sm_mm.diagram.edit.parts.A2CEditPart(view);

			case sm_mm.diagram.edit.parts.AttributeEditPart.VISUAL_ID:
				return new sm_mm.diagram.edit.parts.AttributeEditPart(view);

			case sm_mm.diagram.edit.parts.AttributeNameEditPart.VISUAL_ID:
				return new sm_mm.diagram.edit.parts.AttributeNameEditPart(view);

			case sm_mm.diagram.edit.parts.ClassAttributesEditPart.VISUAL_ID:
				return new sm_mm.diagram.edit.parts.ClassAttributesEditPart(
						view);

			case sm_mm.diagram.edit.parts.C2CLhsEditPart.VISUAL_ID:
				return new sm_mm.diagram.edit.parts.C2CLhsEditPart(view);

			case sm_mm.diagram.edit.parts.C2CRhsEditPart.VISUAL_ID:
				return new sm_mm.diagram.edit.parts.C2CRhsEditPart(view);

			case sm_mm.diagram.edit.parts.A2ALhsEditPart.VISUAL_ID:
				return new sm_mm.diagram.edit.parts.A2ALhsEditPart(view);

			case sm_mm.diagram.edit.parts.A2ARhsEditPart.VISUAL_ID:
				return new sm_mm.diagram.edit.parts.A2ARhsEditPart(view);

			case sm_mm.diagram.edit.parts.ContextOperatorContextMappingsEditPart.VISUAL_ID:
				return new sm_mm.diagram.edit.parts.ContextOperatorContextMappingsEditPart(
						view);

			case sm_mm.diagram.edit.parts.ReferenceEditPart.VISUAL_ID:
				return new sm_mm.diagram.edit.parts.ReferenceEditPart(view);

			case sm_mm.diagram.edit.parts.ReferenceNameEditPart.VISUAL_ID:
				return new sm_mm.diagram.edit.parts.ReferenceNameEditPart(view);

			case sm_mm.diagram.edit.parts.R2RLhsEditPart.VISUAL_ID:
				return new sm_mm.diagram.edit.parts.R2RLhsEditPart(view);

			case sm_mm.diagram.edit.parts.R2RRhsEditPart.VISUAL_ID:
				return new sm_mm.diagram.edit.parts.R2RRhsEditPart(view);

			case sm_mm.diagram.edit.parts.ClassSupertypesEditPart.VISUAL_ID:
				return new sm_mm.diagram.edit.parts.ClassSupertypesEditPart(
						view);

			case sm_mm.diagram.edit.parts.A2CLhsAttributeEditPart.VISUAL_ID:
				return new sm_mm.diagram.edit.parts.A2CLhsAttributeEditPart(
						view);

			case sm_mm.diagram.edit.parts.A2CRhsAttributeEditPart.VISUAL_ID:
				return new sm_mm.diagram.edit.parts.A2CRhsAttributeEditPart(
						view);

			case sm_mm.diagram.edit.parts.A2CRhsClassEditPart.VISUAL_ID:
				return new sm_mm.diagram.edit.parts.A2CRhsClassEditPart(view);

			case sm_mm.diagram.edit.parts.A2CRhsReferenceEditPart.VISUAL_ID:
				return new sm_mm.diagram.edit.parts.A2CRhsReferenceEditPart(
						view);

			}
		}
		return createUnrecognizedEditPart(context, model);
	}

	/**
	 * @generated
	 */
	private EditPart createUnrecognizedEditPart(EditPart context, Object model) {
		// Handle creation of unrecognized child node EditParts here
		return null;
	}

	/**
	 * @generated
	 */
	public static CellEditorLocator getTextCellEditorLocator(
			ITextAwareEditPart source) {
		if (source.getFigure() instanceof WrappingLabel)
			return new TextCellEditorLocator((WrappingLabel) source.getFigure());
		else {
			return new LabelCellEditorLocator((Label) source.getFigure());
		}
	}

	/**
	 * @generated
	 */
	static private class TextCellEditorLocator implements CellEditorLocator {

		/**
		 * @generated
		 */
		private WrappingLabel wrapLabel;

		/**
		 * @generated
		 */
		public TextCellEditorLocator(WrappingLabel wrapLabel) {
			this.wrapLabel = wrapLabel;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getWrapLabel() {
			return wrapLabel;
		}

		/**
		 * @generated
		 */
		public void relocate(CellEditor celleditor) {
			Text text = (Text) celleditor.getControl();
			Rectangle rect = getWrapLabel().getTextBounds().getCopy();
			getWrapLabel().translateToAbsolute(rect);
			if (getWrapLabel().isTextWrapOn()
					&& getWrapLabel().getText().length() > 0) {
				rect.setSize(new Dimension(text.computeSize(rect.width,
						SWT.DEFAULT)));
			} else {
				int avr = FigureUtilities.getFontMetrics(text.getFont())
						.getAverageCharWidth();
				rect.setSize(new Dimension(text.computeSize(SWT.DEFAULT,
						SWT.DEFAULT)).expand(avr * 2, 0));
			}
			if (!rect.equals(new Rectangle(text.getBounds()))) {
				text.setBounds(rect.x, rect.y, rect.width, rect.height);
			}
		}
	}

	/**
	 * @generated
	 */
	private static class LabelCellEditorLocator implements CellEditorLocator {

		/**
		 * @generated
		 */
		private Label label;

		/**
		 * @generated
		 */
		public LabelCellEditorLocator(Label label) {
			this.label = label;
		}

		/**
		 * @generated
		 */
		public Label getLabel() {
			return label;
		}

		/**
		 * @generated
		 */
		public void relocate(CellEditor celleditor) {
			Text text = (Text) celleditor.getControl();
			Rectangle rect = getLabel().getTextBounds().getCopy();
			getLabel().translateToAbsolute(rect);
			int avr = FigureUtilities.getFontMetrics(text.getFont())
					.getAverageCharWidth();
			rect.setSize(new Dimension(text.computeSize(SWT.DEFAULT,
					SWT.DEFAULT)).expand(avr * 2, 0));
			if (!rect.equals(new Rectangle(text.getBounds()))) {
				text.setBounds(rect.x, rect.y, rect.width, rect.height);
			}
		}
	}
}
