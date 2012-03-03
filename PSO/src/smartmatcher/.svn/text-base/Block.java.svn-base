package smartmatcher;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Block {
	private int id;
	protected Set<Construct> sourceConstructs = new HashSet<Construct>();
	protected Set<Construct> targetConstructs = new HashSet<Construct>();
	
	
	public Block(int id) {
		this.id = id;
	}
	
	
	public Block(int id, Collection<Construct> sourceConstructs) {
		this(id);
		this.sourceConstructs.addAll(sourceConstructs);
	}
	
	
	public int getId() {
		return id;
	}
	
	
	public Set<Construct> getSourceConstructs() {
		return sourceConstructs;
	}
	
	
	public void setSourceConstructs(Set<Construct> sourceConstructs) {
		this.sourceConstructs = sourceConstructs;
	}
	
	
	public Set<Construct> getTargetConstructs() {
		return targetConstructs;
	}
	
	
	public void setTargetConstructs(Set<Construct> targetConstructs) {
		this.targetConstructs = targetConstructs;
	}
	
	public String toString() {
		return "block" + ": " + sourceConstructs;
	}
	
}
