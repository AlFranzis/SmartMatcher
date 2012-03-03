package com.big.tuwien.SmartMatcher.strategy.pso;

import java.util.Set;

public class TreeView {
	protected Set<Node> roots;
	protected Set<Node> nodes;
	
	
	public void addRoot(Node n) {
		roots.add(n);
		
	}
	
	
	public void addNode(Node n) {
		if(nodes.contains(n)) throw new IllegalArgumentException("Node " + n + " is already member of the tree");
		
		if(n.isRoot()) addRoot(n);
		else {
			
			nodes.add(n);
		}
		
	}
	
	
	public void removeNode(Node n) {
		
	}
	
	
	private int maxPathLength(Node n) {
		if(n.isLeaf()) return 0;
		
		int max = 0;
		for(Node c : n.getChildren()) {
			int l = maxPathLength(c);
			if(l > max) max = l;
		}
		
		return max + 1;
	}
	
	
	private int descendents(Node n) {
		if(n.isLeaf()) return 0;
		
		int children = n.children();
		for(Node c : n.getChildren()) {
			children += descendents(c);
		}
		
		return children;
	}
	
	
	public Set<Node> getRoots() {
		return roots;
	}
	
	
	public int nodes() {
		return roots.size();
	}
	
	
	public static class Node {
		private Set<Node> parents;
		private Set<Node> children;
		
		
		public Node(Set<Node> parents, Set<Node> children) {
			this.parents = parents;
			this.children = children;
		}


		public Set<Node> getParents() {
			return parents;
		}
		
		
		public int parents() {
			return parents.size();
		}

		
		public boolean isRoot() {
			return parents.isEmpty();
		}
		
		
		public Set<Node> getChildren() {
			return children;
		}
		
		
		public int children() {
			return children.size();
		}
		
		
		public boolean hasChildren() {
			return !children.isEmpty();
		}
		
		
		public boolean isLeaf() {
			return hasChildren();
		}
		
		
		public void setChildren(Set<Node> cn) {
			children = cn;
		}
		
		
		public void setParents(Set<Node> pn) {
			parents = pn;
		}
	}
	
	
	
	
}
