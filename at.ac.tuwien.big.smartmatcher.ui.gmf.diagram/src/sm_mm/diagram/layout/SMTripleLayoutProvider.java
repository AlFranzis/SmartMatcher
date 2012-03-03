package sm_mm.diagram.layout;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.AbstractLayoutEditPartProvider;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.ILayoutNode;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.ILayoutNodeOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.LayoutType;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;

import sm_mm.Operator;

public class SMTripleLayoutProvider extends AbstractLayoutEditPartProvider {
	
	public static String DEFAULT_LAYOUT = "Default";

	private static String CONTAINER_NAME = "Sm_mm_1";
	
	
	@Override
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


	@Override
	public Command layoutEditParts(List selectedObjects, IAdaptable layoutHint) {
		throw new IllegalArgumentException("Method not implemented");
	}

	
	@Override
	public Command layoutEditParts(GraphicalEditPart containerEditPart, IAdaptable layoutHint) {
		CompoundCommand cc = new CompoundCommand("");
		ModelManager mmanager = new ModelManager();
		
		List<IGraphicalEditPart> parts = (List<IGraphicalEditPart>) containerEditPart.getChildren();
		for(IGraphicalEditPart gep : parts) {
			// we just care about diagram nodes (= classes and operators) 
			if (gep instanceof ShapeEditPart) {
				ShapeEditPart shapeEP = (ShapeEditPart) gep;
				Node n = (Node)shapeEP.getModel();
				EObject obj = n.getElement();
				if(obj instanceof Operator) {
					Operator o = (Operator) obj;
					System.out.println("Operator found: " + o);
					mmanager.addShape(o, shapeEP);
				} else if(obj instanceof sm_mm.Class) {
					sm_mm.Class c = (sm_mm.Class) obj;
					System.out.println("Class found: " + c);
					mmanager.addShape(c, shapeEP);
				} else 
					throw new IllegalArgumentException("Unknown diagram node type");
			}
		}
		
		Set<Operator> ops = mmanager.getOpShapes().keySet();
		BoxBuilder bbuilder = new BoxBuilder();
		// create boxes
		List<Box> boxes = bbuilder.build(ops);
		
		BoxSorter bsorter = new BoxSorterImpl();
		bsorter.setBoxes(boxes);
		// sort boxes
		List<Box> sorted = bsorter.getSorted();
		
		BoxSorter bsopt = new BoxSortOptimizer();
		bsopt.setBoxes(sorted);
		// optimize sort order
		sorted = bsopt.getSorted();
		
		// add unmapped elements are added to a single box 
		// which is layouted at the diagram canvas bottom
		Box unmappedNodes = new Box();
		Map<sm_mm.Class,ShapeEditPart> shapes = mmanager.getClassShapes();
		for(sm_mm.Class c : shapes.keySet()) {
			if(!c.isMapped()) unmappedNodes.addAssociated(c);
		}
		// Box containing unmapped nodes is added at last
		sorted.add(unmappedNodes);
		
		ContainerPartLayouter cpl = new ContainerPartLayouter(mmanager);
		// layout boxes
		LayoutContainerPart lcp1 = cpl.layout(containerEditPart, sorted);
		
		//horizontal layout optimization
		LayoutContainerPart lcp = new HorizontalLayouter().layout(lcp1);
		
		for(LayoutBoxPart lbp : lcp.getLayoutBoxes()) {
			// create move command for all operators
			for(LayoutOpPart lop : lbp.getOperators()) {
				Point oldLocation = lop.getOldNodeLocation();
				Point newLocation = lop.getNewNodeLocation();
				Command cmd = createMoveCommand(lop.getNode(), oldLocation, newLocation);
                 if (cmd != null && cmd.canExecute()) {
                     cc.add(cmd);
                 }
			}
			// create move command for all lhs classes
			for(LayoutClassPart llcp : lbp.getLhsClasses()) {
				Point oldLocation = llcp.getOldNodeLocation();
				Point newLocation = llcp.getNewNodeLocation();
				Command cmd = createMoveCommand(llcp.getNode(), oldLocation, newLocation);
                 if (cmd != null && cmd.canExecute()) {
                     cc.add(cmd);
                 }
			}
			// create move command for all rhs classes
			for(LayoutClassPart rlcp : lbp.getRhsClasses()) {
				Point oldLocation = rlcp.getOldNodeLocation();
				Point newLocation = rlcp.getNewNodeLocation();
				Command cmd = createMoveCommand(rlcp.getNode(), oldLocation, newLocation);
                 if (cmd != null && cmd.canExecute()) {
                     cc.add(cmd);
                 }
			}
			
			
		}
		
		return cc;
	}
	
	
	/*
	 * Creates the command to move the node figure from the old location to the new one.
	 */
	private Command createMoveCommand(ShapeEditPart sep, Point oldLocation, Point newLocation) {
		ChangeBoundsRequest request = new ChangeBoundsRequest(RequestConstants.REQ_MOVE);

		sep.getFigure().translateToAbsolute(oldLocation);
		sep.getFigure().translateToAbsolute(newLocation);

		PrecisionPoint delta = new PrecisionPoint(
							newLocation.preciseX() - oldLocation.preciseX(), 
							newLocation.preciseY() - oldLocation.preciseY());

		request.setEditParts(sep);
		request.setMoveDelta(delta);
		request.setLocation(newLocation);

		Command cmd = sep.getCommand(request);
		return cmd;
	}

}
