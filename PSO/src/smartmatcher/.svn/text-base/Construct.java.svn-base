package smartmatcher;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class Construct {
	private int id;
	protected String name;
	protected Set<Construct> related = new HashSet<Construct>();
	protected Map<String,Predicate<?>> predicates = new HashMap<String,Predicate<?>>();
	
	
	public Construct(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	
	public int getId() {
		return id;
	}
	
	
	public String getName() {
		return name;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	public Set<Predicate<?>> getPredicates() {
		return new HashSet<Predicate<?>>(predicates.values());
	}
	
	
	public Predicate<?> getPredicate(String name) {
		return predicates.get(name);
	}
	
	
	@SuppressWarnings("unchecked")
	public <S extends Predicate> S getPredicate(String name, Class<S> clazz) {
		Predicate<?> p = predicates.get(name);
		if(!p.getClass().equals(clazz)) {
			throw new IllegalArgumentException("Predicate " + name + " is not of type " + clazz.getCanonicalName());
		}
		return (S) p;	// checked cast
	}
	
	
	public void addAll(Collection<Predicate<?>> ps) {
		for(Predicate<?> p : ps) {
			predicates.put(p.getName(), p);
		}
	}
	
	
	public void setRelated(Set<Construct> related) {
		this.related = related;
	}
	
	
	public Set<Construct> getRelated() {
		return related;
	}
	
	
	public String toString() {
		return name + ": " + predicates;
	}
	
}
