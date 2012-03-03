package smartmatcher;

import uk.ac.shef.wit.simmetrics.similaritymetrics.Levenshtein;

public class NameSimilarity implements Measure<Name> {

	@Override
	public double sim(Name n1, Name n2) {
		return new Levenshtein().getSimilarity(n1.getValue(), n2.getValue());
	}

}
