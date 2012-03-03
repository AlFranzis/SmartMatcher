package smartmatcher;

import smartmatcher.Type.TYPE;

public class TypeSimilarity implements Measure<Type> {

	
	@Override
	public double sim(Type t1, Type t2) {
		TYPE type1 = t1.getValue();
		TYPE type2 = t2.getValue();
		return typeSim(type1, type2);
	}
	
	
	private double typeSim(TYPE t1, TYPE t2) {
		if(t1.equals(t2)) return 1;
		return 0;
	}

}
