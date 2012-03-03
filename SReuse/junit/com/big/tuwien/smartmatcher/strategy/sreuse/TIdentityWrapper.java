package com.big.tuwien.smartmatcher.strategy.sreuse;

import static org.junit.Assert.*;

import org.junit.Test;

import com.big.tuwien.smartmatcher.strategy.sreuse.XMLElementResolverFactory.IdentityWrapper;

public class TIdentityWrapper {

	@Test
	public void testIdWrapper() {
		Integer i = 10;
		
		IdentityWrapper<Integer> id1 = new IdentityWrapper<Integer>(i);
		IdentityWrapper<Integer> id2 = new IdentityWrapper<Integer>(i);
		
		assertTrue(id1.hashCode() == id2.hashCode());
		assertTrue(id1.equals(id2));
		
	}
}
