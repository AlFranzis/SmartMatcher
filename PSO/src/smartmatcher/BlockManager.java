package smartmatcher;

import java.util.Map;

public interface BlockManager {
	
	public Map<Construct,Block> getBlocks(double[] position);
	
	
	
	public int constructs();
	
	public int blocks();
}
