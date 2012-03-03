package sm_mm.diagram.layout;

import sm_mm.A2A;
import sm_mm.A2C;
import sm_mm.Attribute;
import sm_mm.C2C;
import sm_mm.Reference;
import sm_mm.Class;
import sm_mm.Sm_mmFactory;

public class PersonFamilyModel {
	public final Class lPerson;
	public final Attribute lFn;
	public final Attribute lLn;
	public final Class rPerson;
	public final Attribute rFn;
	public final Class rFamily;
	public final Attribute rLn;
	public final Reference rBelongsTo;
	
	public final C2C person2person;
	public final A2A fn2fn;
	public final A2C a2c;
	
	
	public PersonFamilyModel() {
		Sm_mmFactory factory = Sm_mmFactory.eINSTANCE;
		BuildingHelper bh = new BuildingHelper(factory);
		
		// lhs model
		lPerson = bh.createClass("Person", true);
		lFn = bh.addAttribute(lPerson, "firstname", "String");
		lLn = bh.addAttribute(lPerson, "lastname", "String");
		
		// rhs model
		rPerson = bh.createClass("Person", false);
		rFn = bh.addAttribute(rPerson, "firstname", "String");
		
		rFamily = bh.createClass("Family", false);
		rLn = bh.addAttribute(rFamily, "lastname", "String");
		
		rBelongsTo = bh.createReference("belongsTo", rPerson, rFamily);
			
		// mapping model
		person2person = bh.createC2C(lPerson, rPerson);
		
		fn2fn = bh.createA2A(lFn, rFn);
		bh.addContext(person2person, fn2fn);
		
		a2c = bh.createA2C(lLn, rFamily, rBelongsTo, rLn);
		bh.addContext(person2person, a2c);
	}
	
	
	
}
