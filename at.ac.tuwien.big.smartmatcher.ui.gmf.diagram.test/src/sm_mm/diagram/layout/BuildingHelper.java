package sm_mm.diagram.layout;

import sm_mm.A2A;
import sm_mm.A2C;
import sm_mm.Attribute;
import sm_mm.C2C;
import sm_mm.ContextOperator;
import sm_mm.Operator;
import sm_mm.R2A;
import sm_mm.R2R;
import sm_mm.Reference;
import sm_mm.Sm_mmFactory;

/**
 * This class contains helper methods to build models.
 * @author alex
 *
 */
public class BuildingHelper {
	private Sm_mmFactory factory;
	
	
	public BuildingHelper(Sm_mmFactory factory) {
		this.factory = factory;
	}

	
	protected sm_mm.Class createClass(String name, boolean lhs) {
		sm_mm.Class c = factory.createClass();
		c.setName(name);
		c.setLhs(lhs);
		return c;
	}
	
	
	protected Attribute createAttribute(String name, String type, boolean lhs) {
		Attribute a = factory.createAttribute();
		a.setName(name);
		a.setType(type);
		a.setLhs(lhs);
		return a;
	}
	
	
	protected Reference createReference(String name, sm_mm.Class src, sm_mm.Class target) {
		Reference r = factory.createReference();
		r.setName(name);
		r.setSource(src);
		r.setLhs(src.isLhs());
		r.setTarget(target);
		return r;
	}
	
	
	protected Attribute addAttribute(sm_mm.Class c, String name, String type) {
		Attribute a = factory.createAttribute();
		a.setName(name);
		a.setType(type);
		a.setLhs(c.isLhs());
		a.setContainer(c);
		return a;
	}
	
	
	protected A2A createA2A(Attribute lhs, Attribute rhs) {
		A2A a2a = factory.createA2A();
		a2a.setLhs(lhs);
		a2a.setRhs(rhs);
		return a2a;
	}
	
	
	protected R2R createR2R(Reference lhs, Reference rhs) {
		R2R a2a = factory.createR2R();
		a2a.setLhs(lhs);
		a2a.setRhs(rhs);
		return a2a;
	}
	
	
	protected A2C createA2C(Attribute lAtt, sm_mm.Class rClass, Reference rRef, Attribute rAtt) {
		A2C a2c = factory.createA2C();
		a2c.setLhsAttribute(lAtt);
		a2c.setRhsClass(rClass);
		a2c.setRhsReference(rRef);
		a2c.setRhsAttribute(rAtt);
		return a2c;
	}
	
	
	protected R2A createR2A(Reference lRef, Attribute rAtt1, Attribute rAtt2) {
		R2A r2a = factory.createR2A();
		r2a.setLhsReference(lRef);
		r2a.setRhsAttribute1(rAtt1);
		r2a.setRhsAttribute2(rAtt2);
		return r2a;
	}
	
	
	protected C2C createC2C(sm_mm.Class lhs, sm_mm.Class rhs) {
		C2C c2c = factory.createC2C();
		c2c.setLhs(lhs);
		c2c.setRhs(rhs);
		return c2c;
	}

	
	protected void addContext(ContextOperator cop, Operator op) {
		cop.getContextMappings().add(op);
	}
	
	
}
