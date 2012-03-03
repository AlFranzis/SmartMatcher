package com.big.tuwien.SmartMatcher.views.iterator;


/**
 * A NodeIterator represents a node in the dependency graph. It contains all elements
 * that fulfill the context constraints to be in a certain mapping role.
 * A NodeIterator differs from a {@link java.util.Iterator} in that
 * it's iteration position after initialization is already the first element
 * (not before the first element). This first element (or the element at the current 
 * iteration position in general) can be retrieved by calling the
 * {@link #current()} method.
 * @author alex
 *
 */
public interface NodeIt<T> {
	
	/**
	 * Returns the role that is associated with the elements
	 * contained in the iterator.
	 * @return
	 */
	public Role getRole();
	
	
	/**
	 * This method is called by parent nodes of the dependency graph if the 
	 * parental context has changed.
	 * @param t Calling parent node.
	 * @param context The new context.
	 * @throws TransitionException
	 */
	public <R> void updateContext(NodeIt<R> t, Context context) throws TransitionException;
	
	
	/**
	 * Returns the element at the current iteration position.
	 * @return Element at current iteration position.
	 */
	public T current();
	
	
	/**
	 * Returns if there is an element at the current iteration position.
	 * If this method returns false then {@link #hasNext()} returns false too.
	 * @return 
	 */
	public boolean hasCurrent();
	
	
	/**
	 * Returns if there is at least one more element in the iterator.
	 * @return
	 */
	public boolean hasNext();
	
	
	/**
	 * Returns the next element in the iterator.
	 * @return
	 */
	public T next();
	
	
	/**
	 * Resets the iterator to the position of the first element.
	 */
	public void reset();
}
