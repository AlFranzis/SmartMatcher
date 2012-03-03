package sm_mm.diagram.layout;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeEditPart;

import sm_mm.Operator;
import sm_mm.Class;


public class ModelManager {
	private Map<Class,ShapeEditPart> classes = new HashMap<Class,ShapeEditPart>();
	private Map<Operator,ShapeEditPart> ops = new HashMap<Operator,ShapeEditPart>();
	
	
	public ShapeEditPart getClassShape(Class e) {
		return classes.get(e);
	}
	
	
	public Map<Class,ShapeEditPart> getClassShapes() {
		return Collections.unmodifiableMap(classes);
	}
	
	
	public ShapeEditPart getOpShape(Operator e) {
		return ops.get(e);
	}
	
	
	public Map<Operator,ShapeEditPart> getOpShapes() {
		return Collections.unmodifiableMap(ops);
	}
	
	
	public void addShape(Operator o, ShapeEditPart shape) {
		ops.put(o, shape);
	}
	
	
	public void addShape(Class c, ShapeEditPart shape) {
		classes.put(c, shape);
	}
}
