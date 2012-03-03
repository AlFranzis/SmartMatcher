package sm_mm.diagram.part;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeConnectionTool;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;

/**
 * @generated
 */
public class Sm_mm_1PaletteFactory {

	/**
	 * @generated
	 */
	public void fillPalette(PaletteRoot paletteRoot) {
		paletteRoot.add(createSm_mm1Group());
	}

	/**
	 * Creates "sm_mm" palette tool group
	 * @generated
	 */
	private PaletteContainer createSm_mm1Group() {
		PaletteGroup paletteContainer = new PaletteGroup(
				sm_mm.diagram.part.Messages.Sm_mm1Group_title);
		paletteContainer.setId("createSm_mm1Group"); //$NON-NLS-1$
		paletteContainer.add(createClass1CreationTool());
		paletteContainer.add(createClassAttributes2CreationTool());
		paletteContainer.add(createAttribute3CreationTool());
		paletteContainer.add(createC2C4CreationTool());
		paletteContainer.add(createA2A5CreationTool());
		paletteContainer.add(createC2CLHS6CreationTool());
		paletteContainer.add(createC2CRHS7CreationTool());
		paletteContainer.add(createA2ALHS8CreationTool());
		paletteContainer.add(createA2ARHS9CreationTool());
		paletteContainer.add(createC2Ccontextlink10CreationTool());
		paletteContainer.add(createReference11CreationTool());
		paletteContainer.add(createR2RLHS12CreationTool());
		paletteContainer.add(createR2RRHS13CreationTool());
		paletteContainer.add(createR2R14CreationTool());
		paletteContainer.add(createGeneralization15CreationTool());
		paletteContainer.add(createA2C16CreationTool());
		paletteContainer.add(createMappinglink17CreationTool());
		return paletteContainer;
	}

