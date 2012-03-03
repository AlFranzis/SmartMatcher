/**
 * 
 */
package smartmatcher;

import java.util.Arrays;
import java.util.List;

public class DummyBlockFactory implements BlockFactory {
	private SMElementFactory eFactory;
	
	
	public DummyBlockFactory() {
		eFactory = new SMElementFactory();
	}
	
	
	@Override
	public List<Block> createBlocks() {
		Clazz person = eFactory.createClazz(1, "Person");
		
		Attribute firstName = eFactory.createAttribute(2, person, "firstName", Type.TYPE.STRING);
		Block b1 = new Block(1, Arrays.asList(person, firstName));
		
		Attribute lastName = eFactory.createAttribute(3, person, "hometown", Type.TYPE.STRING);
		Block b2 = new Block(2, Arrays.asList(person, lastName));
		
		Attribute age = eFactory.createAttribute(4, person, "age", Type.TYPE.INTEGER);
		Block b3 = new Block(3, Arrays.asList(person, age));
		
		Clazz student = eFactory.createClazz(2, "Student");
		Block b4 = new Block(4, Arrays.<Construct>asList(student));
		
		Attribute name = eFactory.createAttribute(3, student, "name", Type.TYPE.STRING);
		Block b5 = new Block(5, Arrays.asList(student, name));
		
		
		return Arrays.asList(b1, b2, b3, b4, b5);
	}
	
}