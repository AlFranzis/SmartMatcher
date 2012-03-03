package smartmatcher;

public class ClazzSimilarity implements Measure<Clazz> {
	
	public double sim(Clazz c1, Clazz c2) {
		double nameSim = new NameSimilarity().sim(c1.getPredicate(Name.NAME, Name.class), c2.getPredicate(Name.NAME, Name.class));
		return nameSim;
	}
} 
