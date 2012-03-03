package sm_mm.diagram.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.GetParserOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserProvider;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserService;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.parser.ParserHintAdapter;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class Sm_mm_1ParserProvider extends AbstractProvider implements
		IParserProvider {

	/**
	 * @generated
	 */
	private IParser className_5002Parser;

	/**
	 * @generated
	 */
	private IParser getClassName_5002Parser() {
		if (className_5002Parser == null) {
			EAttribute[] features = new EAttribute[] { sm_mm.Sm_mmPackage.eINSTANCE
					.getClass_Name() };
			sm_mm.diagram.parsers.MessageFormatParser parser = new sm_mm.diagram.parsers.MessageFormatParser(
					features);
			className_5002Parser = parser;
		}
		return className_5002Parser;
	}

	/**
	 * @generated
	 */
	private IParser attributeNameType_5001Parser;

	/**
	 * @generated
	 */
	private IParser getAttributeNameType_5001Parser() {
		if (attributeNameType_5001Parser == null) {
			EAttribute[] features = new EAttribute[] {
					sm_mm.Sm_mmPackage.eINSTANCE.getAttribute_Name(),
					sm_mm.Sm_mmPackage.eINSTANCE.getAttribute_Type() };
			sm_mm.diagram.parsers.MessageFormatParser parser = new sm_mm.diagram.parsers.MessageFormatParser(
					features);
			parser.setViewPattern("{0}:{1}"); //$NON-NLS-1$
			parser.setEditorPattern("{0}:{1}"); //$NON-NLS-1$
			parser.setEditPattern("{0}:{1}"); //$NON-NLS-1$
			attributeNameType_5001Parser = parser;
		}
		return attributeNameType_5001Parser;
	}

	/**
	 * @generated
	 */
	private IParser referenceName_6001Parser;

	/**
	 * @generated
	 */
	private IParser getReferenceName_6001Parser() {
		if (referenceName_6001Parser == null) {
			EAttribute[] features = new EAttribute[] { sm_mm.Sm_mmPackage.eINSTANCE
					.getReference_Name() };
			sm_mm.diagram.parsers.MessageFormatParser parser = new sm_mm.diagram.parsers.MessageFormatParser(
					features);
			referenceName_6001Parser = parser;
		}
		return referenceName_6001Parser;
	}

	/**
	 * @generated
	 */
	protected IParser getParser(int visualID) {
		switch (visualID) {
		case sm_mm.diagram.edit.parts.ClassNameEditPart.VISUAL_ID:
			return getClassName_5002Parser();
		case sm_mm.diagram.edit.parts.AttributeNameEditPart.VISUAL_ID:
			return getAttributeNameType_5001Parser();
		case sm_mm.diagram.edit.parts.ReferenceNameEditPart.VISUAL_ID:
			return getReferenceName_6001Parser();
		}
		return null;
	}

	/**
	 * Utility method that consults ParserService
	 * @generated
	 */
	public static IParser getParser(IElementType type, EObject object,
			String parserHint) {
		return ParserService.getInstance().getParser(
				new HintAdapter(type, object, parserHint));
	}

	/**
	 * @generated
	 */
	public IParser getParser(IAdaptable hint) {
		String vid = (String) hint.getAdapter(String.class);
		if (vid != null) {
			return getParser(sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
					.getVisualID(vid));
		}
		View view = (View) hint.getAdapter(View.class);
		if (view != null) {
			return getParser(sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
					.getVisualID(view));
		}
		return null;
	}

	/**
	 * @generated
	 */
	public boolean provides(IOperation operation) {
		if (operation instanceof GetParserOperation) {
			IAdaptable hint = ((GetParserOperation) operation).getHint();
			if (sm_mm.diagram.providers.Sm_mm_1ElementTypes.getElement(hint) == null) {
				return false;
			}
			return getParser(hint) != null;
		}
		return false;
	}

	/**
	 * @generated
	 */
	private static class HintAdapter extends ParserHintAdapter {

		/**
		 * @generated
		 */
		private final IElementType elementType;

		/**
		 * @generated
		 */
		public HintAdapter(IElementType type, EObject object, String parserHint) {
			super(object, parserHint);
			assert type != null;
			elementType = type;
		}

		/**
		 * @generated
		 */
		public Object getAdapter(Class adapter) {
			if (IElementType.class.equals(adapter)) {
				return elementType;
			}
			return super.getAdapter(adapter);
		}
	}

}
