package smartmatcher;

import java.util.List;

public class Attribute extends Construct {
	public final static String NAME = "attribute";
	
	
	public Attribute(int id, List<Predicate<?>> ps) {
		super(id, NAME);
		addAll(ps);
	}
	
	
	public Attribute(int id, Clazz c, List<Predicate<?>> ps) {
		this(id, ps);
		setClazz(c);
	}
	
	
	public void setClazz(Clazz c) {
		this.related.add(c);
	}
	
}