	/**
	 * @generated
	 */
	private ToolEntry createClass1CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(sm_mm.diagram.providers.Sm_mm_1ElementTypes.Class_2001);
		NodeToolEntry entry = new NodeToolEntry(
				sm_mm.diagram.part.Messages.Class1CreationTool_title,
				sm_mm.diagram.part.Messages.Class1CreationTool_desc, types);
		entry.setId("createClass1CreationTool"); //$NON-NLS-1$
		entry
				.setSmallIcon(sm_mm.diagram.providers.Sm_mm_1ElementTypes
						.getImageDescriptor(sm_mm.diagram.providers.Sm_mm_1ElementTypes.Class_2001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createClassAttributes2CreationTool() {
		ToolEntry entry = new ToolEntry(
				sm_mm.diagram.part.Messages.ClassAttributes2CreationTool_title,
				sm_mm.diagram.part.Messages.ClassAttributes2CreationTool_desc,
				null, null) {
		};
		entry.setId("createClassAttributes2CreationTool"); //$NON-NLS-1$
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createAttribute3CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(sm_mm.diagram.providers.Sm_mm_1ElementTypes.Attribute_3001);
		NodeToolEntry entry = new NodeToolEntry(
				sm_mm.diagram.part.Messages.Attribute3CreationTool_title,
				sm_mm.diagram.part.Messages.Attribute3CreationTool_desc, types);
		entry.setId("createAttribute3CreationTool"); //$NON-NLS-1$
		entry
				.setSmallIcon(sm_mm.diagram.providers.Sm_mm_1ElementTypes
						.getImageDescriptor(sm_mm.diagram.providers.Sm_mm_1ElementTypes.Attribute_3001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createC2C4CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(sm_mm.diagram.providers.Sm_mm_1ElementTypes.C2C_2003);
		NodeToolEntry entry = new NodeToolEntry(
				sm_mm.diagram.part.Messages.C2C4CreationTool_title,
				sm_mm.diagram.part.Messages.C2C4CreationTool_desc, types);
		entry.setId("createC2C4CreationTool"); //$NON-NLS-1$
		entry
				.setSmallIcon(sm_mm.diagram.providers.Sm_mm_1ElementTypes
						.getImageDescriptor(sm_mm.diagram.providers.Sm_mm_1ElementTypes.C2C_2003));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createA2A5CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2A_2002);
		NodeToolEntry entry = new NodeToolEntry(
				sm_mm.diagram.part.Messages.A2A5CreationTool_title,
				sm_mm.diagram.part.Messages.A2A5CreationTool_desc, types);
		entry.setId("createA2A5CreationTool"); //$NON-NLS-1$
		entry
				.setSmallIcon(sm_mm.diagram.providers.Sm_mm_1ElementTypes
						.getImageDescriptor(sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2A_2002));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createC2CLHS6CreationTool() {
		ToolEntry entry = new ToolEntry(
				sm_mm.diagram.part.Messages.C2CLHS6CreationTool_title,
				sm_mm.diagram.part.Messages.C2CLHS6CreationTool_desc, null,
				null) {
		};
		entry.setId("createC2CLHS6CreationTool"); //$NON-NLS-1$
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createC2CRHS7CreationTool() {
		ToolEntry entry = new ToolEntry(
				sm_mm.diagram.part.Messages.C2CRHS7CreationTool_title,
				sm_mm.diagram.part.Messages.C2CRHS7CreationTool_desc, null,
				null) {
		};
		entry.setId("createC2CRHS7CreationTool"); //$NON-NLS-1$
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createA2ALHS8CreationTool() {
		ToolEntry entry = new ToolEntry(
				sm_mm.diagram.part.Messages.A2ALHS8CreationTool_title,
				sm_mm.diagram.part.Messages.A2ALHS8CreationTool_desc, null,
				null) {
		};
		entry.setId("createA2ALHS8CreationTool"); //$NON-NLS-1$
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createA2ARHS9CreationTool() {
		ToolEntry entry = new ToolEntry(
				sm_mm.diagram.part.Messages.A2ARHS9CreationTool_title,
				sm_mm.diagram.part.Messages.A2ARHS9CreationTool_desc, null,
				null) {
		};
		entry.setId("createA2ARHS9CreationTool"); //$NON-NLS-1$
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createC2Ccontextlink10CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types
				.add(sm_mm.diagram.providers.Sm_mm_1ElementTypes.ContextOperatorContextMappings_4005);
		LinkToolEntry entry = new LinkToolEntry(
				sm_mm.diagram.part.Messages.C2Ccontextlink10CreationTool_title,
				sm_mm.diagram.part.Messages.C2Ccontextlink10CreationTool_desc,
				types);
		entry.setId("createC2Ccontextlink10CreationTool"); //$NON-NLS-1$
		entry
				.setSmallIcon(sm_mm.diagram.providers.Sm_mm_1ElementTypes
						.getImageDescriptor(sm_mm.diagram.providers.Sm_mm_1ElementTypes.ContextOperatorContextMappings_4005));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createReference11CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(sm_mm.diagram.providers.Sm_mm_1ElementTypes.Reference_4006);
		LinkToolEntry entry = new LinkToolEntry(
				sm_mm.diagram.part.Messages.Reference11CreationTool_title,
				sm_mm.diagram.part.Messages.Reference11CreationTool_desc, types);
		entry.setId("createReference11CreationTool"); //$NON-NLS-1$
		entry
				.setSmallIcon(sm_mm.diagram.providers.Sm_mm_1ElementTypes
						.getImageDescriptor(sm_mm.diagram.providers.Sm_mm_1ElementTypes.Reference_4006));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createR2RLHS12CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(sm_mm.diagram.providers.Sm_mm_1ElementTypes.R2RLhs_4007);
		LinkToolEntry entry = new LinkToolEntry(
				sm_mm.diagram.part.Messages.R2RLHS12CreationTool_title,
				sm_mm.diagram.part.Messages.R2RLHS12CreationTool_desc, types);
		entry.setId("createR2RLHS12CreationTool"); //$NON-NLS-1$
		entry
				.setSmallIcon(sm_mm.diagram.providers.Sm_mm_1ElementTypes
						.getImageDescriptor(sm_mm.diagram.providers.Sm_mm_1ElementTypes.R2RLhs_4007));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createR2RRHS13CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(sm_mm.diagram.providers.Sm_mm_1ElementTypes.R2RRhs_4008);
		LinkToolEntry entry = new LinkToolEntry(
				sm_mm.diagram.part.Messages.R2RRHS13CreationTool_title,
				sm_mm.diagram.part.Messages.R2RRHS13CreationTool_desc, types);
		entry.setId("createR2RRHS13CreationTool"); //$NON-NLS-1$
		entry
				.setSmallIcon(sm_mm.diagram.providers.Sm_mm_1ElementTypes
						.getImageDescriptor(sm_mm.diagram.providers.Sm_mm_1ElementTypes.R2RRhs_4008));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createR2R14CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(sm_mm.diagram.providers.Sm_mm_1ElementTypes.R2R_2004);
		NodeToolEntry entry = new NodeToolEntry(
				sm_mm.diagram.part.Messages.R2R14CreationTool_title,
				sm_mm.diagram.part.Messages.R2R14CreationTool_desc, types);
		entry.setId("createR2R14CreationTool"); //$NON-NLS-1$
		entry
				.setSmallIcon(sm_mm.diagram.providers.Sm_mm_1ElementTypes
						.getImageDescriptor(sm_mm.diagram.providers.Sm_mm_1ElementTypes.R2R_2004));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createGeneralization15CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types
				.add(sm_mm.diagram.providers.Sm_mm_1ElementTypes.ClassSupertypes_4009);
		LinkToolEntry entry = new LinkToolEntry(
				sm_mm.diagram.part.Messages.Generalization15CreationTool_title,
				sm_mm.diagram.part.Messages.Generalization15CreationTool_desc,
				types);
		entry.setId("createGeneralization15CreationTool"); //$NON-NLS-1$
		entry
				.setSmallIcon(sm_mm.diagram.providers.Sm_mm_1ElementTypes
						.getImageDescriptor(sm_mm.diagram.providers.Sm_mm_1ElementTypes.ClassSupertypes_4009));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createA2C16CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2C_2005);
		NodeToolEntry entry = new NodeToolEntry(
				sm_mm.diagram.part.Messages.A2C16CreationTool_title,
				sm_mm.diagram.part.Messages.A2C16CreationTool_desc, types);
		entry.setId("createA2C16CreationTool"); //$NON-NLS-1$
		entry
				.setSmallIcon(sm_mm.diagram.providers.Sm_mm_1ElementTypes
						.getImageDescriptor(sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2C_2005));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createMappinglink17CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(8);
		types.add(sm_mm.diagram.providers.Sm_mm_1ElementTypes.C2CLhs_4001);
		types.add(sm_mm.diagram.providers.Sm_mm_1ElementTypes.C2CRhs_4002);
		types.add(sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2ALhs_4003);
		types.add(sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2ARhs_4004);
		types
				.add(sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2CLhsAttribute_4010);
		types
				.add(sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2CRhsAttribute_4011);
		types.add(sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2CRhsClass_4012);
		types
				.add(sm_mm.diagram.providers.Sm_mm_1ElementTypes.A2CRhsReference_4013);
		LinkToolEntry entry = new LinkToolEntry(
				sm_mm.diagram.part.Messages.Mappinglink17CreationTool_title,
				sm_mm.diagram.part.Messages.Mappinglink17CreationTool_desc,
				types);
		entry.setId("createMappinglink17CreationTool"); //$NON-NLS-1$
		entry
				.setSmallIcon(sm_mm.diagram.providers.Sm_mm_1ElementTypes
						.getImageDescriptor(sm_mm.diagram.providers.Sm_mm_1ElementTypes.C2CLhs_4001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private static class NodeToolEntry extends ToolEntry {

		/**
		 * @generated
		 */
		private final List elementTypes;

		/**
		 * @generated
		 */
		private NodeToolEntry(String title, String description,
				List elementTypes) {
			super(title, description, null, null);
			this.elementTypes = elementTypes;
		}

		/**
		 * @generated
		 */
		public Tool createTool() {
			Tool tool = new UnspecifiedTypeCreationTool(elementTypes);
			tool.setProperties(getToolProperties());
			return tool;
		}
	}

	/**
	 * @generated
	 */
	private static class LinkToolEntry extends ToolEntry {

		/**
		 * @generated
		 */
		private final List relationshipTypes;

		/**
		 * @generated
		 */
		private LinkToolEntry(String title, String description,
				List relationshipTypes) {
			super(title, description, null, null);
			this.relationshipTypes = relationshipTypes;
		}

		/**
		 * @generated
		 */
		public Tool createTool() {
			Tool tool = new UnspecifiedTypeConnectionTool(relationshipTypes);
			tool.setProperties(getToolProperties());
			return tool;
		}
	}
}
