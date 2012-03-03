package sm_mm.diagram.navigator;

import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserOptions;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.ITreePathLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.ViewerLabel;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonLabelProvider;

/**
 * @generated
 */
public class Sm_mm_1NavigatorLabelProvider extends LabelProvider implements
		ICommonLabelProvider, ITreePathLabelProvider {

	/**
	 * @generated
	 */
	static {
		sm_mm.diagram.part.Sm_mm_1DiagramEditorPlugin
				.getInstance()
				.getImageRegistry()
				.put(
						"Navigator?UnknownElement", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
		sm_mm.diagram.part.Sm_mm_1DiagramEditorPlugin
				.getInstance()
				.getImageRegistry()
				.put(
						"Navigator?ImageNotFound", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	public void updateLabel(ViewerLabel label, TreePath elementPath) {
		Object element = elementPath.getLastSegment();
		if (element instanceof sm_mm.diagram.navigator.Sm_mm_1NavigatorItem
				&& !isOwnView(((sm_mm.diagram.navigator.Sm_mm_1NavigatorItem) element)
						.getView())) {
			return;
		}
		label.setText(getText(element));
		label.setImage(getImage(element));
	}

	/**
	 * @generated
	 */
	public Image getImage(Object element) {
		if (element instanceof sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup) {
			sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup group = (sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup) element;
			return sm_mm.diagram.part.Sm_mm_1DiagramEditorPlugin.getInstance()
					.getBundledImage(group.getIcon());
		}

		if (element instanceof sm_mm.diagram.navigator.Sm_mm_1NavigatorItem) {
			sm_mm.diagram.navigator.Sm_mm_1NavigatorItem navigatorItem = (sm_mm.diagram.navigator.Sm_mm_1NavigatorItem) element;
			if (!isOwnView(navigatorItem.getView())) {
				return super.getImage(element);
			}
			return getImage(navigatorItem.getView());
		}

		return super.getImage(element);
	}

	/**
	 * @generated
	 */
	public Image getImage(View view) {
		switch (sm_mm.diagram.part.Sm_mm_1VisualIDRegistry.getVisualID(view)) {
		case sm_mm.diagram.edit.parts.MappingModelEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Diagram?http://big.tuwien.ac.at/smartmatcher/ui/gmf/sm_mm1?MappingModel", sm_mm.diagram.providers.Sm_mm_1ElementTypes.MappingModel_1000); //$NON-NLS-1$
		case sm_mm.diagram.edit.parts.ClassEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://big.tuwien.ac.at/smartmatcher/ui/gmf/sm_mm1?Class", sm_mm.diagram.providers.Sm_mm_1ElementTypes.Class_2001); //$NON-NLS-1$
		case sm_mm.diagram.edit.parts.C2CEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://big.tuwien.ac.at/smartmatcher/ui/gmf/sm_mm1?A2A", sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2A_2002); //$NON-NLS-1$
		case sm_mm.diagram.edit.parts.A2AEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://big.tuwien.ac.at/smartmatcher/ui/gmf/sm_mm1?C2C", sm_mm.diagram.providers.Sm_mm_1ElementTypes.C2C_2003); //$NON-NLS-1$
		case sm_mm.diagram.edit.parts.R2REditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://big.tuwien.ac.at/smartmatcher/ui/gmf/sm_mm1?R2R", sm_mm.diagram.providers.Sm_mm_1ElementTypes.R2R_2004); //$NON-NLS-1$
		case sm_mm.diagram.edit.parts.A2CEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://big.tuwien.ac.at/smartmatcher/ui/gmf/sm_mm1?A2C", sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2C_2005); //$NON-NLS-1$
		case sm_mm.diagram.edit.parts.AttributeEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http://big.tuwien.ac.at/smartmatcher/ui/gmf/sm_mm1?Attribute", sm_mm.diagram.providers.Sm_mm_1ElementTypes.Attribute_3001); //$NON-NLS-1$
		case sm_mm.diagram.edit.parts.C2CLhsEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://big.tuwien.ac.at/smartmatcher/ui/gmf/sm_mm1?C2C?lhs", sm_mm.diagram.providers.Sm_mm_1ElementTypes.C2CLhs_4001); //$NON-NLS-1$
		case sm_mm.diagram.edit.parts.C2CRhsEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://big.tuwien.ac.at/smartmatcher/ui/gmf/sm_mm1?C2C?rhs", sm_mm.diagram.providers.Sm_mm_1ElementTypes.C2CRhs_4002); //$NON-NLS-1$
		case sm_mm.diagram.edit.parts.A2ALhsEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://big.tuwien.ac.at/smartmatcher/ui/gmf/sm_mm1?A2A?lhs", sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2ALhs_4003); //$NON-NLS-1$
		case sm_mm.diagram.edit.parts.A2ARhsEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://big.tuwien.ac.at/smartmatcher/ui/gmf/sm_mm1?A2A?rhs", sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2ARhs_4004); //$NON-NLS-1$
		case sm_mm.diagram.edit.parts.ContextOperatorContextMappingsEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://big.tuwien.ac.at/smartmatcher/ui/gmf/sm_mm1?ContextOperator?contextMappings", sm_mm.diagram.providers.Sm_mm_1ElementTypes.ContextOperatorContextMappings_4005); //$NON-NLS-1$
		case sm_mm.diagram.edit.parts.ReferenceEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://big.tuwien.ac.at/smartmatcher/ui/gmf/sm_mm1?Reference", sm_mm.diagram.providers.Sm_mm_1ElementTypes.Reference_4006); //$NON-NLS-1$
		case sm_mm.diagram.edit.parts.R2RLhsEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://big.tuwien.ac.at/smartmatcher/ui/gmf/sm_mm1?R2R?lhs", sm_mm.diagram.providers.Sm_mm_1ElementTypes.R2RLhs_4007); //$NON-NLS-1$
		case sm_mm.diagram.edit.parts.R2RRhsEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://big.tuwien.ac.at/smartmatcher/ui/gmf/sm_mm1?R2R?rhs", sm_mm.diagram.providers.Sm_mm_1ElementTypes.R2RRhs_4008); //$NON-NLS-1$
		case sm_mm.diagram.edit.parts.ClassSupertypesEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://big.tuwien.ac.at/smartmatcher/ui/gmf/sm_mm1?Class?supertypes", sm_mm.diagram.providers.Sm_mm_1ElementTypes.ClassSupertypes_4009); //$NON-NLS-1$
		case sm_mm.diagram.edit.parts.A2CLhsAttributeEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://big.tuwien.ac.at/smartmatcher/ui/gmf/sm_mm1?A2C?lhsAttribute", sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2CLhsAttribute_4010); //$NON-NLS-1$
		case sm_mm.diagram.edit.parts.A2CRhsAttributeEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://big.tuwien.ac.at/smartmatcher/ui/gmf/sm_mm1?A2C?rhsAttribute", sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2CRhsAttribute_4011); //$NON-NLS-1$
		case sm_mm.diagram.edit.parts.A2CRhsClassEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://big.tuwien.ac.at/smartmatcher/ui/gmf/sm_mm1?A2C?rhsClass", sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2CRhsClass_4012); //$NON-NLS-1$
		case sm_mm.diagram.edit.parts.A2CRhsReferenceEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://big.tuwien.ac.at/smartmatcher/ui/gmf/sm_mm1?A2C?rhsReference", sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2CRhsReference_4013); //$NON-NLS-1$
		}
		return getImage("Navigator?UnknownElement", null); //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private Image getImage(String key, IElementType elementType) {
		ImageRegistry imageRegistry = sm_mm.diagram.part.Sm_mm_1DiagramEditorPlugin
				.getInstance().getImageRegistry();
		Image image = imageRegistry.get(key);
		if (image == null
				&& elementType != null
				&& sm_mm.diagram.providers.Sm_mm_1ElementTypes
						.isKnownElementType(elementType)) {
			image = sm_mm.diagram.providers.Sm_mm_1ElementTypes
					.getImage(elementType);
			imageRegistry.put(key, image);
		}

		if (image == null) {
			image = imageRegistry.get("Navigator?ImageNotFound"); //$NON-NLS-1$
			imageRegistry.put(key, image);
		}
		return image;
	}

	/**
	 * @generated
	 */
	public String getText(Object element) {
		if (element instanceof sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup) {
			sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup group = (sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup) element;
			return group.getGroupName();
		}

		if (element instanceof sm_mm.diagram.navigator.Sm_mm_1NavigatorItem) {
			sm_mm.diagram.navigator.Sm_mm_1NavigatorItem navigatorItem = (sm_mm.diagram.navigator.Sm_mm_1NavigatorItem) element;
			if (!isOwnView(navigatorItem.getView())) {
				return null;
			}
			return getText(navigatorItem.getView());
		}

		return super.getText(element);
	}

	/**
	 * @generated
	 */
	public String getText(View view) {
		if (view.getElement() != null && view.getElement().eIsProxy()) {
			return getUnresolvedDomainElementProxyText(view);
		}
		switch (sm_mm.diagram.part.Sm_mm_1VisualIDRegistry.getVisualID(view)) {
		case sm_mm.diagram.edit.parts.MappingModelEditPart.VISUAL_ID:
			return getMappingModel_1000Text(view);
		case sm_mm.diagram.edit.parts.ClassEditPart.VISUAL_ID:
			return getClass_2001Text(view);
		case sm_mm.diagram.edit.parts.C2CEditPart.VISUAL_ID:
			return getA2A_2002Text(view);
		case sm_mm.diagram.edit.parts.A2AEditPart.VISUAL_ID:
			return getC2C_2003Text(view);
		case sm_mm.diagram.edit.parts.R2REditPart.VISUAL_ID:
			return getR2R_2004Text(view);
		case sm_mm.diagram.edit.parts.A2CEditPart.VISUAL_ID:
			return getA2C_2005Text(view);
		case sm_mm.diagram.edit.parts.AttributeEditPart.VISUAL_ID:
			return getAttribute_3001Text(view);
		case sm_mm.diagram.edit.parts.C2CLhsEditPart.VISUAL_ID:
			return getC2CLhs_4001Text(view);
		case sm_mm.diagram.edit.parts.C2CRhsEditPart.VISUAL_ID:
			return getC2CRhs_4002Text(view);
		case sm_mm.diagram.edit.parts.A2ALhsEditPart.VISUAL_ID:
			return getA2ALhs_4003Text(view);
		case sm_mm.diagram.edit.parts.A2ARhsEditPart.VISUAL_ID:
			return getA2ARhs_4004Text(view);
		case sm_mm.diagram.edit.parts.ContextOperatorContextMappingsEditPart.VISUAL_ID:
			return getContextOperatorContextMappings_4005Text(view);
		case sm_mm.diagram.edit.parts.ReferenceEditPart.VISUAL_ID:
			return getReference_4006Text(view);
		case sm_mm.diagram.edit.parts.R2RLhsEditPart.VISUAL_ID:
			return getR2RLhs_4007Text(view);
		case sm_mm.diagram.edit.parts.R2RRhsEditPart.VISUAL_ID:
			return getR2RRhs_4008Text(view);
		case sm_mm.diagram.edit.parts.ClassSupertypesEditPart.VISUAL_ID:
			return getClassSupertypes_4009Text(view);
		case sm_mm.diagram.edit.parts.A2CLhsAttributeEditPart.VISUAL_ID:
			return getA2CLhsAttribute_4010Text(view);
		case sm_mm.diagram.edit.parts.A2CRhsAttributeEditPart.VISUAL_ID:
			return getA2CRhsAttribute_4011Text(view);
		case sm_mm.diagram.edit.parts.A2CRhsClassEditPart.VISUAL_ID:
			return getA2CRhsClass_4012Text(view);
		case sm_mm.diagram.edit.parts.A2CRhsReferenceEditPart.VISUAL_ID:
			return getA2CRhsReference_4013Text(view);
		}
		return getUnknownElementText(view);
	}

	/**
	 * @generated
	 */
	private String getMappingModel_1000Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private String getClass_2001Text(View view) {
		IParser parser = sm_mm.diagram.providers.Sm_mm_1ParserProvider
				.getParser(
						sm_mm.diagram.providers.Sm_mm_1ElementTypes.Class_2001,
						view.getElement() != null ? view.getElement() : view,
						sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
								.getType(sm_mm.diagram.edit.parts.ClassNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			sm_mm.diagram.part.Sm_mm_1DiagramEditorPlugin.getInstance()
					.logError("Parser was not found for label " + 5002); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getA2A_2002Text(View view) {
		sm_mm.A2A domainModelElement = (sm_mm.A2A) view.getElement();
		if (domainModelElement != null) {
			return domainModelElement.getName();
		} else {
			sm_mm.diagram.part.Sm_mm_1DiagramEditorPlugin
					.getInstance()
					.logError(
							"No domain element for view with visualID = " + 2002); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getC2C_2003Text(View view) {
		sm_mm.C2C domainModelElement = (sm_mm.C2C) view.getElement();
		if (domainModelElement != null) {
			return domainModelElement.getName();
		} else {
			sm_mm.diagram.part.Sm_mm_1DiagramEditorPlugin
					.getInstance()
					.logError(
							"No domain element for view with visualID = " + 2003); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getR2R_2004Text(View view) {
		sm_mm.R2R domainModelElement = (sm_mm.R2R) view.getElement();
		if (domainModelElement != null) {
			return domainModelElement.getName();
		} else {
			sm_mm.diagram.part.Sm_mm_1DiagramEditorPlugin
					.getInstance()
					.logError(
							"No domain element for view with visualID = " + 2004); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getA2C_2005Text(View view) {
		sm_mm.A2C domainModelElement = (sm_mm.A2C) view.getElement();
		if (domainModelElement != null) {
			return domainModelElement.getName();
		} else {
			sm_mm.diagram.part.Sm_mm_1DiagramEditorPlugin
					.getInstance()
					.logError(
							"No domain element for view with visualID = " + 2005); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getAttribute_3001Text(View view) {
		IParser parser = sm_mm.diagram.providers.Sm_mm_1ParserProvider
				.getParser(
						sm_mm.diagram.providers.Sm_mm_1ElementTypes.Attribute_3001,
						view.getElement() != null ? view.getElement() : view,
						sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
								.getType(sm_mm.diagram.edit.parts.AttributeNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			sm_mm.diagram.part.Sm_mm_1DiagramEditorPlugin.getInstance()
					.logError("Parser was not found for label " + 5001); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getC2CLhs_4001Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private String getC2CRhs_4002Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private String getA2ALhs_4003Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private String getA2ARhs_4004Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private String getContextOperatorContextMappings_4005Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private String getReference_4006Text(View view) {
		IParser parser = sm_mm.diagram.providers.Sm_mm_1ParserProvider
				.getParser(
						sm_mm.diagram.providers.Sm_mm_1ElementTypes.Reference_4006,
						view.getElement() != null ? view.getElement() : view,
						sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
								.getType(sm_mm.diagram.edit.parts.ReferenceNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			sm_mm.diagram.part.Sm_mm_1DiagramEditorPlugin.getInstance()
					.logError("Parser was not found for label " + 6001); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getR2RLhs_4007Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private String getR2RRhs_4008Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private String getClassSupertypes_4009Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private String getA2CLhsAttribute_4010Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private String getA2CRhsAttribute_4011Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private String getA2CRhsClass_4012Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private String getA2CRhsReference_4013Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private String getUnknownElementText(View view) {
		return "<UnknownElement Visual_ID = " + view.getType() + ">"; //$NON-NLS-1$  //$NON-NLS-2$
	}

	/**
	 * @generated
	 */
	private String getUnresolvedDomainElementProxyText(View view) {
		return "<Unresolved domain element Visual_ID = " + view.getType() + ">"; //$NON-NLS-1$  //$NON-NLS-2$
	}

	/**
	 * @generated
	 */
	public void init(ICommonContentExtensionSite aConfig) {
	}

	/**
	 * @generated
	 */
	public void restoreState(IMemento aMemento) {
	}

	/**
	 * @generated
	 */
	public void saveState(IMemento aMemento) {
	}

	/**
	 * @generated
	 */
	public String getDescription(Object anElement) {
		return null;
	}

	/**
	 * @generated
	 */
	private boolean isOwnView(View view) {
		return sm_mm.diagram.edit.parts.MappingModelEditPart.MODEL_ID
				.equals(sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
						.getModelID(view));
	}

}
