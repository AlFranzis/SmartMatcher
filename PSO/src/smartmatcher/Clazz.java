package smartmatcher;

import java.util.List;

public class Clazz extends Construct {
	public final static String NAME = "clazz";
	
	public Clazz(int id, List<Predicate<?>> ps) {
		super(id, NAME);
		addAll(ps);
	}
}
