/**
 * 
 */
package smartmatcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class DummyBlockManager implements BlockManager {
	private static Logger logger = Logger.getLogger("DummyBlockManager");
	private List<Block> blocks = new ArrayList<Block>();
	private List<Construct> constructs = new ArrayList<Construct>();
	

	public DummyBlockManager() {
		DummyBlockFactory bFactory = new DummyBlockFactory();
		DummyConstructFactory cFactory = new DummyConstructFactory();
		blocks.addAll(bFactory.createBlocks());
		constructs.addAll(cFactory.createConstructs());
	}
	
	
	@Override
	public Map<Construct, Block> getBlocks(double[] position) {
		if(position.length != constructs.size()) throw new IllegalArgumentException("The given position vector has illegal dimension size");	
		
		Map<Construct,Block> m = new HashMap<Construct,Block>();
		for (int i = 0; i < position.length; i++) {
			Construct c = constructs.get(i);
			
			Double d = position[i];
			if(d < 0.0 || d > blocks.size()) throw new IllegalArgumentException("The given position vector contains coordinates out of range");
			// convert double to int by truncation
			int intValue = d.intValue();
			logger.debug("Rounding: Double: " + d + " -> Int: " + intValue);
			Block b = blocks.get(intValue);
			
			m.put(c, b);
		}
		return m;
	}


	@Override
	public int constructs() {
		return constructs.size();
	}
	
	
	
	
	@Override
	public int blocks() {
		return blocks.size();
	}
	
}