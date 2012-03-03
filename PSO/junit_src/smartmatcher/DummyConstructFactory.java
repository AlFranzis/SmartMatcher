/**
 * 
 */
package smartmatcher;

import java.util.Arrays;
import java.util.List;

public class DummyConstructFactory implements ConstructFactory {
	private SMElementFactory eFactory;
	
	
	public DummyConstructFactory() {
		eFactory = new SMElementFactory();
	}
	
	
	@Override
	public List<Construct> createConstructs() {
		Clazz student = eFactory.createClazz(1, "Student");
		
		Attribute name = eFactory.createAttribute(2, student, "name", Type.TYPE.STRING);
		
		Attribute years = eFactory.createAttribute(3, student, "years", Type.TYPE.INTEGER);
		
		return Arrays.asList(student, name, years);
	}
	
}