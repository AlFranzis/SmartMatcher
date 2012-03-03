package smartmatcher;

public class AttributeSimilarity implements Measure<Attribute> {
	
	public double sim(Attribute a1, Attribute a2) {
		double typeSim = new TypeSimilarity().sim(a1.getPredicate(Type.NAME, Type.class), a2.getPredicate(Type.NAME, Type.class));
		double NameSim = new NameSimilarity().sim(a1.getPredicate(Name.NAME, Name.class), a2.getPredicate(Name.NAME, Name.class));
		
		return (typeSim + NameSim) / 2;		// avg
	}
} 
