package sm_mm.diagram.layout;

import sm_mm.A2A;
import sm_mm.A2C;
import sm_mm.Attribute;
import sm_mm.C2C;
import sm_mm.R2A;
import sm_mm.R2R;
import sm_mm.Reference;
import sm_mm.Sm_mmFactory;
import sm_mm.Class;

public class ExtPersonFamilyModel {
	public final Class lPerson;
	public final Attribute lFn;
	public final Attribute lLn;
	public final Class lAddress;
	public final Attribute lStreet;
	public final Attribute lCity;
	public final Attribute lCountry;
	public final Reference lLivesAt;
	public final Class lCompany;
	public final Attribute lCompanyName;
	public final Reference lWorksAt; 
	
	public final Class rPerson;
	public final Attribute rFn;
	public final Attribute rAddressId;
	public final Class rFamily;
	public final Attribute rLn;
	public final Reference rBelongsTo;
	public final Class rAddress;
	public final Attribute rAddressIdAtt;
	public final Attribute rStreet;
	public final Attribute rCity;
	public final Attribute rCountry;
	public final Class rCompany;
	public final Attribute rCompanyName;
	public final Reference rWorksAt;
	
	public final C2C person2person; 
	public final A2A fn2fn;
	public final A2C a2c;
	public final C2C address2address;
	public final A2A street2street;
	public final A2A city2city;
	public final A2A country2country;
	public final R2A r2a;
	public final C2C comp2comp;
	public final A2A cname2cname;
	public final R2R worksAt2worksAt; 
	
	
	public ExtPersonFamilyModel() {
		Sm_mmFactory factory = Sm_mmFactory.eINSTANCE;
		BuildingHelper bh = new BuildingHelper(factory);
		
		// lhs model
		lPerson = bh.createClass("Person", true);
		lFn = bh.addAttribute(lPerson, "firstname", "String");
		lLn = bh.addAttribute(lPerson, "lastname", "String");
		
		lAddress = bh.createClass("Address", true);
		lStreet = bh.addAttribute(lAddress, "street", "String");
		lCity = bh.addAttribute(lAddress, "city", "String");
		lCountry = bh.addAttribute(lAddress, "country", "String");
		
		lLivesAt = bh.createReference("livesAt", lPerson, lAddress);
		
		lCompany = bh.createClass("Company", true);
		lCompanyName = bh.addAttribute(lCompany, "name", "String");
		
		lWorksAt = bh.createReference("worksAt", lPerson, lCompany);
		
		
		// rhs model
		rPerson = bh.createClass("Person", false);
		rFn = bh.addAttribute(rPerson, "firstname", "String");
		rAddressId = bh.addAttribute(rPerson, "address", "String");
		
		rFamily = bh.createClass("Family", false);
		rLn = bh.addAttribute(rFamily, "lastname", "String");
		
		rBelongsTo = bh.createReference("belongsTo", rPerson, rFamily);
		
		rAddress = bh.createClass("Address", false);
		rAddressIdAtt = bh.addAttribute(rAddress, "id", "String");
		rStreet = bh.addAttribute(rAddress, "street", "String");
		rCity = bh.addAttribute(rAddress, "city", "String");
		rCountry = bh.addAttribute(rAddress, "country", "String");
		
		rCompany = bh.createClass("Company", false);
		rCompanyName = bh.addAttribute(rCompany, "name", "String");
		
		rWorksAt = bh.createReference("worksAt", rPerson, rCompany);
			
		// mapping model
		person2person = bh.createC2C(lPerson, rPerson);
		
		fn2fn = bh.createA2A(lFn, rFn);
		bh.addContext(person2person, fn2fn);
		
		a2c = bh.createA2C(lLn, rFamily, rBelongsTo, rLn);
		bh.addContext(person2person, a2c);
		
		address2address = bh.createC2C(lAddress, rAddress);
		
		street2street = bh.createA2A(lStreet, rStreet);
		bh.addContext(address2address, street2street);
		city2city = bh.createA2A(lCity, rCity);
		bh.addContext(address2address, city2city);
		country2country = bh.createA2A(lCountry, rCountry);
		bh.addContext(address2address, country2country);
		
		r2a = bh.createR2A(lLivesAt, rAddressId, rAddressIdAtt);
		bh.addContext(person2person, r2a);
		bh.addContext(address2address, r2a);
		
		comp2comp = bh.createC2C(lCompany, rCompany);
		
		cname2cname = bh.createA2A(lCompanyName, rCompanyName);
		bh.addContext(comp2comp, cname2cname);
		
		worksAt2worksAt = bh.createR2R(lWorksAt, rWorksAt);
		bh.addContext(person2person, worksAt2worksAt);
		bh.addContext(comp2comp, worksAt2worksAt);
	}
	
	
	
	
}
