package smartmatcher;

public class BlockConstructSimilarity {
	
	public double sim(Block b, Construct c) {
		for(Construct src : b.getSourceConstructs()) {
			if(c.getName().equals(src.getName())) {
				
				String cname = c.getName();
				
				if(cname.equals(Clazz.NAME)) {
					return new ClazzSimilarity().sim((Clazz) c, (Clazz) src); 
				} else if(cname.equals(Attribute.NAME)) {
					return new AttributeSimilarity().sim((Attribute) c, (Attribute) src); 
				} else {
					throw new PSOException("Unknown construct type");
				}
				
			}
		}
		return 0;
	}
}
