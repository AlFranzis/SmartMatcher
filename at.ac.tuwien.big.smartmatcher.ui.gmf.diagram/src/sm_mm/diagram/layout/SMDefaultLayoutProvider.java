package sm_mm.diagram.layout;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.graph.DirectedGraph;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.diagram.ui.providers.LeftRightProvider;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.ILayoutNode;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.ILayoutNodeOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.LayoutType;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;

public class SMDefaultLayoutProvider extends LeftRightProvider {
	
	public static String DEFAULT_LAYOUT = "Default";

	private static String CONTAINER_NAME = "Sm_mm_1";
	
	
	public boolean provides(IOperation operation) {
		
		System.out.println("provides() called");
		
		// enable this provider only on SmartMatcher diagrams
		if (operation instanceof ILayoutNodeOperation) {
			Iterator<?> nodes = ((ILayoutNodeOperation) operation)
								.getLayoutNodes().listIterator();
			if (nodes.hasNext()) {
				View node = ((ILayoutNode) nodes.next()).getNode();
				Diagram container = node.getDiagram();
				
				if(container != null)
					System.out.println("Container type: " + container.getType());
				
				if (container == null || !(container.getType().equals(CONTAINER_NAME)))
						return false;
			}
		} else {
			return false;
		}
		
		IAdaptable layoutHint = ((ILayoutNodeOperation) operation).getLayoutHint();
		String layoutType = (String) layoutHint.getAdapter(String.class);
		return LayoutType.DEFAULT.equals(layoutType);
	}
	
	
	public Command layoutEditParts(GraphicalEditPart containerEditPart,
            IAdaptable layoutHint) {
		return super.layoutEditParts(containerEditPart, layoutHint);
	}
	
	
	public Command layoutEditParts(List selectedObjects, IAdaptable layoutHint) {
		return super.layoutEditParts(selectedObjects, layoutHint);
	}
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.providers.internal.DefaultProvider#translateToGraph(org.eclipse.draw2d.geometry.Rectangle)
	 */
	protected Rectangle translateToGraph(Rectangle r) {
		Rectangle rDP = r.getCopy();
		return rDP;
	}

	
	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.providers.internal.DefaultProvider#translateFromGraph(org.eclipse.draw2d.geometry.Rectangle)
	 */
	protected Rectangle translateFromGraph(Rectangle rect) {
		Rectangle rLP = rect.getCopy();
		return rLP;
	}	
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.providers.internal.DefaultProvider#createGraph()
	 */
	protected DirectedGraph createGraph() {
		DirectedGraph g = super.createGraph();
		g.setDirection(PositionConstants.WEST);
		return g;
	}



}
