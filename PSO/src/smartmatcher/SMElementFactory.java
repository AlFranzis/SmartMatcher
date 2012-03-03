package smartmatcher;

import java.util.Arrays;

public class SMElementFactory {
	
	public Attribute createAttribute(int id, Clazz c, String name, Type.TYPE t) {
		return new Attribute(id, c, Arrays.<Predicate<?>>asList(new Name(name), new Type(t)));
	}
	
	
	public Clazz createClazz(int id, String name) {
		return new Clazz(id, Arrays.<Predicate<?>>asList(new Name(name)));
	}
}
